package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import static frc.robot.Constants.WheelConstants;

public class MecanumSystem extends SubsystemBase{

    public MecanumDrive m_robotDrive;

    //motors for mecanum
    private static WPI_TalonSRX frontRightWheel = new WPI_TalonSRX(WheelConstants.frontRightMotorPort);
    private static WPI_TalonSRX frontLeftWheel = new WPI_TalonSRX(WheelConstants.frontLeftMotorPort);
    private static WPI_TalonSRX backRightWheel = new WPI_TalonSRX(WheelConstants.backRightMotorPort);
    private static WPI_TalonSRX backLeftWheel = new WPI_TalonSRX(WheelConstants.backLeftMotorPort);
    

    public MecanumSystem(){
        //creating mecanum drive
        m_robotDrive = new MecanumDrive(frontLeftWheel, backLeftWheel, frontRightWheel, backRightWheel);
      
        //configuring motors
        frontLeftWheel.configOpenloopRamp(0.5);
        frontRightWheel.configOpenloopRamp(0.5);
        backLeftWheel.configOpenloopRamp(0.5);
        backRightWheel.configOpenloopRamp(0.5);
        frontRightWheel.setInverted(true);
        backRightWheel.setInverted(true);
    }

    //method to actually drive mecanum
    public void drive(double y, double x, double z){

        m_robotDrive.driveCartesian(y, x, z);
    }
 
}