/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private static WPI_VictorSPX IntakeMotor = new WPI_VictorSPX(Constants.kIntakeMotorID);
  /**
   * Creates a new Intake.
   */
  public Intake() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void IntakeIn(){
    IntakeMotor.set(1);
  }

  public void IntakeOut(){
    IntakeMotor.set(-1);
  }

  public void StopIntakeMotor(){
    IntakeMotor.set(0);
  }
}
