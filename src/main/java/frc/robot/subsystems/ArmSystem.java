  // Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.lang.Math;

import org.opencv.core.Mat;

import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import static frc.robot.Constants.ArmConstants.*;
import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.math.controller.ProfiledPIDController;

public class ArmSystem extends SubsystemBase {
  private CANSparkMax upperMotor, lowerMotorOne, lowerMotorTwo;
  private DCMotor upperMotorModel, lowerMotorModel;
  // private SparkMaxPIDController upperController, lowerControllerOne, lowerControllerTwo;
  private ProfiledPIDController upperPID, lowerPID;
  private RelativeEncoder upperEncoder, lowerEncoderOne, lowerEncoderTwo;
  private DigitalInput upperTopSwitch, upperBottomSwitch, lowerSwitch;
  private ArmFF feedforward;


  public ArmSystem(){
    //Initialize Motors
    upperMotor = new CANSparkMax(UpperArmMotorPort, MotorType.kBrushless);
    lowerMotorOne = new CANSparkMax(LowerArmMotorOnePort, MotorType.kBrushless);
    lowerMotorTwo = new CANSparkMax(LowerArmMotorTwoPort, MotorType.kBrushless);

    //Configure Motors
    upperMotor.restoreFactoryDefaults();
    lowerMotorOne.restoreFactoryDefaults();
    lowerMotorTwo.restoreFactoryDefaults();
    
    upperMotor.setInverted(true);
    lowerMotorOne.setInverted(true);

    lowerMotorTwo.follow(lowerMotorOne,true);

    //Get encoders
    lowerEncoderOne = lowerMotorOne.getEncoder();
    lowerEncoderTwo = lowerMotorTwo.getEncoder();
    upperEncoder = upperMotor.getEncoder();

    //Encoder Scale factor? To Degrees?
    lowerEncoderOne.setPositionConversionFactor(3.6);
    lowerEncoderTwo.setPositionConversionFactor(3.6);
    upperEncoder.setPositionConversionFactor(3.6);

    //Model Motor
    lowerMotorModel = DCMotor.getNEO(2).withReduction(ArmConstants.GearReduction);
    upperMotorModel = DCMotor.getNEO(1).withReduction(ArmConstants.GearReduction);

    feedforward = new ArmFF(lowerMotorModel, upperMotorModel);

    //Reset Encoders
    lowerEncoderOne.setPosition(-135);
    lowerEncoderTwo.setPosition(-135);
    upperEncoder.setPosition(15);

    upperPID = new ProfiledPIDController(upperkP, upperkI, upperkD, null);
    lowerPID = new ProfiledPIDController(lowerkP, lowerkI, lowerkD, null);


    // //Configure PID constants (WARNING!! Arbitrary!! Tune these)
    // upperController.setP(.1);
    // lowerControllerOne.setP(.1);
    // lowerControllerTwo.setP(.1);

    // upperController.setD(.05);
    // lowerControllerOne.setD(.05);
    // lowerControllerTwo.setD(.05);

    // upperController.setOutputRange(-.3, .3);
    // lowerControllerOne.setOutputRange(-.3, .3);
    // lowerControllerTwo.setOutputRange(-.3, .3);

    //Limit Switches
    upperTopSwitch = new DigitalInput(ArmConstants.upperArmTopSwitchPort);
    upperBottomSwitch = new DigitalInput(ArmConstants.upperArmBottomSwitchPort);
    lowerSwitch = new DigitalInput(ArmConstants.lowerArmSwitchPort);
  }



  @Override
  public void periodic() {
    // SmartDashboard.putNumber("Lower Arm Position", getLowerPosition());
    // // SmartDashboard.putNumber("Lower Arm Two Position", lowerEncoderTwo.getPosition());
    // SmartDashboard.putNumber("Upper Arm Position", getUpperPosition());
    // SmartDashboard.putNumber("Lower Arm One Speed", upperEncoder.getVelocity());
    // SmartDashboard.putNumber("Lower Arm Two Speed", upperEncoder.getVelocity());
    // SmartDashboard.putNumber("Upper Arm Speed", upperEncoder.getVelocity());
    // SmartDashboard.putBoolean("Upper Top Switch", upperTopSwitch.get());
    // SmartDashboard.putBoolean("Upper Bottom Switch", upperBottomSwitch.get());
    // SmartDashboard.putBoolean("Lower Switch", lowerSwitch.get());
  }
  public void setLowerVoltage(double voltage){
    lowerMotorOne.setVoltage(voltage);
  }
  public void setUpperVoltage(double voltage){
    upperMotor.setVoltage(voltage);
  }
  public void setUpperSpeed(double speed){
    if (upperTopSwitch.get()){
      speed = 0;
    }
    else if (upperBottomSwitch.get()){
      speed = Math.max(speed, 0);
    }
    upperMotor.set(speed);
  }
  public void setLowerSpeed(double speed){
    if (lowerSwitch.get()){
      speed = Math.max(speed, 0);
    }
    lowerMotorOne.set(speed);
  }

