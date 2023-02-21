package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LowerArm;
import frc.robot.subsystems.UpperArm;
import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;

public class ArmControlTests extends CommandBase{
    
    private final UpperArm m_upperArm;
    private final LowerArm m_lowerArm;
    
    private XboxController m_xbox;
    private Joystick m_Joystick;
    private Joystick m_Joystick2;
    private Joystick m_analogstuff;
    private double vertAxis;
    private int povForward;
    private int povBackward;
    private boolean button3;
    public ArmControlTests(LowerArm lowerArm, UpperArm upperArm, XboxController xbox, Joystick joystickone, Joystick joysticktwo, Joystick analogstuff){
        
        m_upperArm = upperArm;
        m_lowerArm = lowerArm;
        m_xbox = xbox;
        
        m_Joystick = joystickone;
        m_Joystick2 = joysticktwo;
        m_analogstuff = analogstuff;

        addRequirements(m_lowerArm);
        addRequirements(m_upperArm);

    }
    @Override public void execute(){
        vertAxis = m_Joystick2.getY();
        
        button3 = m_analogstuff.getRawButton(3);
        if (button3 != false){
            m_upperArm.upperencoderoutput();
        }
    }

}