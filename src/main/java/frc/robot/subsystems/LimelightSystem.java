package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.Constants.VisionConstants;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import java.lang.Math;

public class LimelightSystem extends SubsystemBase{
    private double x, y, area, distance, Hastarget, tagID, pipeline;
    private NetworkTable limelightTable;
    private NetworkTableEntry tx, ty, ta, tv, tid, getpipe, setpipe;
    public LimelightSystem(){
        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
        tx = limelightTable.getEntry("tx");
        ty = limelightTable.getEntry("ty");
        ta = limelightTable.getEntry("ta");
        tv = limelightTable.getEntry("tv");
        tid = limelightTable.getEntry("tid");
        getpipe = limelightTable.getEntry("getpipe");
        setpipe = limelightTable.getEntry("pipeline");
        setPipeline(0);
    }
    public double getX(){return x;}
    public double getY(){return y;}
    public double getDistance(){return distance;}
    // public boolean hasTarget(){return (area>0.05);}
    public double hasTarget(){return Hastarget;}
    public double getTagID() {return tagID;}
    public double getPipeline(){return pipeline;}
    public void setPipeline(double pipelineID){setpipe.setNumber(pipelineID);}

    @Override
    public void periodic(){
        x = tx.getDouble(99);
        y = ty.getDouble(99);
        area = ta.getDouble(99);
        Hastarget = tv.getDouble(-1);
        tagID = tid.getDouble(99);
        pipeline = getpipe.getDouble(-1);
        distance = (Constants.VisionConstants.Targetheight-Constants.VisionConstants.Lightheight)/Math.tan(Math.toRadians(y+VisionConstants.AngleOffset));
       // SmartDashboard.putNumber("X", x);
        //SmartDashboard.putNumber("Y", y);
        //SmartDashboard.putNumber("Target", hasTarget());
        //SmartDashboard.putNumber("Distance", distance);
    }


} 
