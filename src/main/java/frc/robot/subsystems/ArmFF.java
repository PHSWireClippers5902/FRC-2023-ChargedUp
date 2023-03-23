// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.Vector;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N2;
import edu.wpi.first.math.system.plant.DCMotor;
import static frc.robot.Constants.ArmConstants.*;
import static frc.robot.Constants.g;

//Thank you to the numinous minds of 449 for this whitepaper https://www.chiefdelphi.com/t/whitepaper-two-jointed-arm-dynamics/423060 and to 3467 for revealing this to me
public class ArmFF extends SubsystemBase {
    private static final double g = 9.80665;
    private final DCMotor lowerMotor;
    private final DCMotor upperMotor;


    public ArmFF(DCMotor lowerMotor, DCMotor upperMotor) {
      this.lowerMotor = lowerMotor;
      this.upperMotor = upperMotor;
    }
  
    //Calculuates voltage for motors based only on position
    public Vector<N2> feedforward(Vector<N2> position) {
      return feedforward(position, VecBuilder.fill(0.0, 0.0), VecBuilder.fill(0.0, 0.0));
    }
  
    public Vector<N2> feedforward(Vector<N2> position, Vector<N2> velocity, Vector<N2> acceleration) {
      var torque =
          M(position)
              .times(acceleration)
              .plus(C(position, velocity).times(velocity))
              .plus(Tg(position));
      return VecBuilder.fill(
          lowerMotor.getVoltage(torque.get(0, 0), velocity.get(0, 0)),
          upperMotor.getVoltage(torque.get(1, 0), velocity.get(1, 0)));
    }
  
  
    private Matrix<N2, N2> M(Vector<N2> position) {
      var M = new Matrix<>(N2.instance, N2.instance);
      M.set(
          0,
          0,
          lowerMass * Math.pow(lowerCenterGravityRadius, 2.0)
              + upperMass * (Math.pow(lowerLength, 2.0) + Math.pow(upperCenterGravityRadius, 2.0))
              + lowerMomentInertia
              + upperMomentInertia
              + 2
                  * upperMass
                  * lowerLength
                  * upperCenterGravityRadius
                  * Math.cos(position.get(1, 0)));
      M.set(
          1,
          0,
          upperMass * Math.pow(upperCenterGravityRadius, 2.0)
              + upperMomentInertia
              + upperMass * lowerLength * upperCenterGravityRadius * Math.cos(position.get(1, 0)));
      M.set(
          0,
          1,
          upperMass * Math.pow(upperCenterGravityRadius, 2.0)
              + upperMomentInertia
              + upperMass * lowerLength * upperCenterGravityRadius * Math.cos(position.get(1, 0)));
      M.set(1, 1, upperMass * Math.pow(upperCenterGravityRadius, 2.0) + upperMomentInertia);
      return M;
    }
  
    private Matrix<N2, N2> C(Vector<N2> position, Vector<N2> velocity) {
      var C = new Matrix<>(N2.instance, N2.instance);
      C.set(
          0,
          0,
          -upperMass
              * lowerLength
              * upperCenterGravityRadius
              * Math.sin(position.get(1, 0))
              * velocity.get(1, 0));
      C.set(
          1,
          0,
          upperMass
              * lowerLength
              * upperCenterGravityRadius
              * Math.sin(position.get(1, 0))
              * velocity.get(0, 0));
      C.set(
          0,
          1,
          -upperMass
              * lowerLength
              * upperCenterGravityRadius
              * Math.sin(position.get(1, 0))
              * (velocity.get(0, 0) + velocity.get(1, 0)));
      return C;
    }
  
    private Matrix<N2, N1> Tg(Vector<N2> position) {
      var Tg = new Matrix<>(N2.instance, N1.instance);
      Tg.set(
          0,
          0,
          (lowerMass * lowerCenterGravityRadius + upperMass * lowerLength)
                  * g
                  * Math.cos(position.get(0, 0))
              + upperMass
                  * upperCenterGravityRadius
                  * g
                  * Math.cos(position.get(0, 0) + position.get(1, 0)));
      Tg.set(
          1,
          0,
          upperMass * upperCenterGravityRadius * g * Math.cos(position.get(0, 0) + position.get(1, 0)));
      return Tg;
    }

}