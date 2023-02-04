package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.LinearActuator;
import frc.robot.RobotContainer;

public class ActivateLinearActuator extends CommandBase {
    public final LinearActuator m_linearActuator;
    public double amount;

    public ActivateLinearActuator(double amount, LinearActuator subSystem){
        m_linearActuator = subSystem;
        addRequirements(m_linearActuator);
        this.amount = amount;

    }
    @Override
    public void execute() {
        m_linearActuator.change(amount);
        //System.out.println("gogogooooogogo" + amount);
    }
    @Override
    public boolean isFinished(){
        return false;
    }

    
  }
