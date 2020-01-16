/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import frc.robot.RobotContainer;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Turret extends SubsystemBase {
  
  private final VictorSP TurretMotor = new VictorSP(Constants.kTurretMotorID);
  /**
   * Creates a new Turret.
   */
  public Turret() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // if(RobotContainer.OpStick.getRawAxis(4) < -0.5){
    //   TurnLeft(0.15);
    // } else if (RobotContainer.OpStick.getRawAxis(4) > 0.5){
    //   TurnRight(0.15);
    // } else {
    //   StopMotor();
    // }
  }

  public void TurnLeft(double pSpeed) {
    TurretMotor.set(pSpeed);
  }

  public void TurnRight(double pSpeed){
    TurretMotor.set(-pSpeed);
  }

  public void StopMotor(){
    TurretMotor.set(0);
  }
}
