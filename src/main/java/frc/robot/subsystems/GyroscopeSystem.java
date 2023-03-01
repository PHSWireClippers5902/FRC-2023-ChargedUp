// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.lang.Math;

public class GyroscopeSystem extends SubsystemBase {
  /** Creates a new GyroscopeSystem. */

  AHRS m_gyroscope; //https://robotpy.readthedocs.io/projects/navx/en/stable/api.html is documentation I think - tim
  double Rolloffset, Pitchoffset, YawOffset;
  boolean isZeroed;

  public GyroscopeSystem() {
    m_gyroscope = new AHRS(I2C.Port.kMXP);
    isZeroed = false;
    SmartDashboard.putBoolean("isZeroed", isZeroed);
  }

  public void calibrate(){
    m_gyroscope.calibrate();
  }

  public double getTilt(char axis){ // will return value between -180 to 180 which is degrees  
    //https://pdocs.kauailabs.com/navx-mxp/guidance/terminology/ 
    switch(axis){
      case('y'):
        return m_gyroscope.getYaw()-YawOffset;// z positive is right rotation 
      case('r'):
        return m_gyroscope.getRoll()-Rolloffset; // y postive is left rotate
      case('p'):
        return m_gyroscope.getPitch()-Pitchoffset; // x postive is backward rotation 
      default:
        return 0;
    }
  }

  public boolean isTilted(char axis){ // check for if the tilt angle is enough to move the robo
    return Math.abs(getTilt(axis)) > 2.5;   
  }


  @Override
  public void periodic() {
    if ((!isZeroed) && (!m_gyroscope.isCalibrating())){ // checks to see if values have been zeroed if so creates the offsets
      isZeroed = true;
      m_gyroscope.zeroYaw();
      YawOffset = m_gyroscope.getYaw();
      Rolloffset = m_gyroscope.getRoll();
      Pitchoffset = m_gyroscope.getPitch();
    }
    // This method will be called once per scheduler run
    //System.out.println("GYro says: " + m_gyroscope.isConnected() + m_gyroscope.isCalibrating());
    SmartDashboard.putNumber("Yaw", getTilt('y')); // turning left right
    SmartDashboard.putNumber("Roll", getTilt('r')); //on side
    SmartDashboard.putNumber("Pitch", getTilt('p')); //pitch up and down
    SmartDashboard.putBoolean("isZeroed", isZeroed); //

  }
}
