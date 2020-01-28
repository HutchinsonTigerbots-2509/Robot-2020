/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.VariableVault;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {

  private static WPI_TalonSRX ClimbMotor1 = new WPI_TalonSRX(VariableVault.kClimbMotor1ID);
  private static WPI_TalonSRX ClimbMotor2 = new WPI_TalonSRX(VariableVault.kClimbMotor2ID);
  private static WPI_TalonSRX ClimbMover = new WPI_TalonSRX(VariableVault.kClimbMoverID);
  
  /**
   * Creates a new Climb.
   */
  public Climb() {
  }

  public void move(double speed) {
    ClimbMover.set(speed);
  }

  public void climb(double speed) {
    ClimbMotor1.set(speed);
    ClimbMotor2.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
