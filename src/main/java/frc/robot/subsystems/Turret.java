/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class Turret extends SubsystemBase {
  
  // private final VictorSP TurretMotor = new VictorSP(Constants.kTurretMotorID);
  private final WPI_TalonSRX TurretMotor = new WPI_TalonSRX(Constants.kTurretMotorID);
  private final DigitalInput LeftLimit = new DigitalInput(1);
  private final DigitalInput RightLimit = new DigitalInput(0);
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
    if(RobotContainer.CoOpStick.getRawAxis(4) < -0.2){
      TurnLeft(0.5);
    } else if (RobotContainer.CoOpStick.getRawAxis(4) > 0.2){
      TurnRight(0.5);
    } else {
      StopMotor();
    }
  }

  public boolean GetLeftLimit(){
    return LeftLimit.get();
  }

  public boolean GetRightLimit(){
    return RightLimit.get();
  }

  public void TurnLeft(double pSpeed) {
    if(LeftLimit.get()) {
      TurretMotor.set(0.0);
    }
    else {
      TurretMotor.set(pSpeed);
    }
  }

  public void TurnRight(double pSpeed){
    if(RightLimit.get()){
      TurretMotor.set(0.0);
    } else {
      TurretMotor.set(-pSpeed);
    }
  }

  public void StopMotor(){
    TurretMotor.set(0);
  }
}
