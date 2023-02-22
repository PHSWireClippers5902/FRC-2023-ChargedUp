package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.VisionConstants;
import frc.robot.subsystems.ArmSystem;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import java.lang.Math;

import org.opencv.core.Mat;

public class MoveArm extends CommandBase {
    private Joystick joystick;
    private ArmSystem arm;
    private double lowerposition, upperposition;

    public MoveArm(Joystick joystick, ArmSystem arm){
        this.joystick = joystick;
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        if (!joystick.getRawButton(1)){
            lowerposition = Math.max(joystick.getY(), 0); 
            arm.raiseLowerArm(lowerposition);
        }
        else{
            upperposition = Math.max(0, joystick.getY());
            arm.raiseUpperArm(upperposition);
        }
    }
    @Override
    public void end(boolean interrupted) {

    }
}
