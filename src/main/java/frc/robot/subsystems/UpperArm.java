package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;;

public class UpperArm extends SubsystemBase{
    
    CANSparkMax SecondPartOfArm = new CANSparkMax(7,MotorType.kBrushless);

    public void setSpeed(double speed){
        SecondPartOfArm.set(speed);
    }




}
