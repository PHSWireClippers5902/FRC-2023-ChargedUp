package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;
import frc.robot.subsystems.PneumaticBoard;
import frc.robot.subsystems.TankDrive;
// import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.TankDrive;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import javax.lang.model.util.ElementFilter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoCommand extends SequentialCommandGroup {
  TankDrive driveSystem;
  PneumaticBoard pneumaticSystem;
  ParallelCommandGroup autoGroup;

  public AutoCommand(TankDrive tankSystem, PneumaticBoard pneumaticSystem){
    driveSystem = tankSystem;
    this.pneumaticSystem = pneumaticSystem;

    addRequirements(driveSystem, this.pneumaticSystem);

    addCommands(
      new InstantCommand(() -> {pneumaticSystem.TrueSolenoid();}, pneumaticSystem),
      new RunCommand(() -> {driveSystem.drive(-.2, -.2);}, driveSystem).withTimeout(5)
    );
  }
}