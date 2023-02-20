package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

import frc.robot.subsystems.pneumaticBoard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;


public class SolenoidCommand extends CommandBase {

  private final pneumaticBoard m_pneumatics;
  private Joystick m_danxbox;
  public SolenoidCommand(pneumaticBoard pneumatics, Joystick analogstuff){
    m_pneumatics = pneumatics;
    m_danxbox = analogstuff;
    addRequirements(m_pneumatics);
  }

  @Override
  public void initialize() {
    
  }
  @Override
  public void execute() {
    
    if(m_danxbox.getRawButton(1)){
        m_pneumatics.TrueSolenoid();
    }
    else if(m_danxbox.getRawButton(2)){
      m_pneumatics.FalseSolenoid();
    }
  }
}
