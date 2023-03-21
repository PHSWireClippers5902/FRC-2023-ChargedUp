package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import static frc.robot.Constants.WheelConstants;

import javax.swing.text.LayeredHighlighter;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants.WheelConstants;

public class TankDrive extends SubsystemBase{


    // public Encoder m_encoderFR;
    // public Encoder m_encoderFL;
    // public Encoder m_encoderBR;
    // public Encoder m_encoderBL;
    
    public DifferentialDrive m_Drive;

    //motors for mecanum
    
    public WPI_TalonSRX left1 = new WPI_TalonSRX(WheelConstants.left1);
    public WPI_TalonSRX left2 = new WPI_TalonSRX(WheelConstants.left2);
    public WPI_TalonSRX right1 = new WPI_TalonSRX(WheelConstants.right1);
    public WPI_TalonSRX right2 = new WPI_TalonSRX(WheelConstants.right2);

    MotorControllerGroup leftmotors = new MotorControllerGroup(left1, left2);
    MotorControllerGroup rightmotors = new MotorControllerGroup(right1, right2);
    
    public TankDrive(){
      rightmotors.setInverted(true);
      m_Drive = new DifferentialDrive(leftmotors, rightmotors);
      m_Drive.setDeadband(0.01);
      setLimits(2, 0.5);
    }

    @Override
    public void periodic(){
      // System.out.println("Left 1,2: " + left1.get() + " " + left2.get());
      // System.out.println("Right 1,2: " + right1.get() + " " + right2.get());
      // leftmotors.set(-.2);
      // rightmotors.set(-.2);
    }
    public void breaks(){
      left1.setNeutralMode(NeutralMode.Brake);
      left2.setNeutralMode(NeutralMode.Brake);
      right1.setNeutralMode(NeutralMode.Brake);
      right2.setNeutralMode(NeutralMode.Brake);
    }

    public void setLimits(double motorpower, double softlimit){
      left1.configClosedloopRamp(motorpower);
      left2.configClosedloopRamp(motorpower);
      right1.configClosedloopRamp(motorpower);
      right2.configClosedloopRamp(motorpower);

      // left1.configPeakOutputForward(softlimit);
      // left2.configPeakOutputForward(softlimit);
      // right1.configPeakOutputForward(softlimit);
      // right2.configPeakOutputForward(softlimit);

      // left1.configPeakOutputReverse(softlimit);
      // left2.configPeakOutputReverse(softlimit);
      // right1.configPeakOutputReverse(softlimit);
      // right2.configPeakOutputReverse(softlimit);
      
    }

   // public double getPosition(){
     //   return m_encoder.getPosition();
   // }


    public void drive(double left, double right){
        m_Drive.tankDrive(left,right, false);
    }
 
}