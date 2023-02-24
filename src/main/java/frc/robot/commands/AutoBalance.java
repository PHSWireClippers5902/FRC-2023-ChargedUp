// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GyroscopeSystem;
import frc.robot.subsystems.TankDrive;

public class AutoBalance extends CommandBase {
  /** Creates a new GyroBalance. */ 
  private GyroscopeSystem m_gyro;
  private TankDrive m_tank;
  private double kP;
  private boolean tilt;

  public AutoBalance(GyroscopeSystem gyro, TankDrive tank) {
    m_gyro = gyro;
    m_tank = tank;
    kP = 0.008;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_gyro, m_tank);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    tilt = m_gyro.isTilted('z');
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_tank.drive(-kP*m_gyro.getTilt('z'), -kP*m_gyro.getTilt('z'));
    System.out.println("THE GYRO IS RUNNING FROM THE CHARGE STATION");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
