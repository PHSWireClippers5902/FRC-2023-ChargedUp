package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LowerArm;
import frc.robot.subsystems.UpperArm;
import frc.robot.RobotContainer;
import frc.robot.subsystems.MecanumSystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;

public class ArmControlTests extends CommandBase{
    
    private final UpperArm m_upperArm;
    private final LowerArm m_lowerArm;
    
    private XboxController m_danxbox;
    private Joystick m_danJoystick;
    private Joystick m_danJoysticktwo;
    private Joystick m_analogstuff;
    
    public ArmControlTests(LowerArm lowerArm, UpperArm upperArm, XboxController xbox, Joystick joystickone, Joystick joysticktwo, Joystick analogstuff){
        
        m_upperArm = upperArm;
        m_lowerArm = lowerArm;
        m_danxbox = xbox;
        
        m_danJoystick = joystickone;
        m_danJoysticktwo = joysticktwo;
        m_analogstuff = analogstuff;

        addRequirements(m_lowerArm);
        addRequirements(m_upperArm);

    }

}
