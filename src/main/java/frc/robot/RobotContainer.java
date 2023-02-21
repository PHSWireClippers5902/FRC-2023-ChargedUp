package frc.robot;

// //Spark Imports
// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMaxLowLevel;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import static frc.robot.Constants.ControllerConstants;

import java.time.Instant;

import javax.sound.sampled.SourceDataLine;
import javax.swing.JToggleButton;

import static frc.robot.Constants.ClimbConstants;
import edu.wpi.first.wpilibj.AnalogInput;

public class RobotContainer {

    //Initializing Subsystems
    private ArmSystem m_arm = new ArmSystem();

    //Controls
    Joystick joystickone = new Joystick(2);
    Joystick joysticktwo = new Joystick(4);
    Joystick analogstuff = new Joystick(3);

  public RobotContainer(){
    //Default Commands
    configureButtonBindings();
  }
  


  private void configureButtonBindings(){ 
  }
}
