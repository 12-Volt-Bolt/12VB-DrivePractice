package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
    private final WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(Constants.LEFT_FRONT_MOTOR_ID);
    private final WPI_TalonSRX leftRearMotor = new WPI_TalonSRX(Constants.LEFT_REAR_MOTOR_ID);
    private final WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(Constants.RIGHT_FRONT_MOTOR_ID);
    private final WPI_TalonSRX rightRearMotor = new WPI_TalonSRX(Constants.RIGHT_REAR_MOTOR_ID);

    private final DifferentialDrive drive;
    //private final MecanumDrive m_drive = new MecanumDrive(rightMaster, rightSlave, leftMaster, leftSlave);

    public DriveSubsystem() {
        // Configure motors
        leftRearMotor.follow(leftFrontMotor);
        rightRearMotor.follow(rightFrontMotor);
        rightFrontMotor.setInverted(true);
        rightRearMotor.setInverted(true);

        // Set up the differential drive
        drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
    }

    // Method to control the robot's tank drive
    /* public void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed, rightSpeed);
    } */

    public void arcadeDrive(double speed, double rotation) {
        drive.arcadeDrive(speed, rotation);
    }

    // Optional: Stop the motors
    public void stop() {
        drive.stopMotor();
    }

    @Override
    public void periodic(){

        Double xvalue = Limelight.getHorizontalOffset();

        if (xvalue >= 20 && xvalue <= -20) {
            drive.arcadeDrive(0, 5);
        }

        SmartDashboard.putString("Test", "I'm running");  
        if (Limelight.hasTarget()) {
            SmartDashboard.putString("Sight Test", "tag " + xvalue);
        } else {
            SmartDashboard.putString("Sight Test", "Doesn't see a tag");
        }
        if (xvalue <= 20 && xvalue >= -20) {
            SmartDashboard.putString("Within Range", "Target is in front of me");
        } else {
            SmartDashboard.putString("Within Range", "Target is NOT in front of me");
        }
    }
}
