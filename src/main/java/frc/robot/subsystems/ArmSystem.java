// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
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

    //Reset Motors
    upperMotor.restoreFactoryDefaults();
    lowerMotorOne.restoreFactoryDefaults();
    lowerMotorTwo.restoreFactoryDefaults();

    //Get encoders
    lowerEncoderOne = lowerMotorOne.getEncoder();
    lowerEncoderTwo = lowerMotorTwo.getEncoder();
    upperEncoder = upperMotor.getEncoder();

    //Reset Encoders
    lowerEncoderOne.setPosition(0);
    lowerEncoderTwo.setPosition(0);
    upperEncoder.setPosition(0);

    //Get motor PID controllers
    upperController = upperMotor.getPIDController();
    lowerControllerOne = lowerMotorOne.getPIDController();
    lowerControllerTwo = lowerMotorTwo.getPIDController();
  }



  @Override
  public void periodic() {
    SmartDashboard.putNumber("Lower Arm One", lowerEncoderOne.getPosition());
    SmartDashboard.putNumber("Lower Arm Two", lowerEncoderTwo.getPosition());
    SmartDashboard.putNumber("Upper Arm", upperEncoder.getPosition());
  }
}
