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

import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import static frc.robot.Constants.ControllerConstants;

import java.time.Instant;

import javax.sound.sampled.SourceDataLine;

import static frc.robot.Constants.AimConstants;
import static frc.robot.Constants.ClimbConstants;
import edu.wpi.first.wpilibj.AnalogInput;

public class RobotContainer {

    //initializing subsystems(
    public final MecanumSystem m_mecanumSystem = new MecanumSystem();
    private final FlyWheel m_flyWheelSystem = new FlyWheel();
    private final LinearActuator m_linearActuator = new LinearActuator();
    private final UltrasonicSystem m_UltrasonicSystem = new UltrasonicSystem();
    public final AimSystem m_aimSystem = new AimSystem();
    public final ClimbSystem m_climbSystem = new ClimbSystem();
    public final ServoSystem m_servo = new ServoSystem();
    public final LimelightSystem limelight = new LimelightSystem();
    public final testNeo NeoMotor = new testNeo();
    public final AccelerometerSystem m_accel = new AccelerometerSystem();

    // public final AutoCommand autoCommand = new AutoCommand(m_mecanumSystem, m_flyWheelSystem, m_linearActuator);
    // public final ParallelCommandGroup autoshoot = new ParallelCommandGroup(new ActivateLinearActuator(1.0, m_linearActuator),
    // // new ActivateFlyWheel(0.75, m_flyWheelSystem));
    // private final StartEndCommand aimUpDownCommand = new StartEndCommand(
    //   () -> {m_aimSystem.aimTo(AimConstants.MotorDownPosition);},
    //   () -> {m_aimSystem.aimTo(AimConstants.MotorUpPosition);},
    //   m_aimSystem);
  
    // Xbox
    XboxController xbox = new XboxController(ControllerConstants.ControllerPort);

    //AimRobot drive = new AimRobot(m_mecanumSystem, limelight);
  public RobotContainer(){
    //Default Commands
    configureButtonBindings();
    //m_flyWheelSystem.setDefaultCommand(new ActivateFlyWheel(-.1, m_flyWheelSystem));
    //m_mecanumSystem.setDefaultCommand(drive);
    //m_UltrasonicSystem.setDefaultCommand(new CheckDistance(m_UltrasonicSystem));
    new InstantCommand(() -> {m_accel.calibrate();}).schedule();
  }
  


  private void configureButtonBindings(){ 
    //Locks Climb
    new JoystickButton(xbox, ControllerConstants.A)
    .whenPressed(new InstantCommand(()->{limelight.setPipeline((limelight.getPipeline()+1)%4);System.out.println("code run");}, limelight));
    // new JoystickButton(xbox, ControllerConstants.A)
    // .whileHeld(new StartEndCommand(() -> {NeoMotor.setSpeed(.3);}, () -> {NeoMotor.setSpeed(0);}, NeoMotor));

    // new JoystickButton(xbox, ControllerConstants.B)
    // .whileHeld(new StartEndCommand(() -> {NeoMotor.setSpeed(-.1);}, () -> {NeoMotor.setSpeed(0);}, NeoMotor));

    // new JoystickButton(xbox, ControllerConstants.X)
    // .whileHeld(new StartEndCommand(() -> {NeoMotor.setSpeed(.4);}, () -> {NeoMotor.setSpeed(0);}, NeoMotor));
    // // new JoystickButton(xbox, ControllerConstants.X)
    // .whenPressed(() -> {m_servo.changeHookServo(35);}, m_servo)
    // .cancelWhenPressed(aimUpDownCommand);
  

    //Ball Collection Hook
    // new JoystickButton(xbox, ControllerConstants.A)
    //.whenPressed(new InstantCommand(() -> {m_aimSystem.resetPosition();}, m_aimSystem));
    //.whenPressed(new InstantCommand(() -> {m_aimSystem.aimTo(0);}, m_aimSystem));
    // .whenHeld(new StartEndCommand(
    // () -> {m_servo.changeBallServo(90);},
    // () -> {m_servo.changeBallServo(30);},
    //   m_servo));
    // //.whenPressed(new InstantCommand(() -> {m_servo.changeBallServo(0);}, m_servo))
    //.whenReleased(new InstantCommand(() -> {m_servo.changeBallServo(90);}, m_servo));//sets angle to 90 this value will be reset to zero after called.

    //Shoots Ball
    // new JoystickButton(xbox, ControllerConstants.Y)
    // .whileHeld(new ActivateLinearActuator(1.0, m_linearActuator))
    // .whenReleased(new ActivateLinearActuator(-1.0, m_linearActuator))
    // .whenHeld(new ActivateFlyWheel(1.0, m_flyWheelSystem));

    //Aim
    // new JoystickButton(xbox, ControllerConstants.B)
    // .toggleWhenPressed(aimUpDownCommand)
    // .toggleWhenPressed(new StartEndCommand(
    //   () -> {m_flyWheelSystem.speed(-0.1);}, 
    //   () -> {m_flyWheelSystem.speed(0.0);},
    //    m_flyWheelSystem));
    
    //I hate this. I hate programming in pit. AAHH
    // //Sometimes the robot gets confused about what angle aim system is at. When this happens, smash the cannon against the ground and press this button
    // new JoystickButton(xbox, ControllerConstants.LinkButton)
    // .whenPressed(() -> {m_aimSystem.setPosition(0.5);}, m_aimSystem)
    // .whenPressed(aimUpDownCommand);

    
    // //Climb arm
    // new JoystickButton(xbox, ControllerConstants.LeftButton)
    // .toggleWhenPressed(new StartEndCommand(
    //   () -> {m_climbSystem.moveTo(ClimbConstants.MotorUpPosition);},
    //   () -> {m_climbSystem.moveTo(ClimbConstants.MotorDownPosition);},
    //   m_climbSystem));


      //move 
      // new JoystickButton(xbox,ControllerConstants.RightButton)
      // .whileHeld(startWheels);
  }

  public XboxController getXbox() {
    return xbox;
  }
}
