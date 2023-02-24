// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.lang.Math;

import javax.sound.sampled.SourceDataLine;

public class GyroscopeSystem extends SubsystemBase {
  /** Creates a new GyroscopeSystem. */

  AHRS m_gyroscope; //https://robotpy.readthedocs.io/projects/navx/en/stable/api.html is documentation I think - tim

  public GyroscopeSystem() {
    m_gyroscope = new AHRS(I2C.Port.kMXP); 
    m_gyroscope.reset();
  }

  public void calibrate(){
    m_gyroscope.calibrate();
  }

  public float getTilt(char axis){ // will return value between -180 to 180 which is degrees  
    //https://pdocs.kauailabs.com/navx-mxp/guidance/terminology/ 
    switch(axis){
      case('z'):
        return m_gyroscope.getYaw();// z positive is right rotation 
      case('y'):
        return m_gyroscope.getRoll(); // y postive is left rotate
      case('x'):
        return m_gyroscope.getPitch(); // x postive is backward rotation 
      default:
        return 0;
    }
  }

  public boolean isTilted(char axis){
    return Math.abs(getTilt(axis)) < 7;   
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.println("GYro says: " + m_gyroscope.isConnected() + m_gyroscope.isCalibrating());
    SmartDashboard.putNumber("Yaw(z)", getTilt('z')); // forward back tilt
    SmartDashboard.putNumber("Roll(y)", getTilt('y')); //left right tilt
    SmartDashboard.putNumber("Pitch(x)", getTilt('x')); //
  }
}
