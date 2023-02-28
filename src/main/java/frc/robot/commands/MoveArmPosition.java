package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.VisionConstants;
import frc.robot.subsystems.ArmSystem;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import java.lang.Math;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class MoveArmPosition extends CommandBase {
    private Joystick joystickone, joysticktwo;
    private ArmSystem arm;
    private double lowervelocity, uppervelocity;
    private PIDController upperPID, lowerPID;

    public MoveArmPosition(Joystick joystickone, Joystick joysticktwo, ArmSystem arm){
        this.joystickone = joystickone;
        this.joysticktwo = joysticktwo;
        this.arm = arm;
        addRequirements(arm);

        //Untuned
        upperPID = new PIDController(0.01, 0, 0.001);
        lowerPID = new PIDController(0.05, 0, 0.002);
    }

    @Override
    public void initialize(){
        upperPID.reset();
        lowerPID.reset();
    }

    @Override
    public void execute() {
        double lowerFeedForward = arm.calculateFeedForwardLower(arm.getLowerPosition(), arm.getUpperPosition(), arm.getLowerSpeed());
        //double lowerFeedBack = lowerPID.calculate(arm.getLowerPosition(), getLowerSetpoint());
        double lowerFeedBack = 0;
        arm.setLowerVoltage(lowerFeedForward + lowerFeedBack);
        double upperFeedForwrd = arm.calculateFeedForwardUpper(arm.getLowerPosition(), arm.getUpperPosition(), arm.getUpperSpeed());
        //double upperFeedBack = upperPID.calculate(arm.getUpperPosition(), getUpperSetpoint());
        double upperFeedBack = 0;
        arm.setUpperVoltage(upperFeedForwrd + upperFeedBack);
    }
    @Override
    public void end(boolean interrupted) {
        arm.setLowerVoltage(0);
        arm.setUpperVoltage(0);
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
