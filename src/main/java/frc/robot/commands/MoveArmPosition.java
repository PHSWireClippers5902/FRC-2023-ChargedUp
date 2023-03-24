package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.VisionConstants;
import frc.robot.subsystems.ArmSystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import java.lang.Math;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static frc.robot.Constants.ArmConstants.*;



public class MoveArmPosition extends CommandBase {
    private CommandXboxController xbox;
    private ArmSystem arm;
    private double lowerSetpoint, upperSetpoint, lowerPosition, upperPosition;
    private ProfiledPIDController upperPID, lowerPID;

    public MoveArmPosition(CommandXboxController xbox, ArmSystem arm){
        this.xbox = xbox;
        this.arm = arm;
        addRequirements(arm);

        upperPID = new ProfiledPIDController(upperkP, upperkI, upperkD, upperConstraints);
        lowerPID = new ProfiledPIDController(lowerkP, lowerkI, lowerkD, lowerConstraints);

        lowerSetpoint = 40;
        upperSetpoint = 120;
    }

    @Override
    public void initialize(){
        upperPID.reset(new TrapezoidProfile.State(arm.getUpperPosition(), arm.getUpperSpeed()));
        lowerPID.reset(new TrapezoidProfile.State(arm.getLowerPosition(), arm.getLowerSpeed()));
    }

    @Override
    public void execute() {
        double lowerFeedForward = arm.lowerFeedForward(VecBuilder.fill(lowerSetpoint, upperSetpoint));
        double lowerFeedBack = lowerPID.calculate(arm.getLowerPosition(), lowerSetpoint);
        SmartDashboard.putNumber("lowvoltage", lowerFeedForward + lowerFeedBack);
        arm.setLowerVoltage(lowerFeedForward + lowerFeedBack);

        double upperFeedForward = arm.upperFeedForward(VecBuilder.fill(lowerSetpoint, upperSetpoint));
        double upperFeedBack = upperPID.calculate(arm.getUpperPosition(), upperSetpoint);
        SmartDashboard.putNumber("uppvoltage", upperFeedBack + upperFeedForward);
        arm.setUpperVoltage(upperFeedForward + upperFeedBack);
    }
    @Override
    public void end(boolean interrupted) {
        arm.setLowerVoltage(0);
        arm.setUpperVoltage(0);
    }

    public void setUpperSetpoint(double setpoint){
        upperSetpoint = setpoint;
    }

    public void setLowerSetpoint(double setpoint){
        lowerSetpoint = setpoint;
    }

    double getUpperSetpoint(){
        //Scale joystick?
        return 0;
    }

    double getLowerSetpoint(){
        //Scale joystick?
        return 0;
    }
}