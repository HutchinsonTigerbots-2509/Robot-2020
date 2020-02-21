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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.Turret.AlignTurretAutonomous;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ConveyorReverse;
import frc.robot.commands.RunConveyorMax;
import frc.robot.commands.RunShooterMax;
import frc.robot.commands.ShootAll;
import frc.robot.commands.ShootAllAutonomous;
import frc.robot.commands.Drivetrain.RadiusTurnRight;
import frc.robot.commands.Turret.AlignTurret;
import frc.robot.subsystems.Climb;
import frc.robot.commands.Drivetrain.RadiusTurnLeft;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj2.command.WaitCommand;
/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Declare buttons here
  private static JoystickButton AlignButton;
  private static JoystickButton RunConveyorMaxButton;
  private static JoystickButton ConveyorReverseButton;
  private static JoystickButton ShootAllButton;
  private static JoystickButton RunShooterMaxButton;
  private static JoystickButton ColorWheelForward;
  private static JoystickButton ColorWheelReverse;
  private static JoystickButton SwitchPipelineButton;
  private static JoystickButton ExtendClimberButton;
  private static JoystickButton RetractClimberButton;
  private static JoystickButton CreepLeftButton;
  private static JoystickButton CreepRightButton;

   // Subsystems - Create all subsystems here, and then pass them into Commands
  public static Drivetrain sDrivetrain = new Drivetrain();
  public static Vision sVision = new Vision();
  public static Turret sTurret = new Turret();
  public static Conveyor sConveyor = new Conveyor();
  public static Shooter sShooter = new Shooter();
  public static Intake sIntake = new Intake();
  public static ColorWheel sColorWheel = new ColorWheel();
  public static Climb sClimb = new Climb();

  //FOR SHUFFLEBOARD AUTO CHOOSING
  // private final SendableChooser<Command> commandChooser = new SendableChooser<Command>();
  // private static ShuffleboardTab CoolTab = Shuffleboard.getTab("CoolTab");
  
   // Joysticks - Joysticks are made here
  public static Joystick OpStick = new Joystick(Constants.kOpStickID);
  public static Joystick CoOpStick = new Joystick(Constants.kCoOpStickID);

   // Commands - Create Command Objects

  private ParallelCommandGroup AutoCommands = new ParallelCommandGroup();
  

  // AUTONOMOUS 1A - START RIGHT OF TARGET, GOES STRAIGHT BACK INTO TRENCH
  // Vision issues.
  // private ParallelCommandGroup AutoCommands = new ParallelCommandGroup(
  //   new SequentialCommandGroup(
  //     new RunCommand(() -> sTurret.TurnRight(0.9), sTurret).withTimeout(1),
  //     new AlignTurret(sVision, sTurret)),//.withTimeout(2),
  //     // new WaitCommand(0.8).andThen(new AlignTurret(sVision, sTurret)),
  //   new RunCommand(() -> sIntake.DropIntake()).withTimeout(1)
  //     .andThen(new RunCommand(() -> sIntake.IntakeIn())),
  //   new ShootAllAutonomous(sShooter, sConveyor, 3900, 1),
  //   new WaitCommand(5).andThen(new RunCommand(() -> sDrivetrain.MoveDrivetrain(0.6)).withTimeout(2.3)));


  // AUTONOMOUS 2A - START IN FRONT OF TARGET, ANGLED TOWARD CONTROL PANEL
  // Might not go back far enough. Goes straight back.
  // private ParallelCommandGroup AutoCommands = new ParallelCommandGroup(
  //   new SequentialCommandGroup(
  //     new RunCommand(() -> sTurret.TurnRight(0.9), sTurret).withTimeout(0.6),
  //     new AlignTurret(sVision, sTurret)),
  //   new RunCommand(() -> sIntake.DropIntake()).withTimeout(1.5)
  //     .andThen(new RunCommand(() -> sIntake.IntakeIn())),
  //   new SequentialCommandGroup(
  //     new ShootAllAutonomous(sShooter, sConveyor, 3900, 0.8).withTimeout(4),
  //     new RunCommand(() -> sDrivetrain.MoveDrivetrain(0.5)).withTimeout(6)
  //     .alongWith(new RunCommand(() -> sIntake.IntakeIn()).withTimeout(5)),
  //     new ShootAllAutonomous(sShooter, sConveyor, 3900, 0.8)
  //   ));
  
  //Autonomous 2A (with turn) - START IN FRONT OF TARGET ANGLED TOWARD FRONT OF TRENCH
  // Shoots trench balls too high
  // private ParallelCommandGroup AutoCommands = new ParallelCommandGroup(
  //   new SequentialCommandGroup(
  //     new RunCommand(() -> sTurret.TurnRight(0.9), sTurret).withTimeout(0.6),
  //     new AlignTurret(sVision, sTurret).withTimeout(1.5),
  //     new WaitCommand(1.3).andThen(new AlignTurret(sVision, sTurret))),
  //   new RunCommand(() -> sIntake.DropIntake()).withTimeout(1.5)
  //     .andThen(new RunCommand(() -> sIntake.IntakeIn())),
  //   new SequentialCommandGroup(
  //     new ShootAllAutonomous(sShooter, sConveyor, 3900, 1).withTimeout(4),
  //     new RunCommand(() -> sDrivetrain.MoveDrivetrain(0.5)).withTimeout(2.8),
  //     new RadiusTurnRight(sDrivetrain, 25, -0.05, 5),
  //     new RunCommand(() -> sDrivetrain.MoveDrivetrain(0.4)).withTimeout(5.2)
  //       .raceWith(new RunCommand(() -> sConveyor.FullConveyorForward(1, 0.8)),
  //       new ShootAllAutonomous(sShooter, sConveyor, 0, 1)),
  //     new ShootAllAutonomous(sShooter, sConveyor, 2500, 1)
  //     .alongWith(new RunCommand(() -> sConveyor.FullConveyorForward(1, 0.8)))
  //   ));

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // CoolTab.add("Auto Chooser", commandChooser);

    // commandChooser.setDefaultOption("A1", new PUTNAMEHERE());
    // commandChooser.addOption("A1", new PUTNAMEHERE());

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  
  private void configureButtonBindings() {
  AlignButton = new JoystickButton(CoOpStick, 1);
  AlignButton.toggleWhenPressed(new AlignTurret(sVision, sTurret));

  RunConveyorMaxButton = new JoystickButton(CoOpStick, 5);
  RunConveyorMaxButton.whileHeld(new RunConveyorMax(sConveyor));

  ConveyorReverseButton = new JoystickButton(CoOpStick, 6);
  ConveyorReverseButton.whileHeld(new ConveyorReverse(sConveyor));

  ShootAllButton = new JoystickButton(CoOpStick, 2);
  ShootAllButton.whileHeld(new ShootAll(sShooter, sConveyor));

  RunShooterMaxButton = new JoystickButton(CoOpStick, 3);
  // RunShooterMaxButton.toggleWhenPressed(new RampUpShooter(sShooter).andThen(new RunShooterRPM(sShooter)));
  RunShooterMaxButton.toggleWhenPressed(new RunShooterMax(sShooter));
  // RunShooterMaxButton.toggleWhenPressed(new RampUpShooter(sShooter));

  ColorWheelForward = new JoystickButton(OpStick, 5);
  ColorWheelForward.whileHeld(new RunCommand(() -> sColorWheel.ColorWheelForward(), sColorWheel));
  ColorWheelForward.whenReleased(new InstantCommand(() -> sColorWheel.StopColorMotor()));

  ColorWheelReverse = new JoystickButton(OpStick, 6);
  ColorWheelReverse.whileHeld(new RunCommand(() -> sColorWheel.ColorWheelReverse(), sColorWheel));
  ColorWheelReverse.whenReleased(new InstantCommand(() -> sColorWheel.StopColorMotor()));

  SwitchPipelineButton = new JoystickButton(CoOpStick, 4);
  SwitchPipelineButton.whenPressed(new InstantCommand(() -> sVision.SwitchPipeline(), sVision));

  ExtendClimberButton = new JoystickButton(CoOpStick, 8);
  ExtendClimberButton.whileHeld(new RunCommand(() -> sClimb.ClimbExtend()));
  ExtendClimberButton.whenReleased(new InstantCommand(() -> sClimb.StopClimb()));

  RetractClimberButton = new JoystickButton(CoOpStick, 7);
  RetractClimberButton.whileHeld(new RunCommand(() -> sClimb.ClimbRetract()));
  RetractClimberButton.whenReleased(new InstantCommand(() -> sClimb.StopClimb()));

  CreepLeftButton = new JoystickButton(OpStick, 3);
  CreepLeftButton.whileHeld(new RunCommand(() -> sClimb.Creep(1)));
  CreepLeftButton.whenReleased(new InstantCommand(() -> sClimb.Creep(0)));

  CreepRightButton = new JoystickButton(OpStick, 2);
  CreepRightButton.whileHeld(new RunCommand(() -> sClimb.Creep(-1)));
  CreepRightButton.whenReleased(new InstantCommand(() -> sClimb.Creep(0)));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // NOTE: Put in an actual command
    return AutoCommands;
    //return commandChooser.getSelected();
  }
}
