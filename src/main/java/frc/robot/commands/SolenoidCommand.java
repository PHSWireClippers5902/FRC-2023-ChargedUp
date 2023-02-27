package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

import frc.robot.subsystems.PneumaticBoard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;


public class SolenoidCommand extends CommandBase {

  private final PneumaticBoard m_pneumatics;
  private Joystick m_controller;
  public SolenoidCommand(PneumaticBoard pneumatics, Joystick analogstuff){
    m_pneumatics = pneumatics;
    m_controller = analogstuff;
    addRequirements(m_pneumatics);
  }

  @Override
  public void initialize() {
    
  }
  @Override
  public void execute() {
    
    if(m_controller.getRawButton(1)){
         m_pneumatics.TrueSolenoid();
     }
     else if(m_controller.getRawButton(2)){
       m_pneumatics.FalseSolenoid();
     }
  }
}