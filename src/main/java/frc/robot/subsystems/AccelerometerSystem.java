// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj.BuiltInAccelerometer;
// import edu.wpi.first.wpilibj.interfaces.Accelerometer;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import java.lang.Math;

// import edu.wpi.first.math.filter.LinearFilter;

// public class AccelerometerSystem extends SubsystemBase {
//   /** Creates a new accelSystem. */
//   private Accelerometer accel;
//   private LinearFilter AccelFilterX;
//   private LinearFilter AccelFilterY;
//   private LinearFilter AccelFilterZ;
//   //AccelFilterY = movingave(100)
//   //..
//   private int filterSize;
//   private double xOffSet;
//   private double yOffSet;
//   private double zOffSet;
//   private char verticalAxis;
//   private double tiltThreshold = .2;

//   public AccelerometerSystem(char Axis, int filterSize) { 
//     accel = new BuiltInAccelerometer();
//     verticalAxis = Axis;
//     this.filterSize = filterSize;
//     AccelFilterX = LinearFilter.movingAverage(filterSize);
//     AccelFilterY = LinearFilter.movingAverage(filterSize);
//     AccelFilterZ = LinearFilter.movingAverage(filterSize);
//   }

//   public void updateValues(){
//     AccelFilterX.calculate(accel.getX());
//     AccelFilterY.calculate(accel.getY());
//     AccelFilterZ.calculate(accel.getZ());
//   }
//   //periodic{}
//   //filterx.calc(accel.getx())
//   //y, z...
  
//   //get x {return filterx.calculate(accel.getx())}
//   //get y
//   //get z
//   public void calibrate(){
//     for (int i = 0; i<filterSize; i++){
//       updateValues();
//     }

//     xOffSet = getAccel('x');
//     //xoffset = getx
//     yOffSet = getAccel('y');
//     //y...
//     zOffSet = getAccel('z');
    
//   } 
  
//   public boolean isTilted(char axis){
//     switch(axis){
//       case('x'):
//         return (Math.abs(accel.getX()-xOffSet) > tiltThreshold);
//       case('y'):
//         return (Math.abs(accel.getY()-yOffSet) > tiltThreshold);
//       case('z'):
//         return (Math.abs(accel.getZ()-zOffSet) > tiltThreshold);
//       default:
//       return false;
//     }
//   }
//   //@overload
//   public boolean isTilted(){
//     return isTilted(verticalAxis);
//   }
//   //istilt{
//   //return getx-offset > .05
//   //}
//   private double getAccel(char a){
//     switch(a){
//       case('z'):
//         return (AccelFilterZ.calculate(accel.getZ()));
//       case('x'):
//         return (AccelFilterX.calculate(accel.getX()));
//       case('y'):
//         return (AccelFilterY.calculate(accel.getY()));
//       default:
//         return 0;

//     }
//   }

//   @Override
//   public void periodic() {
//     updateValues();
//     SmartDashboard.putNumber("X Accel Zero", getAccel('x') - xOffSet);
//     SmartDashboard.putNumber("Y Accel Zero", getAccel('y') - yOffSet);
//     SmartDashboard.putNumber("Z Accel Zero", getAccel('z') - zOffSet);
//     SmartDashboard.putNumber("X Accel", getAccel('x'));
//     SmartDashboard.putNumber("Y Accel", getAccel('y'));
//     SmartDashboard.putNumber("Z Accel", getAccel('z'));
//     System.out.println("X: " + accel.getX() + " Y: " + accel.getY() + " Z: " + accel.getZ());
//     // This method will be called once per scheduler run
//   }
// }
