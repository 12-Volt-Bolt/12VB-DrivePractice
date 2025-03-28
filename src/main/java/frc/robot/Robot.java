package frc.robot;

  import edu.wpi.first.wpilibj.TimedRobot;
  import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
  import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

  import java.util.Optional;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DriverStation;
  import edu.wpi.first.wpilibj.DriverStation.Alliance;
  import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
  import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.POVButton;
  import edu.wpi.first.wpilibj2.command.button.Trigger;
  import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.DisableGyro;
import frc.robot.commands.EnableGyro;
import frc.robot.commands.autonomous.auto_scripts.*;
import frc.robot.subsystems.AlgaeSubsystem;
import frc.robot.subsystems.CoralSubsystem;
// import frc.robot.commands.elevator.CoralElevatorMoveCommand;
  // import frc.robot.commands.elevator.CoralElevatorSetPositionArmCommand;
  // import frc.robot.commands.elevator.CoralElevatorWheelMoveCommand;
/*   import frc.robot.commands.intake.IntakePickUpAlgaeCommand;
  import frc.robot.commands.intake.IntakePickUpCoralCommand;
  import frc.robot.commands.intake.IntakeSetArmPositionCommand;
  import frc.robot.commands.intake.IntakeSetBarPowerComma
  import frc.robot.commands.vision.DriveToTrackedTargetCommand; */
  // import frc.robot.subsystems.CoralElevatorSubsystem;
  import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
