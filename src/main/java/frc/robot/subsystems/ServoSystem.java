package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;
import static frc.robot.Constants.ServoConstants;;

public class ServoSystem extends SubsystemBase{

    public Servo ballServo = new Servo(ServoConstants.BallServoPort);
    public Servo hookServo = new Servo(ServoConstants.HookServoPort);

    public void changeBallServo(double angle){
        ballServo.setAngle(angle); //sets the angle of the servo based on a double
    }

    public void changeHookServo(double angle){
        hookServo.setAngle(angle);
    }
    
}
