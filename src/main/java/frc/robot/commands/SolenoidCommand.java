package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

import frc.robot.subsystems.PneumaticBoard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


public class SolenoidCommand extends CommandBase {

  private final PneumaticBoard m_pneumatics;
  private Joystick m_controller;
  private CommandXboxController m_box;
  public SolenoidCommand(PneumaticBoard pneumatics, Joystick analogstuff, CommandXboxController controller){
    m_pneumatics = pneumatics;
    m_controller = analogstuff;
    m_box = controller;
    addRequirements(m_pneumatics);
  }

  @Override
  public void initialize() {
    
  }
  @Override
  public void execute() {
    
    if(m_box.leftStick().getAsBoolean()){
         m_pneumatics.TrueSolenoid();
     }
     else if(m_box.rightStick().getAsBoolean()){
       m_pneumatics.FalseSolenoid();
     }
  }
}