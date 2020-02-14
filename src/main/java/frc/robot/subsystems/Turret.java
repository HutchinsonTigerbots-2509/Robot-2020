/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class Turret extends SubsystemBase {
  
  // private final VictorSP TurretMotor = new VictorSP(Constants.kTurretMotorID);
  private final WPI_TalonSRX TurretMotor = new WPI_TalonSRX(Constants.kTurretMotorID);
  private final AnalogInput TurretLimit = new AnalogInput(Constants.kTurretLimitID);
  private boolean LeftLimit = false;
  private boolean RightLimit = false;
  private boolean Direction; // True = Right, false = left

  // private final DigitalInput LeftLimit = new DigitalInput(1);
  // private final DigitalInput RightLimit = new DigitalInput(0);
  /**
   * Creates a new Turret.
   */
  public Turret() {
    TurretMotor.setNeutralMode(NeutralMode.Brake);
    // TurretMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    // TurretMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    GetLimitValue();

    SmartDashboard.putBoolean("Left Limit", LeftLimit);
    SmartDashboard.putBoolean("Right Limit", RightLimit);
    // SmartDashboard.putBoolean("Direction", Direction);
    // SmartDashboard.putNumber("Turret Limit", TurretLimit.getVoltage());

    if(RobotContainer.CoOpStick.getRawAxis(4) < -0.2 && !LeftLimit){
      TurnLeft(0.5);
      UpdateDirection(false); // True = Right, false = left
    } else if (RobotContainer.CoOpStick.getRawAxis(4) > 0.2 && !RightLimit){
      TurnRight(0.5);
      UpdateDirection(true); // True = Right, false = left
    } else {
      StopTurretMotor();
    }
  }

  public void TurnLeft(double pSpeed) {
    if(LeftLimit) {
      TurretMotor.set(0.0);
    }
    else {
      TurretMotor.set(pSpeed);
      UpdateDirection(false); // True = Right, false = left
    }
  }

  public void TurnRight(double pSpeed){
    if(RightLimit){
      TurretMotor.set(0.0);
    } else {
      TurretMotor.set(-pSpeed);
      UpdateDirection(true); // True = Right, false = left
    }
  }

  public void StopTurretMotor(){
    TurretMotor.set(0);
  }

  // Updates the Direction of the turret motor if the limit switch is not triggered
  // True = Right, false = left
  private void UpdateDirection(boolean pDirection){
    if(!LeftLimit && !RightLimit){
      Direction = pDirection;
    }
  }

  private void GetLimitValue(){
    if(TurretLimit.getVoltage() > 2 && Direction == false){
      LeftLimit = true;
    } else if(TurretLimit.getVoltage() > 2 && Direction == true){
      RightLimit = true;
    } else {
      LeftLimit = false;
      RightLimit = false;
    }
  }

  
  // public boolean GetLeftLimit(){
  //   return LeftLimit.get();
  // }

  // public boolean GetRightLimit(){
  //   return RightLimit.get();
  // }
}
