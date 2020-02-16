/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {
  private static WPI_VictorSPX IntakeMotor = new WPI_VictorSPX(Constants.kIntakeMotorID);
  private static WPI_TalonSRX IntakeDropMotor = new WPI_TalonSRX(Constants.kIntakeDropMotorID);
  /**
   * Creates a new Intake.
   */
  public Intake() {
  }

  @Override
  public void periodic() {
    if(RobotContainer.CoOpStick.getRawAxis(2) > 0.1){
      IntakeIn();
    } else if (RobotContainer.CoOpStick.getRawAxis(3) > 0.1){
      IntakeOut();
    } else {
      StopIntakeMotor();
    }

    if(RobotContainer.CoOpStick.getRawAxis(1) > 0.2){
      LiftIntake();
    } else if (RobotContainer.CoOpStick.getRawAxis(1) < -0.2){
      DropIntake();
    } else {
      StopDropIntake();
    }
    // This method will be called once per scheduler run
  }

  public void DropIntake(){
    IntakeDropMotor.set(0.5);
  }

  public void LiftIntake(){
    IntakeDropMotor.set(-0.5);
  }

  public void StopDropIntake(){
    IntakeDropMotor.set(0);
  }

  private void IntakeIn(){
    IntakeMotor.set(0.8);
  }

  private void IntakeOut(){
    IntakeMotor.set(-0.8);
  }

  private void StopIntakeMotor(){
    IntakeMotor.set(0);
  }
}