//import frc.robot.subsystems.IntakeSubsystem;
  import frc.robot.subsystems.PowerSubsystem;
  import frc.robot.subsystems.AprilTagAlignSubsystem;
  //import frc.robot.subsystems.VisionSubsystem;

  /**
   * The VM is configured to automatically run this class, and to call the functions corresponding to
   * each mode, as described in the TimedRobot documentation. If you change the name of this class or
   * the package after creating this project, you must also update the build.gradle file in the
   * project.
   */
  public class Robot extends TimedRobot {

    Command m_autonomousCommand;
    SendableChooser<Command> autonChooser = new SendableChooser<Command>(); // Create a chooser to select an autonomous command
    SendableChooser<Command> gyroChooser = new SendableChooser<Command>();

    public static boolean manualDriveControl = true;

    public static final GenericHID controller = new GenericHID(Constants.CONTROLLER_USB_PORT_ID); // Instantiate our controller at the specified USB port

    public static final DriveSubsystem m_driveSubsystem = new DriveSubsystem(); // Drivetrain subsystem
    public static final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
    public static final PowerSubsystem m_powerSubsystem = new PowerSubsystem(); // Power subsystem for interacting with the Rev PDH
    public static final CoralSubsystem m_coralSubsysyem = new CoralSubsystem();
    public static final AlgaeSubsystem m_algaeSubsystem = new AlgaeSubsystem();
    public static final AprilTagAlignSubsystem m_aprilTagAlign = new AprilTagAlignSubsystem();

    double goalAngle;

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
     
    @Override
    public void robotInit() {
      configureButtonBindings(); // Bind our commands to physical buttons on a controller

      // Add our Autonomous Routines to the chooser //


      autonChooser.setDefaultOption("Do Nothing", new InstantCommand());
      autonChooser.addOption("Drive 1 Meter", new Drive1MeterAuto());
      autonChooser.addOption("Square", new SquareAutonomous());
      autonChooser.addOption("Triangle", new TriangleAutonomous());
      autonChooser.addOption("Strafe 1 Meter", new Strafe1MeterAutonomous());
      autonChooser.addOption("Drive 1 Meter - Bunny Edition", new Drive1MeterBackAuto());
      autonChooser.addOption("Turn 90 Degrees", new Rotate90Degrees());
      autonChooser.addOption("full auto", new FullAutoTest(m_elevatorSubsystem));
      autonChooser.addOption("IDEK", new IDEK());
      autonChooser.addOption("Isaiah Auto Command", new IsaiahAutoCommand());
      autonChooser.addOption("Shuffle Dance", new StrafeFullTest());
      SmartDashboard.putData("Auto Mode", autonChooser);

      gyroChooser.setDefaultOption("Enabled", new EnableGyro());
      gyroChooser.addOption("Disabled", new DisableGyro());
      SmartDashboard.putData("Gyro Setting", gyroChooser);


      // Zero the gyroscope and reset the drive encoders
      m_driveSubsystem.zeroGyro();
      m_driveSubsystem.resetEncoders();
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * This runs after the mode specific periodic functions, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
      // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
      // commands, running already-scheduled commands, removing finished or interrupted commands,
      // and running subsystem periodic() methods. This must be called from the robot's periodic
      // block in order for anything in the Command-based framework to work.
      
      CommandScheduler.getInstance().run();
      
    }

    /** This function is called once each time the robot enters Disabled mode. */
    @Override
    public void disabledInit() {
      System.out.println("BUNNY MISSING FOOD (robot disabled)");
    }

    /** This function is called continuously after the robot enters Disabled mode. */
    @Override
    public void disabledPeriodic() {

    }

    /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
    @Override
    public void autonomousInit() {
      System.out.println("BUNNY RUNNING FREE");

      m_autonomousCommand = autonChooser.getSelected();
      
      // Zero the gyrodcope and reset the drive encoders
      m_driveSubsystem.zeroGyro();
      m_driveSubsystem.resetEncoders();

      // schedule the selected autonomous command
      if (m_autonomousCommand != null) {
        m_autonomousCommand.schedule();
      }

      // Set the LED pattern for autonomous mode

      // Set Elevator/End Effector inital preset
      // m_CoralElevatorSubsystem.climbNeutral();
      // m_CoralElevatorSubsystem.armInitial();
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
      System.out.println("BUNNY BEING CONTROLLED BY OWNER");

      // This makes sure that the autonomous stops running when
      // teleop starts running. If you want the autonomous to
      // continue until interrupted by another command, remove
      // this if statement or comment it out.
      if (m_autonomousCommand != null) {
        m_autonomousCommand.cancel();
      }

      // Zero the gyroscope and reset the drive encoders
      m_driveSubsystem.zeroGyro();
      m_driveSubsystem.resetEncoders();

      goalAngle = m_driveSubsystem.getGyroAngle();
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
      // Log controller inputs to SmartDashboard
      SmartDashboard.putNumber("Controller: Right Trigger", controller.getRawAxis(Constants.RIGHT_TRIGGER_AXIS));
      SmartDashboard.putNumber("Controller: Left Trigger", controller.getRawAxis(Constants.LEFT_TRIGGER_AXIS));
      // SmartDashboard.putBoolean("Controller: Right Bumper", controller.getRawButton(Constants.RIGHT_BUMPER));
      // SmartDashboard.putBoolean("Controller: Left Bumper", controller.getRawButton(Constants.LEFT_BUMPER));
      // SmartDashboard.putBoolean("Controller: X Button", controller.getRawButton(Constants.X_BUTTON));
      // SmartDashboard.putBoolean("Controller: Y Button", controller.getRawButton(Constants.Y_BUTTON));
      // SmartDashboard.putBoolean("Controller: B Button", controller.getRawButton(Constants.B_BUTTON));
      // SmartDashboard.putBoolean("Controller: A Button", controller.getRawButton(Constants.A_BUTTON));
      // SmartDashboard.putNumber("Controller: Left Joystick X Axis", controller.getRawAxis(Constants.LEFT_HORIZONTAL_JOYSTICK_AXIS));
      // SmartDashboard.putNumber("Controller: Left Joystick Y Axis", controller.getRawAxis(Constants.LEFT_VERTICAL_JOYSTICK_AXIS));
      // SmartDashboard.putNumber("Controller: Right Joystick X Axis", controller.getRawAxis(Constants.RIGHT_HORIZONTAL_JOYSTICK_AXIS));
      // SmartDashboard.putNumber("Controller: Right Joystick Y Axis", controller.getRawAxis(Constants.RIGHT_VERTICAL_JOYSTICK_AXIS));

    if (Robot.manualDriveControl) {

      
      double ySpeed = controller.getRawAxis(Constants.LEFT_VERTICAL_JOYSTICK_AXIS);
      double xSpeed = -controller.getRawAxis(Constants.LEFT_HORIZONTAL_JOYSTICK_AXIS);
      double zSpeed = -controller.getRawAxis(Constants.RIGHT_HORIZONTAL_JOYSTICK_AXIS);
      
      // Speed limits
      ySpeed = Math.max(Math.min(ySpeed, Constants.SPEED_LIMIT), -Constants.SPEED_LIMIT);
      xSpeed = Math.max(Math.min(xSpeed, Constants.SPEED_LIMIT), -Constants.SPEED_LIMIT);
      zSpeed = Math.max(Math.min(zSpeed, Constants.SPEED_LIMIT), -Constants.SPEED_LIMIT);

      if (Math.abs(zSpeed) > 0.01) { // If we are telling the robot to rotate, then let it rotate
        // m_driveSubsystem.driveCartesian(ySpeed, xSpeed, zSpeed, m_driveSubsystem.getRotation2d()); // field-relative
        m_driveSubsystem.driveCartesian(ySpeed, xSpeed, zSpeed); // robot-relative
        goalAngle = m_driveSubsystem.getGyroAngle();
      }
      else { // Otherwise, use the gyro to maintain our current angle
        double error = m_driveSubsystem.getGyroAngle() - goalAngle;
        
        double correction = Constants.GYRO_TURN_KP * error;
        if (Math.abs(correction) > Constants.MAX_POWER_GYRO) { // Maximum value we want
          correction = Math.copySign(Constants.MAX_POWER_GYRO, correction);
        }
        
        // m_driveSubsystem.driveCartesian(ySpeed, xSpeed, -1 * correction, m_driveSubsystem.getRotation2d()); // field-relative
        m_driveSubsystem.driveCartesian(ySpeed, xSpeed, 0); // robot-relative
        }
      } else {
        goalAngle = m_driveSubsystem.getGyroAngle();
      }
    }

    @Override
    public void testInit() {
      // Cancels all running commands at the start of test mode.
      CommandScheduler.getInstance().cancelAll();
      System.out.println("TEST BUNNY STARTED");
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {}

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or onse of its subclasses ({@link edu.wpi.first.wpilibj.Joystick} 
     * or {@link XboxController}), and then passing it to a {@link edu.wpi.first.wpilibj2.command.button.Trigger}.
     */
    private void configureButtonBindings() {
      // Intake Controls //
      // new Trigger(() -> controller.getRawButton(Constants.RIGHT_BUMPER)).whileTrue(new IntakeSetBarPowerCommand(Constants.INTAKE_BAR_SPEED)); // Intake 
      // new Trigger(() -> controller.getRawButton(Constants.LEFT_BUMPER)).whileTrue(new IntakeSetBarPowerCommand(-Constants.INTAKE_BAR_SPEED)); // Outake 

      // new Trigger(() -> controller.getRawButton(Constants.A_BUTTON)).onTrue(new IntakeSetArmPositionCommand(Constants.HOLD_ALGAE_POSITION)); // Set arm position
      // new Trigger(() -> controller.getRawButton(Constants.B_BUTTON)).onTrue(new IntakeSetArmPositionCommand(Constants.HOLD_CORAL_POSITION)); // Set arm position
      // new Trigger(() -> controller.getRawButton(Constants.Y_BUTTON)).onTrue(new IntakeSetArmPositionCommand(Constants.PICK_UP_ALGAE_POSITION)); // Set arm position
      // new Trigger(() -> controller.getRawButton(Constants.X_BUTTON)).onTrue(new IntakeSetArmPositionCommand(Constants.PICK_UP_CORAL_POSITION)); // Set arm position

      //new Trigger(() -> controller.getRawButton(Constants.RIGHT_BUMPER)).whileTrue(new IntakePickUpAlgaeCommand()); // Pick Up Algae
      //new Trigger(() -> controller.getRawButton(Constants.RIGHT_BUMPER)).onFalse(new IntakeSetArmPositionCommand(Constants.HOLD_ALGAE_POSITION));
      //new Trigger(() -> controller.getRawButton(Constants.RIGHT_TRIGGER_BUTTON)).whileTrue(new IntakePickUpCoralCommand()); // Pick Up Coral
      //new Trigger(() -> controller.getRawButton(Constants.RIGHT_TRIGGER_BUTTON)).onFalse(new IntakeSetArmPositionCommand(Constants.HOLD_ALGAE_POSITION));

      // Coral Elevator Controls //
      /* new Trigger(() -> controller.getRawButton(Constants.PREV_BUTTON)).whileTrue(new CoralElevatorWheelMoveCommand(-Constants.WHEEL_SPEED)); // Wheel Outtake Manual
      new Trigger(() -> controller.getRawButton(Constants.START_BUTTON)).whileTrue(new CoralElevatorWheelMoveCommand(Constants.WHEEL_SPEED)); // Weel Intake Manual
      new POVButton(controller, 0).onTrue(new CoralElevatorSetPositionArmCommand(m_CoralElevatorSubsystem.arm_max)); // Score Mid Preset
      new POVButton(controller, 90).onTrue(new CoralElevatorSetPositionArmCommand(21.33)); // Score High Preset
      new POVButton(controller, 180).onTrue(new CoralElevatorSetPositionArmCommand(29.4)); //  Intake Preset
      new POVButton(controller, 270).onTrue(new CoralElevatorSetPositionArmCommand(m_CoralElevatorSubsystem.arm_max)); // Score Low Preset
 */
      // Test Controls //
      //new Trigger(() -> controller.getRawButton(Constants.A_BUTTON)).whileTrue(new DriveToTrackedTargetCommand(1)); // Track AprilTag
      // Add left turn binding using left trigger

    /* new Trigger(() -> triggerValue > 0.1)
			.whileTrue(new RunCommand(() -> {
				// Get the trigger value (used to control the speed)
				//double triggerValue = controller.getRawAxis(Constants.LEFT_TRIGGER_AXIS);
				
				// Control the left motors (inverted)
				double leftMotorSpeed = -triggerValue;
				
				// Control the right motors (normal)
				double rightMotorSpeed = triggerValue;

        
				
				//m_driveSubsystem.setLeftMotorSpeed(leftMotorSpeed);  // Set speed for left front motor

				//m_driveSubsystem.setRightMotorSpeed(rightMotorSpeed * -1);  // Set speed for right front motor
			}, m_driveSubsystem)); */


    }
  }