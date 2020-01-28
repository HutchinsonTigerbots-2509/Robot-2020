/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.VariableVault;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision extends SubsystemBase {
  // The Network Table contains all values relevant to working with vision
  private NetworkTable mLimelightTable = NetworkTableInstance.getDefault().getTable(VariableVault.kLimelightNetworkID);
  // Network Table entry variables to hold data in the form of a NetworkTableEntry
  private NetworkTableEntry mTableX = mLimelightTable.getEntry(VariableVault.kLimelightTargetXID);
  private NetworkTableEntry mTableY = mLimelightTable.getEntry(VariableVault.kLimelightTargetYID);
  private NetworkTableEntry mTableSkew = mLimelightTable.getEntry(VariableVault.kLimelightTargetSkewID);
  private NetworkTableEntry mTableArea = mLimelightTable.getEntry(VariableVault.kLimelightTargetAreaID);
  private NetworkTableEntry mTableVert = mLimelightTable.getEntry(VariableVault.kLimelightTargetVertID);
  private NetworkTableEntry mTableHor = mLimelightTable.getEntry(VariableVault.kLimelightTargetHorID);
  //private NetworkTableEntry mTableCorners = mLimelightTable.getEntry("tcornx");
  // private NetworkTableEntry mTableTargetFound = mLimelightTable.getEntry(VariableVault.kLimelightTargetID);
  
  // Variables to hold Network Table values in the form of Doubles
  private double mTargetX = 0;
  private double mTargetY = 0;
  private double mTargetSkew = 0;
  private double mTargetArea = 0;
  private double mTargetVert = 0;
  private double mTargetHor = 0;
  // private double mTargetFound = 0;

  /**
   * Creates a new Vision.
   */
  public Vision() {
    mLimelightTable.getEntry("ledMode").setNumber(VariableVault.kLimelightLED);
    mLimelightTable.getEntry("camMode").setNumber(VariableVault.kLimelightMode);
    mLimelightTable.getEntry("stream").setNumber(VariableVault.kLimelightStream);
    mLimelightTable.getEntry("pipeline").setNumber(VariableVault.kLimelightStartingPipeline);
    SmartDashboard.putNumber("here",2);
  }

  // Returns the horizontal offset from crosshair to target (+/- 27 degrees)
  public double getTargetX() {
    mTableX = mLimelightTable.getEntry(VariableVault.kLimelightTargetXID); 
    mTargetX = mTableX.getDouble(0.0); 
    return mTargetX; 
  }

  // Returns the vertical offset from crosshair to target (+/- 20.5 degrees)
  public double getTargetY() {
    mTableY = mLimelightTable.getEntry(VariableVault.kLimelightTargetYID);
    mTargetY = mTableY.getDouble(0.0);
    return mTargetY;
  }

  // Returns target area (0-100 % of image)
  public double getTargetArea() {
    mTableArea = mLimelightTable.getEntry(VariableVault.kLimelightTargetAreaID);
    mTargetArea = mTableArea.getDouble(0.0);
    return mTargetArea;
  }

  // Returns the target skew/rotation (-90 to 0 degrees)
  public double getTargetSkew() {
    mTableSkew = mLimelightTable.getEntry(VariableVault.kLimelightTargetSkewID);
    mTargetSkew = mTableSkew.getDouble(0.0);
    return mTargetSkew;
  }

  // Returns the vertical sidelength of the bounding box (0-320 pixels)
  public double getTargetVert() {
    mTableVert = mLimelightTable.getEntry(VariableVault.kLimelightTargetVertID);
    mTargetVert = mTableVert.getDouble(0.0);
    return mTargetVert;
  }

  // Returns the horizontal sidelength of the bounding box (0-320 pixels)
  public double getTargetHor() {
    mTableHor = mLimelightTable.getEntry(VariableVault.kLimelightTargetHorID);
    mTargetHor = mTableHor.getDouble(0.0);
    return mTargetHor;
  }

  // Returns the estimated distance from the target in inches
  // Uses the formula D=177*(Target Area)^-0.56
  public double getEstimatedDistance() {
    return (177 * Math.pow(getTargetArea(), -0.56));
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // Puts all the vision numbers on the Shuffleboard
    SmartDashboard.putNumber("Target X", getTargetX());
    SmartDashboard.putNumber("Target Y", getTargetY());
    SmartDashboard.putNumber("Target Area", getTargetArea());
    SmartDashboard.putNumber("Target Skew", getTargetSkew());
    SmartDashboard.putNumber("Target Vert", getTargetVert());
    SmartDashboard.putNumber("Target Hor", getTargetHor());  
    SmartDashboard.putNumber("Estimated Distance", getEstimatedDistance());
  }
}
