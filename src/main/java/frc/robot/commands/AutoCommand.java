package frc.robot.commands;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
// import frc.robot.subsystems.FlyWheel;
// import frc.robot.subsystems.LinearActuator;
import frc.robot.subsystems.TankDrive;
import frc.robot.RobotContainer;

public class AutoCommand extends SequentialCommandGroup {
    private TankDrive m_tank;
    // private FlyWheel m_flyWheel;
    // private LinearActuator m_linearActuator;

    public AutoCommand(TankDrive tankSystem){

        //took out: ,FlyWheel flyWheelSystem, LinearActuator linearActuatorSystem
        m_tank = tankSystem;
        // m_flyWheel = flyWheelSystem;
        // m_linearActuator = linearActuatorSystem;

        addRequirements(tankSystem);


        //Adding Auto Commands
        //addCommands(new AutoDrive(m_mecanum, 0.2, 0, 0).withTimeout(1.4));
        //addCommands(new ParallelCommandGroup(new ActivateFlyWheel(1, m_flyWheel), new ActivateLinearActuator(1.0, m_linearActuator)).withTimeout(4));
        //addCommands(new ParallelCommandGroup(new ActivateLinearActuator(-1.0, m_linearActuator), new AutoDrive(m_mecanum, -0.2, 0, 0)).withTimeout(4));

    }

  }
