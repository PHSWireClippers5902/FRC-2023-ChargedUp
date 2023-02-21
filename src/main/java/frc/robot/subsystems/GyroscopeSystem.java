// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GyroscopeSystem extends SubsystemBase {
  /** Creates a new GyroscopeSystem. */

  AHRS m_gyroscope; //https://robotpy.readthedocs.io/projects/navx/en/stable/api.html is documentation I think - tim

  public GyroscopeSystem() {
    AHRS m_gyroscope = new AHRS(SPI.Port.kMXP); 
  }

  public void calibrate(){
    m_gyroscope.calibrate();
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
