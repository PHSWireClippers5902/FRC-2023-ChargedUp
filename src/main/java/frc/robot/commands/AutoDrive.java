// package frc.robot.commands;
// import  edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.RobotContainer;
// import frc.robot.subsystems.FlyWheel;
// import frc.robot.subsystems.LinearActuator;
// import frc.robot.subsystems.MecanumSystem;
// import frc.robot.RobotContainer;

// public class AutoDrive extends CommandBase {
//     private MecanumSystem mecanumSystem;
//     double fowardX, lateralY, rotationalZ;
//     public AutoDrive(MecanumSystem subsSystem, double x, double y, double z){
//         fowardX = x;
//         lateralY = y;
//         rotationalZ = z;

//         mecanumSystem = subsSystem;
//         addRequirements(mecanumSystem);
//     }

//     @Override
//     public void execute() {
//         //mecanumSystem.drive(fowardX, lateralY, rotationalZ);
//     }
//     @Override
//     public void end(boolean interupted){
//         //mecanumSystem.drive(0, 0, 0);
//     }
//   }