package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.FlyWheel;

public class ActivateFlyWheel extends CommandBase {

  private final FlyWheel m_flyWheel;
  private double speed;

  public ActivateFlyWheel(double speed, FlyWheel subSystem){
    m_flyWheel = subSystem;
    addRequirements(m_flyWheel);
    this.speed = speed;
  }

  @Override
  public void execute() {
    m_flyWheel.speed(speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_flyWheel.speed(0);


  }
}
