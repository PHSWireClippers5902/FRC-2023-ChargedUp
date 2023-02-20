// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class pneumaticBoard extends SubsystemBase {
  /** Creates a new pneumaticBoard. */


  Solenoid exampleSingle = new Solenoid(20, PneumaticsModuleType.CTREPCM, 0);
  Compressor pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
  

  public void TrueSolenoid(){
   
    // Initialize the Solenoid so it knows where to start.  Not required for single solenoids.
    exampleSingle.set(true); 
  }

  public void FalseSolenoid(){
    exampleSingle.set(false);
  }
}