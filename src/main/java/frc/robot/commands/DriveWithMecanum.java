package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
// import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.MecanumSystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;

public class DriveWithMecanum extends CommandBase {

  private final MecanumSystem m_mecanum;
  private XboxController m_danxbox;
  private Joystick m_danJoystick;
  private Joystick m_danJoysticktwo;
  private double vert;
  private double vertright;
  private double kidmode;
  private double adultmode;
  private double currmode;
  private boolean rightbump;
  private boolean leftbump;
  private boolean x;
  private double distance;
  private double horiz;
  private boolean trigger;
  private boolean thumb;
  private double Zaxis;
  private boolean one;
  private boolean init;
  private double vertfixed;
  private double Zaxisfixed;


  public DriveWithMecanum(MecanumSystem mecanumSystem, XboxController xbox, Joystick joystickone, Joystick joysticktwo){
    m_danxbox = xbox;
    m_mecanum = mecanumSystem;
    m_danJoystick = joystickone;
    m_danJoysticktwo = joysticktwo;
    kidmode = 0.3;
    adultmode = 0.7;
    
    thumb = false;

    addRequirements(m_mecanum);
  }

  @Override
  public void execute() {


    vert = m_danJoystick.getY();
    
    Zaxis = m_danJoystick.getZ();

    trigger = m_danJoystick.getRawButton(1);
    thumb = m_danJoystick.getRawButton(2);
    rightbump = m_danJoystick.getRawButton(3);
    leftbump = m_danJoystick.getRawButton(4);
    // vert = m_danxbox.getLeftY();
    // vertright = m_danxbox.getRightY();
    // rightbump = m_danxbox.getRightBumper();
    // leftbump = m_danxbox.getLeftBumper();


    //x = m_danxbox.getXButton();
    // if (x != false)
    // {
    //   distance = m_mecanum.
    //   System.out.println(distance);
    // }

    // if(rightbump != false)
    // {
    //   currmode = adultmode;
    // }

    // if(leftbump != false)
    // {
    //   currmode = kidmode;
    // }


    if ((vert>0.25)||(vert<-0.25))
    {
      vertfixed = vert;
    }
    else
    {
      vertfixed = 0;
    }

    if ((Zaxis>0.1)||(Zaxis<-0.1))
    {
      Zaxisfixed = Zaxis;
    }
    else
    {
      Zaxisfixed = 0;
    }
    
    if (trigger != false)
    {
      currmode = kidmode;
      init = true;
    }
    else if (init != false)
    {
      currmode = adultmode;
    }
    
    

    if (thumb != false)
    {
      m_mecanum.frontLeft(currmode*(-vert));
      m_mecanum.frontRight(currmode*(vert));
      m_mecanum.backLeft(currmode*(-vert));
      m_mecanum.backRight(currmode*(vert));    
    }
    else if (m_danJoystick.getRawButton(3) != false)
    {
      m_mecanum.frontLeft(-currmode*(-0.5*(Zaxisfixed)));
      m_mecanum.frontRight(currmode*(0.5*(Zaxisfixed)));
      m_mecanum.backLeft(-currmode*(0.5*(-Zaxisfixed)));
      m_mecanum.backRight(currmode*(0.5*(Zaxisfixed)));
    }
    else
    {
      m_mecanum.frontLeft(-currmode*(vertfixed-Zaxisfixed));
      m_mecanum.frontRight(currmode*(vertfixed+Zaxisfixed));
      m_mecanum.backLeft(-currmode*(vertfixed-Zaxisfixed));
      m_mecanum.backRight(currmode*(vertfixed+Zaxisfixed));
      // m_mecanum.frontLeft(currmode*(-vertright));
      // m_mecanum.frontRight(currmode*(vert));
      // m_mecanum.backLeft(currmode*(-vertright));
      // m_mecanum.backRight(currmode*(vert)); 
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_mecanum.frontLeft(0);
    m_mecanum.frontRight(0);
    m_mecanum.backLeft(0);
    m_mecanum.backRight(0);
  }
}
