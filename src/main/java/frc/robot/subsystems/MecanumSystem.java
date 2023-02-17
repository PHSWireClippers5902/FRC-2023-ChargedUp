package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import static frc.robot.Constants.WheelConstants;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class MecanumSystem extends SubsystemBase{


    // public Encoder m_encoderFR;
    // public Encoder m_encoderFL;
    // public Encoder m_encoderBR;
    // public Encoder m_encoderBL;
    


    //motors for mecanum
    
    WPI_TalonSRX frontLeft = new WPI_TalonSRX(2);
    WPI_TalonSRX frontRight = new WPI_TalonSRX(3);
    WPI_TalonSRX backLeft = new WPI_TalonSRX(1);
    WPI_TalonSRX backRight = new WPI_TalonSRX(4);
    

    

   // public double getPosition(){
     //   return m_encoder.getPosition();
   // }

    // public double FLdistance(){
    //     m_encoderFR = frontRight.getEncoder();
    //     return 1;
    // }
    // public double FRdistance(){
    //     return 1;
    // }
    // public double BLdistance(){
    //     return 1;
    // }
    // public double BRdistance(){
    //     return 1;
    // }

    
    public void frontLeft(double speed){
        frontLeft.set(speed);
    };
    public void frontRight(double speed){
        frontRight.set(speed);
    };
    public void backLeft(double speed){
        backLeft.set(speed);
    };
    public void backRight(double speed){
        backRight.set(speed);
    };
 
}
