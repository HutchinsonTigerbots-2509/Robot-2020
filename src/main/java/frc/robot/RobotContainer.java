// LOOK BELOW 
//
//
// LOOK HERE:
// https://github.com/wpilibsuite/allwpilib/tree/master/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/armbot
//
// LOOK ABOVE
//
//

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AlignWithTarget;
import frc.robot.commands.OPDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AlignTurret;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Intake;
import frc.robot.commands.RunConveyorMax;
import frc.robot.commands.ConveyorReverse;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Declare buttons here
  private static JoystickButton AlignButton;
  private static JoystickButton RunConveyorMaxButton;
  private static JoystickButton ConveyorReverseButton;

   // Subsystems - Create all subsystems here, and then pass them into Commands
  public static Drivetrain sDrivetrain = new Drivetrain();
  public static Vision sVision = new Vision();
  public static Turret sTurret = new Turret();
  public static Intake sIntake = new Intake();

  
   // Joysticks - Joysticks are made here
  public static Joystick OpStick = new Joystick(Constants.kOpStickID);

   // Commands - Create Command Objects
   // NOTE: it should be private, but if you need to reference it elsewhere, then 
  public final OPDrive OPDrive = new OPDrive(sDrivetrain, OpStick);



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  AlignButton = new JoystickButton(OpStick, 1);
  AlignButton.whenPressed(new AlignTurret(sVision, sTurret));

  RunConveyorMaxButton = new JoystickButton(OpStick, 11);
  RunConveyorMaxButton.whileHeld(new RunConveyorMax(sIntake));

  ConveyorReverseButton = new JoystickButton(OpStick, 12);
  ConveyorReverseButton.whileHeld(new ConveyorReverse(sIntake));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // NOTE: Put in an actual command
    return OPDrive;
  }
}
