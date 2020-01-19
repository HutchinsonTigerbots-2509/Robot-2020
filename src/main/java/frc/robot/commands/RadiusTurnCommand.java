/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RadiusTurnCommand extends CommandBase {
  private static Drivetrain DT;
  private static int Angle;
  private static double Speed;
  private static double Radius;
  private static String Direction;
  private static double LongSpeed;
  private boolean Finished;
  /**
   * Creates a new RadiusTurnCommand.
   */
  public RadiusTurnCommand(Drivetrain pDT, int pAngle, double pSpeed, double pRadius, String pDirection) {
    DT = pDT;
    Angle = pAngle;
    Speed = pSpeed;
    Radius = pRadius;
    Direction = pDirection;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(DT);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    DT.DrivetrainGyro.reset();
    LongSpeed = (Speed * (Math.PI *(Radius + 22)*2))/(Math.PI*Radius*2);
    Finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Gyro",DT.DrivetrainGyro.getAngle());
    SmartDashboard.putString("Going", Direction);
    if(Direction == "Right"){
      if(DT.DrivetrainGyro.getAngle() < Angle){
        DT.Right.set(-LongSpeed);
        DT.Left.set(Speed);
      }else{
        DT.Right.set(0);
        DT.Left.set(0);
        Finished = true;
      }
    }else if(Direction == "Left"){
      if(DT.DrivetrainGyro.getAngle() > -Angle){
        DT.Left.set(LongSpeed);
        DT.Right.set(-Speed);
      }else{
        DT.Right.set(0);
        DT.Left.set(0);
        Finished = true;
      }
    }else{
      DT.Right.set(0);
      DT.Left.set(0);
      Finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DT.Right.set(0);
    DT.Left.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Finished == true){
      return true;
    } else {
      return false;
    }
  }
}
