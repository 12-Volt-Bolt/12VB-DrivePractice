package frc.robot.commands.autonomous.auto_commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

import frc.robot.subsystems.DriveSubsystem;

/** Drivetrain Gyro Strafe **************************************************
 * Command for driving sideways using gyroscope feedback. */
public class Drivetrain_GyroStraight extends Command {
  
  	/** Configuration Constants ***********************************************/
	private static final double kP = Constants.GYRO_STRAIGHT_KP;
	private static final double kI = Constants.GYRO_STRAIGHT_KI;
	private static final double kD = Constants.GYRO_STRAIGHT_KD;
	private static final double CIRCUMFRENCE = Constants.WHEEL_DIAMETER * Math.PI;
	private static final double MAX_CORRECTION = Constants.MAX_POWER_GYRO;
	private static final double VELOCITY_THRESHOLD = Constants.GYRO_STRAIGHT_VELOCITY_THRESHOLD;
	private static final double MAX_INTEGRAL = Constants.GYRO_STRAIGHT_MAX_INTEGRAL;
	
	/** Instance Variables ****************************************************/
	DriveSubsystem drivetrain = Robot.m_driveSubsystem;
	double straightPower, goalAngle, goalDistance, integralError, lastError;

  	/** Creates a new Drivetrain_GyroStrafe. */
  	public Drivetrain_GyroStraight(double distance, double power) {
		straightPower = -power;
		
		// convert distance to revolutions
		goalDistance = distance / CIRCUMFRENCE;

		addRequirements(drivetrain);
  	}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
		drivetrain.driveCartesian(0, 0, 0);
		goalAngle = drivetrain.getGyroAngle();
		drivetrain.resetEncoders();
		integralError = 0;
		lastError = 0;
  	}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
		double currentAngle = drivetrain.getGyroAngle();
		double error = goalAngle - currentAngle;
		integralError += error;
		double deltaError = error - lastError;
		
		// Prevent integral windup
		integralError = Math.max(Math.min(integralError, MAX_INTEGRAL), -MAX_INTEGRAL);
		
		double Pterm = kP * error;
		double Iterm = kI * integralError;
		double Dterm = kD * deltaError;
		
		double correction = Pterm + Iterm + Dterm;

		correction = Math.min(MAX_CORRECTION, correction);
		correction = Math.max(-MAX_CORRECTION, correction);
		
		drivetrain.driveCartesian(straightPower, 0, -1 * correction);
		lastError = error;
 	 }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean frontReached = Math.abs(drivetrain.getRightFrontPosition()) >= goalDistance;
		boolean backReached = Math.abs(drivetrain.getRightBackPosition()) >= goalDistance;
		double currentVelocity = Math.abs(drivetrain.getRightFrontVelocity());
		
		return (frontReached && backReached) 
			&& currentVelocity < VELOCITY_THRESHOLD;
  	}

    // Called once the command ends or is interrupted.
	public void end(boolean interrupted) {
		drivetrain.driveCartesian(0, 0, 0);
	}
}