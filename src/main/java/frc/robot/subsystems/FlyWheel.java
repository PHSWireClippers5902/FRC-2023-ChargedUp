package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.ActivateFlyWheel;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import static frc.robot.Constants.FlyWheelConstants;

public class FlyWheel extends SubsystemBase{

    public CANSparkMax spark = new CANSparkMax(FlyWheelConstants.motorOnePort, MotorType.kBrushless);
    public CANSparkMax spark2 = new CANSparkMax(FlyWheelConstants.motorTwoPort, MotorType.kBrushless);
  
    public void speed(double speed){
       spark.set(speed * -1);
       spark2.set(speed);
       
    }

    public void speedbutdifferent(double speed){
        
    }
}
