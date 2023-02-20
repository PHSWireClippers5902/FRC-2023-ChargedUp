package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import static frc.robot.Constants.WheelConstants;

import javax.swing.text.LayeredHighlighter;

import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants.WheelConstants;
public class TankDrive extends SubsystemBase{


    // public Encoder m_encoderFR;
    // public Encoder m_encoderFL;
    // public Encoder m_encoderBR;
    // public Encoder m_encoderBL;
    
    public DifferentialDrive m_tankDrive;

    //motors for mecanum
    
    WPI_TalonSRX left1 = new WPI_TalonSRX(WheelConstants.left1);
    WPI_TalonSRX left2 = new WPI_TalonSRX(WheelConstants.left2);
    WPI_TalonSRX right1 = new WPI_TalonSRX(WheelConstants.right1);
    WPI_TalonSRX right2 = new WPI_TalonSRX(WheelConstants.right2);

    MotorControllerGroup leftmotors = new MotorControllerGroup(left1, left2);
    MotorControllerGroup rightmotors = new MotorControllerGroup(right1, right2);
    
    public TankDrive(){
      leftmotors.setInverted(true);
      m_tankDrive = new DifferentialDrive(leftmotors, rightmotors);
    }
    @Override
    public void periodic(){
      leftmotors.set(.1);;
      rightmotors.setVoltage(6);
    }

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

    
    public void drive(double left, double right){
        m_tankDrive.tankDrive(left,right);
    }
 
}
