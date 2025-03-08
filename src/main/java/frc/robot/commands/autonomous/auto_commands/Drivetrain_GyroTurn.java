// Author: UMN Robotics Ri3D
// Last Updated: January 2025

package frc.robot.commands.autonomous.auto_commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

import frc.robot.subsystems.DriveSubsystem;

/** Drivetrain Gyro Turn ******************************************************
 * Command for turning in place to a set angle. */
public class Drivetrain_GyroTurn extends Command {
	/** Configuration Constants ***********************************************/
	private static final double kP = Constants.GYRO_TURN_KP;
	private static final double kI = Constants.GYRO_TURN_KI;  // Now using I term
	private static final double kD = Constants.GYRO_TURN_KD;  // Now using D term
	private static final double TURNING_THRESHOLD_DEGREES = Constants.TURNING_THRESHOLD_DEGREES;
	private static final double VELOCITY_THRESHOLD = Constants.GYRO_TURN_VELOCITY_THRESHOLD;
	private static final double MAX_POWER = Constants.MAX_POWER_GYRO;
	private static final double MAX_INTEGRAL = Constants.GYRO_TURN_MAX_INTEGRAL;
	
	/** Instance Variables ****************************************************/
	DriveSubsystem drivetrain = Robot.m_driveSubsystem;
	double lastError, integralError, goalAngle;
	
	/** Drivetrain Gyro Turn ************************************************** 
	 * Required subsystems will cancel commands when this command is run.
	 * distance is given in physical units matching the wheel diameter unit
	 * speed is given in physical units per second. The physical units should 
	 * match that of the Wheel diameter.
	 * @param angle (degrees) */
	public Drivetrain_GyroTurn(double angle) {
		goalAngle = angle;
		addRequirements(drivetrain);
	}
	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time */
	public void initialize() {
		drivetrain.driveCartesian(0, 0, 0);
		lastError = 0.0;
		integralError = 0.0;
		drivetrain.zeroGyro();
	}

	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run */
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
		
		// Apply power constraints
		correction = Math.min(MAX_POWER, correction);
		correction = Math.max(-MAX_POWER, correction);
	
		drivetrain.driveCartesian(0, 0, -1 * correction);		
		lastError = error;
	}
	
	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute() */
	public boolean isFinished() {
		double currentVelocity = Math.abs(drivetrain.getGyroRate());
		return Math.abs(lastError) < TURNING_THRESHOLD_DEGREES 
			&& currentVelocity < VELOCITY_THRESHOLD;
	}

	// Called once the command ends or is interrupted.
	public void end(boolean interrupted) {
		drivetrain.driveCartesian(0, 0, 0);
	}
}