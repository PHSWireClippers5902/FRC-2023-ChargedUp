package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveEncoder extends SubsystemBase{
    private static WPI_TalonSRX tEncoder1 = new WPI_TalonSRX(1);
    private static WPI_TalonSRX tEncoder2 = new WPI_TalonSRX(2);
    private static WPI_TalonSRX tEncoder3 = new WPI_TalonSRX(3);
    private static WPI_TalonSRX tEncoder4 = new WPI_TalonSRX(4);
    
public DriveEncoder(){
    tEncoder1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    tEncoder1.configSelectedFeedbackCoefficient(1.0/4096.0,0,10);
    tEncoder2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    tEncoder2.configSelectedFeedbackCoefficient(1.0/4096.0,0,10);
    tEncoder3.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    tEncoder3.configSelectedFeedbackCoefficient(1.0/4096.0,0,10);
    tEncoder4.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    tEncoder4.configSelectedFeedbackCoefficient(1.0/4096.0,0,10);
}
    double rotations1 = tEncoder1.getSelectedSensorPosition() / 4096.0;
    double rotations2 = tEncoder2.getSelectedSensorPosition() / 4096.0;
    double rotations3 = tEncoder3.getSelectedSensorPosition() / 4096.0;
    double rotations4 = tEncoder4.getSelectedSensorPosition() / 4096.0;

public void print(){
    // System.out.println("Encoder one:" + rotations1);
    // System.out.println("Encoder two:" + rotations2);
    // System.out.println("Encoder three:" + rotations3);
    // System.out.println("Encoder four:" + rotations4);
    SmartDashboard.putNumber("Encoder one: ",  rotations1);
    SmartDashboard.putNumber("Encoder two: ", rotations2);
    SmartDashboard.putNumber("Encoder three:", rotations3);
    SmartDashboard.putNumber("Encoder four:", rotations4);
    
}

}
