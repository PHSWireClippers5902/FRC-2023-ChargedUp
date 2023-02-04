package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.RobotController;

import edu.wpi.first.wpilibj.AnalogInput;

public class UltrasonicSystem extends SubsystemBase{

    double rawValue;
    double voltage_scale_factor;
    double currentDistanceCentimeters;

    public static AnalogInput ultrasonic = new AnalogInput(0);

    public double getDistance(){
        rawValue = ultrasonic.getValue();
        voltage_scale_factor = 5/RobotController.getVoltage5V();
        currentDistanceCentimeters = rawValue * voltage_scale_factor * 0.125;
        return currentDistanceCentimeters;
    }
}