package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import static frc.robot.Constants.ClimbConstants;


public class ClimbSystem extends SubsystemBase{

    private CANSparkMax m_motor;
    private SparkMaxPIDController m_pidController;
    private RelativeEncoder m_encoder;
    

    public ClimbSystem(){
        m_motor = new CANSparkMax(ClimbConstants.climbMotorPort, MotorType.kBrushless);
        m_motor.restoreFactoryDefaults();
        m_pidController = m_motor.getPIDController();
        m_encoder = m_motor.getEncoder();
        m_encoder.setPosition(0);
        //m_motor.setInverted(true);
        //who needs precision anyway??
        //m_encoder.setPositionConversionFactor(ClimbConstants.PostionConversionFactor);
        setSoftLimits();
        configurePID();
    }
    private void setSoftLimits(){
        m_motor.enableSoftLimit(SoftLimitDirection.kReverse, true);
        m_motor.setSoftLimit(SoftLimitDirection.kReverse, (ClimbConstants.MotorDownPosition));
        m_motor.enableSoftLimit(SoftLimitDirection.kForward, true);
        m_motor.setSoftLimit(SoftLimitDirection.kForward, (ClimbConstants.MotorUpPosition+2));
    }
    private void configurePID(){
        m_pidController.setP(ClimbConstants.P);
        m_pidController.setI(ClimbConstants.I);
        m_pidController.setD(ClimbConstants.D);
        m_pidController.setIZone(ClimbConstants.Iz);
        m_pidController.setFF(ClimbConstants.FF);
        m_pidController.setOutputRange(ClimbConstants.MinOutput, ClimbConstants.MaxOutput);
    }

    public void moveTo(double position){
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
