package frc.robot;

//Spark Imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Ultrasonic;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import static frc.robot.Constants.ControllerConstants;
import static frc.robot.Constants.AimConstants;
import static frc.robot.Constants.ClimbConstants;
import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.subsystems.GyroscopeSystem;
import frc.robot.commands.AutoBalance;
//import frc.robot.subsystems.AccelerometerSystem;

public class RobotContainer {

    //initializing subsystems
    public final TankDrive m_tankSystem = new TankDrive();
    public final PneumaticBoard m_pneumatic = new PneumaticBoard();
    public final GyroscopeSystem m_gyro = new GyroscopeSystem();
    public final ArmSystem m_arm = new ArmSystem();

    //Controller
    XboxController xbox = new XboxController(ControllerConstants.ControllerPort);
    Joystick joystickone = new Joystick(2);
    Joystick joysticktwo = new Joystick(1);
    Joystick analogstuff = new Joystick(0);
    
    //Commands
    public final DriveWithTank m_TeleDrive = new DriveWithTank(m_tankSystem, xbox, joystickone, joysticktwo);
    public final SolenoidCommand m_pneumaticControl = new SolenoidCommand(m_pneumatic, analogstuff, xbox);
    public final AutoBalance m_autoBalance = new AutoBalance(m_gyro, m_tankSystem);
    public final MoveArmVelocity m_velocity = new MoveArmVelocity(joystickone, joysticktwo, xbox, m_arm);
    public AutoCommand m_auto = new AutoCommand(m_tankSystem, m_pneumatic);
  
  //Default Constructor
  public RobotContainer(){
    m_gyro.calibrate();
    configureButtonBindings();

    //Default Commands
    m_tankSystem.setDefaultCommand(m_TeleDrive);
    m_pneumatic.setDefaultCommand(m_pneumaticControl);
    m_arm.setDefaultCommand(m_velocity);
  }

  private void configureButtonBindings(){ 
    
    new JoystickButton(analogstuff, 1)
    .toggleWhenPressed(m_pneumaticControl);
    
    // new JoystickButton(joystickone,5)
    // .whenPressed(m_autoBalance);

    // new JoystickButton(xbox, 3)
    // .whenPressed(m_autoBalance);
  }

  public Joystick getJoystick() {
    return joystickone;
  }

  public XboxController getXbox() {
    return xbox;
  }

}