  // public void raiseUpperto(double position){
  //   double scaledPosition = position*ArmConstants.UpperRange;
  //   upperController.setReference(scaledPosition, CANSparkMax.ControlType.kPosition);
  // }
  // public void raiseLowerto(double position){
  //   double scaledPosition = position*ArmConstants.LowerRange;
  //   lowerControllerOne.setReference(scaledPosition, CANSparkMax.ControlType.kPosition);
  // }

  public double getLowerPosition(){
    return -1 * lowerEncoderOne.getPosition();
  }
  public double getUpperPosition(){
    return upperEncoder.getPosition();
  }
  public double getLowerSpeed(){
    return lowerEncoderOne.getVelocity();
  }
  public double getUpperSpeed(){
    return upperEncoder.getVelocity();
  }
  /** Archaic, infantile feedforward; doesn't even utilize the Euler-Lagrange equations */
  // public double calculateFeedForwardUpper(double lowerangle, double upperangle, double angularspeed){
  //   double theta = Math.toRadians(lowerangle);
  //   double phi = Math.toRadians(upperangle);
  //   double alpha = theta + (phi - Math.PI);
  //   double upperTorque = Constants.g * Math.cos(alpha) * ArmConstants.UpperArmLength * ((ArmConstants.UpperArmMass*.5) + ArmConstants.ClawMass);
  //   double voltage = 12 * ((upperTorque/260) + (angularspeed/567.6)); //Angular speed in deg/s, I think
  //   // SmartDashboard.putNumber("UpperTorque", upperTorque);
  //   // SmartDashboard.putNumber("UpperVoltage", voltage);
    
  //   return voltage;
  // }

  // public double calculateFeedForwardLower(double lowerangle, double upperangle, double angularspeed){
  //   double theta = Math.toRadians(lowerangle);
  //   double phi = Math.toRadians(upperangle);
  //   double torqueOne = Constants.g * Math.cos(theta) *
  //     ((ArmConstants.LowerArmMassTwo * ArmConstants.LowerArmLengthOne) + (ArmConstants.LowerArmMassTwo * ArmConstants.LowerArmLengthTwo) + (ArmConstants.LowerArmMassThree * ArmConstants.LowerArmLengthThree) + (ArmConstants.LowerArmMassFour * ArmConstants.LowerArmLength));
  //   double directLengthL = Math.sqrt(Math.pow(ArmConstants.UpperArmLength/2, 2) + Math.pow(ArmConstants.LowerArmLength, 2) - (ArmConstants.LowerArmLength * ArmConstants.UpperArmLength * Math.cos(phi)));
  //   double epsilon = Math.asin((ArmConstants.UpperArmLength*Math.sin(phi))/(2*directLengthL));
  //   double torqueTwo = ArmConstants.UpperArmMass * Constants.g * Math.cos(theta - epsilon) * directLengthL;
  //   double directLengthT = Math.sqrt(Math.pow(ArmConstants.UpperArmLength, 2) + Math.pow(ArmConstants.LowerArmLength, 2) - (2 * ArmConstants.LowerArmLength * ArmConstants.UpperArmLength * Math.cos(phi)));
  //   double beta = Math.asin((ArmConstants.UpperArmLength * Math.sin(phi))/directLengthT);
  //   double torqueThree = ArmConstants.ClawMass * Constants.g * Math.cos(theta - beta) * directLengthT;
  //   double torque = torqueOne + torqueTwo + torqueThree;
  //   double voltage = 12 * ((torque/260) + (angularspeed/567.6));
  //   // SmartDashboard.putNumber("LowerTorque", torque);
  //   // SmartDashboard.putNumber("LowerVoltage", voltage);
  //   return voltage;
  // }
}