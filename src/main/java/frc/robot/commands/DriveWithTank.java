package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TankDrive;
// import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.TankDrive;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import javax.lang.model.util.ElementFilter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;

public class DriveWithTank extends CommandBase {

  private final TankDrive m_tank;
  private CommandXboxController m_xbox;
  private double l2;
  private double r2;
  private boolean r1;
  private boolean l1;


  public DriveWithTank(TankDrive tankSystem, CommandXboxController xbox){
    m_xbox = xbox;
    m_tank = tankSystem;

    addRequirements(m_tank);
  }

  @Override
  public void execute() {
    r2 = m_xbox.getRightTriggerAxis();
    l2 = -m_xbox.getLeftTriggerAxis();
    r1 = m_xbox.rightBumper().getAsBoolean();
    l1 = m_xbox.leftBumper().getAsBoolean();

    if (r1){
      m_tank.drive(0.3,-0.3);
    }else if (l1){
      m_tank.drive(-0.3, 0.3);
    }else{
      m_tank.drive(r2 + l2 , r2 + l2);
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_tank.drive(0,0);
  }
}