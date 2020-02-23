/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision extends SubsystemBase {
  // The Network Table contains all values relevant to working with vision
  private NetworkTable mLimelightTable = NetworkTableInstance.getDefault().getTable(Constants.kLimelightNetworkID);
  // Network Table entry variables to hold data in the form of a NetworkTableEntry
  private NetworkTableEntry mTableX = mLimelightTable.getEntry(Constants.kLimelightTargetXID);
  private NetworkTableEntry mTableY = mLimelightTable.getEntry(Constants.kLimelightTargetYID);
  private NetworkTableEntry mTableSkew = mLimelightTable.getEntry(Constants.kLimelightTargetSkewID);
  private NetworkTableEntry mTableArea = mLimelightTable.getEntry(Constants.kLimelightTargetAreaID);
  private NetworkTableEntry mTableVert = mLimelightTable.getEntry(Constants.kLimelightTargetVertID);
  private NetworkTableEntry mTableHor = mLimelightTable.getEntry(Constants.kLimelightTargetHorID);
  //private NetworkTableEntry mTableCorners = mLimelightTable.getEntry("tcornx");
  private NetworkTableEntry mTableTargetFound = mLimelightTable.getEntry(Constants.kLimelightTargetID);
  
  // Variables to hold Network Table values in the form of Doubles
  private double mTargetX = 0;
  private double mTargetY = 0;
  private double mTargetSkew = 0;
  private double mTargetArea = 0;
  private double mTargetVert = 0;
  private double mTargetHor = 0;
  private double mTargetFound = 0;

  private boolean TargetPipeline = true;

  /**
   * Creates a new Vision.
   */
  public Vision() {
    mLimelightTable.getEntry("ledMode").setNumber(Constants.kLimelightLED);
    mLimelightTable.getEntry("camMode").setNumber(Constants.kLimelightMode);
    mLimelightTable.getEntry("stream").setNumber(Constants.kLimelightStream);
    mLimelightTable.getEntry("pipeline").setNumber(Constants.kLimelightStartingPipeline);
  }

  public double getTargetFound() {
    mTableTargetFound = mLimelightTable.getEntry(Constants.kLimelightTargetID);
    mTargetFound = mTableTargetFound.getDouble(0.0);
    return mTargetFound;
  }
  // Returns the horizontal offset from crosshair to target (+/- 27 degrees)
  public double getTargetX() {
    mTableX = mLimelightTable.getEntry(Constants.kLimelightTargetXID); 
    mTargetX = mTableX.getDouble(0.0); 
    return mTargetX; 
  }

  // Returns the vertical offset from crosshair to target (+/- 20.5 degrees)
  public double getTargetY() {
    mTableY = mLimelightTable.getEntry(Constants.kLimelightTargetYID);
    mTargetY = mTableY.getDouble(0.0);
    return mTargetY;
  }

  // Returns target area (0-100 % of image)
  public double getTargetArea() {
    mTableArea = mLimelightTable.getEntry(Constants.kLimelightTargetAreaID);
    mTargetArea = mTableArea.getDouble(0.0);
    return mTargetArea;
  }

  // Returns the target skew/rotation (-90 to 0 degrees)
  public double getTargetSkew() {
    mTableSkew = mLimelightTable.getEntry(Constants.kLimelightTargetSkewID);
    mTargetSkew = mTableSkew.getDouble(0.0);
    return mTargetSkew;
  }

  // Returns the vertical sidelength of the bounding box (0-320 pixels)
  public double getTargetVert() {
    mTableVert = mLimelightTable.getEntry(Constants.kLimelightTargetVertID);
    mTargetVert = mTableVert.getDouble(0.0);
    return mTargetVert;
  }

  // Returns the horizontal sidelength of the bounding box (0-320 pixels)
  public double getTargetHor() {
    mTableHor = mLimelightTable.getEntry(Constants.kLimelightTargetHorID);
    mTargetHor = mTableHor.getDouble(0.0);
    return mTargetHor;
  }

  public void SwitchToDriverPipeline(){
    mLimelightTable.getEntry("camMode").setNumber(1);
    mLimelightTable.getEntry("pipeline").setNumber(Constants.kLimelightDriverPipeline);
  }

  public void SwitchToTargetPipeline(){
    mLimelightTable.getEntry("camMode").setNumber(Constants.kLimelightMode);
    mLimelightTable.getEntry("pipeline").setNumber(Constants.kLimelightStartingPipeline);
  }

  public void SwitchPipeline(){
    if(TargetPipeline){
      mLimelightTable.getEntry("camMode").setNumber(1);
      mLimelightTable.getEntry("pipeline").setNumber(Constants.kLimelightDriverPipeline);
      TargetPipeline = false;
    } else {
      mLimelightTable.getEntry("camMode").setNumber(Constants.kLimelightMode);
      mLimelightTable.getEntry("pipeline").setNumber(Constants.kLimelightStartingPipeline);
      TargetPipeline = true;
    }
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // Puts all the vision numbers on the Shuffleboard
    SmartDashboard.putNumber("Target X", getTargetX());
    // SmartDashboard.putNumber("Target Y", getTargetY());
    // SmartDashboard.putNumber("Target Skew", getTargetSkew());
  }
}
