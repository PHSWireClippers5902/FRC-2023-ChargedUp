package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LowerArm;
import frc.robot.subsystems.UpperArm;
import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;

public class ArmControlTests extends CommandBase{
    
    private final UpperArm m_upperArm;
    private final LowerArm m_lowerArm;
    
    private XboxController m_xbox;
    private Joystick m_Joystick;
    private Joystick m_Joystick2;
    private Joystick m_analogstuff;
    private double vertAxis;
    private double zAxis;
    private int povForward;
    private int povBackward;
    private boolean button3;

    private DigitalInput clawUpper;
    private DigitalInput clawLower;
    private DigitalInput armBack;

    public ArmControlTests(LowerArm lowerArm, UpperArm upperArm, XboxController xbox, Joystick joystickone, Joystick joysticktwo, Joystick analogstuff){
        
        m_upperArm = upperArm;
        m_lowerArm = lowerArm;
        m_xbox = xbox;

        clawUpper = new DigitalInput(5);
        clawLower = new DigitalInput(7);
        armBack = new DigitalInput(3);
        
        m_Joystick = joystickone;
        m_Joystick2 = joysticktwo;
        m_analogstuff = analogstuff;

        addRequirements(m_lowerArm);
        addRequirements(m_upperArm);

    }
    @Override public void execute(){
        vertAxis = m_Joystick2.getY();
        if (m_analogstuff.getY() == 1){
            zAxis = 0.1;
        }else if (m_analogstuff.getY() == -1){
            zAxis = -0.1;
        }

        if (clawUpper.get()){
            vertAxis = -0.1;
        }else if (clawLower.get()){
            vertAxis = -0.1;
        }

        if (armBack.get()){
            zAxis = -0.1;
        }
            
        button3 = m_analogstuff.getRawButton(3);
        if (button3 != false){
            m_upperArm.upperencoderoutput();
        }
        m_upperArm.setSpeed(vertAxis);
        m_lowerArm.setSpeed(zAxis);

    }

}