package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.VisionConstants;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.LimelightSystem;
import frc.robot.subsystems.MecanumSystem;
import frc.robot.Constants;
public class AimRobot extends CommandBase {

  private MecanumSystem mecanum;
  private LimelightSystem limelight;
  private double kP_angle, kP_range, rangeError, angleError, rangeCorrection, angleCorrection;
  public AimRobot(MecanumSystem mecanum, LimelightSystem limelight){
    this.mecanum = mecanum;
    this.limelight = limelight;
    addRequirements(mecanum, limelight);
    kP_angle = .01;
    kP_range = .01;
  }

  @Override
  public void execute() {
    if(limelight.hasTarget() == 0){
      mecanum.drive(0, 0, .1);
    }
    else{
      rangeError = limelight.getDistance() - VisionConstants.RangeThreshhold;
      rangeCorrection = ((rangeError * kP_range) < .05) ? -((rangeError * kP_range) + .05) : -(rangeError * kP_range);
      angleError = limelight.getX();
      angleCorrection = ((angleError * kP_angle) < .05) ? ((angleError * kP_angle) + .05) : (angleError * kP_angle);
      SmartDashboard.putNumber("RangeCorrection", rangeCorrection);
      SmartDashboard.putNumber("AngleCorrection", angleCorrection);

      if (Math.abs(rangeCorrection) > .2){
        rangeCorrection = .2;
      }
      if(Math.abs(angleCorrection) > .2){
        angleCorrection = .2;
      }
      mecanum.drive(rangeCorrection, 0, 0);
    }
  }

  @Override
  public void end(boolean interrupted) {
  }
}
