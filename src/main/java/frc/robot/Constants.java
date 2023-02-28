package frc.robot;

public final class Constants{
    public static final double g = 9.80665;
    public static final class VisionConstants{
        public static final double Targetheight = 40.5;
        public static final double Lightheight = 27;
        public static final double AngleOffset = -4.82;
        public static final double RangeThreshhold = 100;

    }
  public static final class WheelConstants{
      public static final int frontLeftMotorPort = 1;
      public static final int backLeftMotorPort = 2;
      public static final int frontRightMotorPort = 4;
      public static final int backRightMotorPort = 3;
  }
  public static final class ArmConstants{
      public static final int LowerArmMotorOnePort = 5; 
      public static final int LowerArmMotorTwoPort = 6;
      public static final int UpperArmMotorPort = 7;
      public static final int upperArmTopSwitchPort = 5;
      public static final int upperArmBottomSwitchPort = 7;
    //   public static final int lowerArmTopSwitchPort = 2;
      public static final int lowerArmSwitchPort = 3;
      public static final double UpperRange = 20;
      public static final double LowerRange = 25;
      
      public static final double LowerArmMassOne = 0.15;
      public static final double LowerArmMassTwo = 1.51;
      public static final double LowerArmMassThree = 0.984;
      public static final double LowerArmMassFour = 0.5;

      public static final double LowerArmLength = 0.6795;

      public static final double LowerArmLengthOne = 0.114;
      public static final double LowerArmLengthTwo = 0.178;
      public static final double LowerArmLengthThree = LowerArmLength/2;


      public static final double UpperArmMass = .713;
      public static final double ClawMass = .98;
      public static final double UpperArmLength = 0.889;

  }
  public static final class ClimbConstants{
    public static final int climbMotorPort = 10;
    public static final double P = 0.3; 
    public static final double I = 1e-4;
    public static final double D = 0; 
    public static final double Iz = 0; 
    public static final double FF = 0; 
    public static final double MaxOutput = .2; 
    public static final double MinOutput = -.2;
    public static final float MotorUpPosition = 57;
    public static final float MotorDownPosition = 0;
    //Don't use this maybe??? who knowwss? Cruunch
    //public static final double PostionConversionFactor = 2;
}
  public static final class ControllerConstants {
      public static final int A = 1;
      public static final int B = 2;
      public static final int X = 3;
      public static final int Y = 4;
      public static final int LeftButton = 5;
      public static final int RightButton = 6;
      public static final int LinkButton = 7;
      public static final int ControllerPort = 0;
  }
  public static final class FlyWheelConstants{
      public static final int motorOnePort = 7;
      public static final int motorTwoPort = 8;
  }
  public static final class LinearActuatorConstants{
      public static final int ServoChannel = 1;    
  }
  public static final class ServoConstants{
      public static final int HookServoPort = 0;
      public static final int BallServoPort = 2;
  }
}