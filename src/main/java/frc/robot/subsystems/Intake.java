/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {
  private static WPI_VictorSPX IntakeMotor = new WPI_VictorSPX(Constants.kIntakeMotorID);
  private static WPI_TalonSRX IntakeDropMotor = new WPI_TalonSRX(Constants.kIntakeDropMotorID);
  private int dropCounter;
  private int liftCounter;
  /**
   * Creates a new Intake.
   */
  public Intake() {
    dropCounter = 0;
    liftCounter = 0;
  }

  @Override
  public void periodic() {
    if(RobotContainer.CoOpStick.getRawAxis(2) > 0.1){
      if(dropCounter < 50){
        DropIntake();
        dropCounter = dropCounter + 1;
        liftCounter = 0;
      }
      IntakeOut();
    } else if (RobotContainer.CoOpStick.getRawAxis(3) > 0.1){
      if(dropCounter < 50){
        DropIntake();
        dropCounter = dropCounter + 1;
        liftCounter = 0;
      }
      IntakeIn();
    } else {
      if(liftCounter < 150){
        LiftIntake();
        liftCounter = liftCounter + 1;
        dropCounter = 0;
      }
      StopIntakeMotor();
    }

    if(RobotContainer.CoOpStick.getRawAxis(1) > 0.2){
      DropIntake();
    } else if (RobotContainer.CoOpStick.getRawAxis(1) < -0.2){
      LiftIntake();
    } else if (liftCounter >= 150 || dropCounter >= 50){
      StopDropIntake();
    }
    // This method will be called once per scheduler run
  }

  public void DropIntake(){
    IntakeDropMotor.set(1);
  }

  public void LiftIntake(){
    IntakeDropMotor.set(-1);
  }

  public void StopDropIntake(){
    IntakeDropMotor.set(0);
  }

  public void IntakeIn(){
    IntakeMotor.set(0.8);
  }

  public void IntakeOut(){
    IntakeMotor.set(-0.8);
  }

  public void StopIntakeMotor(){
    IntakeMotor.set(0);
  }
}
