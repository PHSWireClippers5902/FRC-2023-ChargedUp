package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import static frc.robot.Constants.AimConstants;


public class AimSystem extends SubsystemBase{

    public CANSparkMax m_motor;
    public SparkMaxPIDController m_pidController;
    public RelativeEncoder m_encoder;
    

    public AimSystem(){
        m_motor = new CANSparkMax(AimConstants.aimMotorPort, MotorType.kBrushless);
        m_motor.restoreFactoryDefaults();
        m_pidController = m_motor.getPIDController();
        m_encoder = m_motor.getEncoder();
        m_motor.setInverted(true);
        m_encoder.setPosition(28);
        aimTo(AimConstants.MotorUpPosition);
        //setSoftLimits();
        configurePID();
    }

    private void setSoftLimits(){
        m_motor.enableSoftLimit(SoftLimitDirection.kReverse, true);
        m_motor.setSoftLimit(SoftLimitDirection.kReverse, AimConstants.MotorUpPosition);
        m_motor.enableSoftLimit(SoftLimitDirection.kForward, true);
        m_motor.setSoftLimit(SoftLimitDirection.kForward, AimConstants.MotorDownPosition);
    }
    private void configurePID(){
        m_pidController.setP(AimConstants.P);
        m_pidController.setI(AimConstants.I);
        m_pidController.setD(AimConstants.D);
        m_pidController.setIZone(AimConstants.Iz);
        m_pidController.setFF(AimConstants.FF);
        m_pidController.setOutputRange(AimConstants.MinOutput, AimConstants.MaxOutput);
    }
    public void aimTo(double position){
        m_pidController.setReference(position, CANSparkMax.ControlType.kPosition);
    }

    public void setPosition(double position){
        m_encoder.setPosition(position);
    }

    public void disableMotor(){
        m_motor.disable();
    }

    public double getPosition(){
        return m_encoder.getPosition();
    }

    public void setSpeed(double speed){
        m_motor.set(speed);
    }
}
