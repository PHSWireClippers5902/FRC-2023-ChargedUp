// package frc.robot.commands;
// import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.RobotContainer;
// import frc.robot.subsystems.UltrasonicSystem;

// public class CheckDistance extends CommandBase {

//   private final UltrasonicSystem m_ultrasonicSystem;

//   public CheckDistance(UltrasonicSystem subSystem){
//     m_ultrasonicSystem = subSystem;
//     addRequirements(m_ultrasonicSystem);
//   }

//   @Override
//   public void execute() {
//     SmartDashboard.putNumber("Ultrasonic", m_ultrasonicSystem.getDistance());
//   }

//   @Override
//   public void end(boolean interrupted) {
//   }

//   @Override
//   public boolean isFinished(){
//       return false;
//     }
// }