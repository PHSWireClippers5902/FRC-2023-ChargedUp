package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;;

public class UpperArm extends SubsystemBase{
    
    CANSparkMax SecondPartOfArm = new CANSparkMax(7,MotorType.kBrushless);
    CANCoder upperarm = new CANCoder(7);


    public void upperencoderoutput(){
        double upperdegrees = upperarm.getPosition();
        System.out.println("CANCoder position is: " + upperdegrees);
    }

    public void setSpeed(double speed){
        SecondPartOfArm.set(speed);
    }




}