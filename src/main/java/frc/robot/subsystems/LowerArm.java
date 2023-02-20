package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
public class LowerArm extends SubsystemBase{

    CANSparkMax leftPart = new CANSparkMax(6,MotorType.kBrushless);
    CANSparkMax rightPart = new CANSparkMax(5,MotorType.kBrushless);

    CANCoder _leftencoder = new CANCoder(6);
    CANCoder _rightencoder = new CANCoder(5);

    public double leftDistance;
    public double rightDistance;


    public void bottomleftencoderoutput(){
    double bottomleftdegrees = _leftencoder.getPosition();
    System.out.println("CANCoder position is: " + bottomleftdegrees);
    }
    public void bottomrightencoderoutput(){
    double bottomrightdegrees = _rightencoder.getPosition();
    System.out.println("CANCoder position is: " + bottomrightdegrees);
    }
    
    public void setSpeed(double speed){
         leftPart.set(-speed);
         rightPart.set(speed);
    }


    
}
