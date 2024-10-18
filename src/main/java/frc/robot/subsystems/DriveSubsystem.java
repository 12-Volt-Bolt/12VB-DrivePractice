package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
    private final WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(Constants.LEFT_FRONT_MOTOR_ID);
    private final WPI_TalonSRX leftRearMotor = new WPI_TalonSRX(Constants.LEFT_REAR_MOTOR_ID);
    private final WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(Constants.RIGHT_FRONT_MOTOR_ID);
    private final WPI_TalonSRX rightRearMotor = new WPI_TalonSRX(Constants.RIGHT_REAR_MOTOR_ID);

    private final DifferentialDrive drive;

    public DriveSubsystem() {
        // Configure motors
        leftRearMotor.follow(leftFrontMotor);
        rightRearMotor.follow(rightFrontMotor);
        rightFrontMotor.setInverted(true);

        // Set up the differential drive
        drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
    }

    // Method to control the robot's tank drive
    public void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed, rightSpeed);
    }

    // Optional: Stop the motors
    public void stop() {
        drive.stopMotor();
    }
}
