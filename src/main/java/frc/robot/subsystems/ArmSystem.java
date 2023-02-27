// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.RobotContainer;
import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.SoftLimitDirection;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmSystem extends SubsystemBase {
  private CANSparkMax upperMotor, lowerMotorOne, lowerMotorTwo;
  private SparkMaxPIDController upperController, lowerControllerOne, lowerControllerTwo;
  private RelativeEncoder upperEncoder, lowerEncoderOne, lowerEncoderTwo;


  public ArmSystem(){
    //Initialize Motors
    upperMotor = new CANSparkMax(ArmConstants.UpperArmMotorPort, MotorType.kBrushless);
    lowerMotorOne = new CANSparkMax(ArmConstants.LowerArmMotorOnePort, MotorType.kBrushless);
    lowerMotorTwo = new CANSparkMax(ArmConstants.LowerArmMotorTwoPort, MotorType.kBrushless);

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

    //Reset Encoders
    lowerEncoderOne.setPosition(0);
    lowerEncoderTwo.setPosition(0);
    upperEncoder.setPosition(0);

    //Encoder Scale factor? To Degrees?

    //Get motor PID controllers
    upperController = upperMotor.getPIDController();
    lowerControllerOne = lowerMotorOne.getPIDController();
    lowerControllerTwo = lowerMotorTwo.getPIDController();


    //Configure PID constants (WARNING!! Arbitrary!! Tune these)
    upperController.setP(.1);
    lowerControllerOne.setP(.1);
    lowerControllerTwo.setP(.1);

    upperController.setD(.05);
    lowerControllerOne.setD(.05);
    lowerControllerTwo.setD(.05);

    upperController.setOutputRange(-.3, .3);
    lowerControllerOne.setOutputRange(-.3, .3);
    lowerControllerTwo.setOutputRange(-.3, .3);

  }



  @Override
  public void periodic() {
    SmartDashboard.putNumber("Lower Arm One Position", lowerEncoderOne.getPosition());
    SmartDashboard.putNumber("Lower Arm Two Position", lowerEncoderTwo.getPosition());
    SmartDashboard.putNumber("Upper Arm Position", upperEncoder.getPosition());
    SmartDashboard.putNumber("Lower Arm One Speed", upperEncoder.getVelocity());
    SmartDashboard.putNumber("Lower Arm Two Speed", upperEncoder.getVelocity());
    SmartDashboard.putNumber("Upper Arm Speed", upperEncoder.getVelocity());

  }
  public void setUpperSpeed(double speed){
    upperMotor.set(speed);
  }
  public void setLowerSpeed(double speed){
    lowerMotorOne.set(speed);
  }
  public void raiseUpperto(double position){
    double scaledPosition = position*ArmConstants.UpperRange;
    upperController.setReference(scaledPosition, CANSparkMax.ControlType.kPosition);
  }
  public void raiseLowerto(double position){
    double scaledPosition = position*ArmConstants.LowerRange;
    lowerControllerOne.setReference(scaledPosition, CANSparkMax.ControlType.kPosition);
  }

  public double calculateFeedForward(){
    //Math
    return 0;
  }
}
