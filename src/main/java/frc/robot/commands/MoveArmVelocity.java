package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.VisionConstants;
import frc.robot.subsystems.ArmSystem;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import java.lang.Math;
import edu.wpi.first.wpilibj.XboxController;

public class MoveArmVelocity extends CommandBase {
    private Joystick joystickone, joysticktwo;
    private ArmSystem arm;
    private XboxController xbox;
    private double lowervelocity, uppervelocity;

    public MoveArmVelocity(Joystick joystickone, Joystick joysticktwo, XboxController xbox, ArmSystem arm){
        this.joystickone = joystickone;
        this.joysticktwo = joysticktwo;
        this.xbox = xbox;
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        //lowervelocity = joystickone.getY()*.3;
        lowervelocity = xbox.getLeftY() * 0.2;
        arm.setLowerSpeed(lowervelocity);
        //uppervelocity = joysticktwo.getY()*.3;
        uppervelocity = xbox.getRightY() * 0.2;
        arm.setUpperSpeed(uppervelocity);

        System.out.println("lower: " + lowervelocity + " upper: " + uppervelocity);
    }
    @Override
    public void end(boolean interrupted) {
    }
}