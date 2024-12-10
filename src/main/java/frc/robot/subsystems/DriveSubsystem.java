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
        SmartDashboard.putNumber("Left Front Motor Speed",       leftFrontMotor.getMotorOutputPercent());  
        SmartDashboard.putNumber("Left Front Motor Voltage",     leftFrontMotor.getMotorOutputVoltage());  
        SmartDashboard.putNumber("Left Front Motor Temperature", leftFrontMotor.getTemperature());  
        SmartDashboard.putNumber("Left Front Motor Velocity",    leftFrontMotor.getSelectedSensorVelocity());  
  
    }
/*     @Override
    public void periodic() {
        
      // Add data to the Shuffleboard tab
       SmartDashboard.putNumber("Left Front Motor Speed",       leftFrontMotor.getMotorOutputPercent());  
       SmartDashboard.putNumber("Left Front Motor Voltage",     leftFrontMotor.getMotorOutputVoltage());  
       SmartDashboard.putNumber("Left Front Motor Temperature", leftFrontMotor.getTemperature());  
       SmartDashboard.putNumber("Left Front Motor Velocity",    leftFrontMotor.getSelectedSensorVelocity());  

       SmartDashboard.putNumber("Left Back Motor Speed",       leftRearMotor.getMotorOutputPercent());  
       SmartDashboard.putNumber("Left Back Motor Voltage",     leftRearMotor.getMotorOutputVoltage());  
       SmartDashboard.putNumber("Left Back Motor Temperature", leftRearMotor.getTemperature());  
       SmartDashboard.putNumber("Left Back Motor Velocity",    leftRearMotor.getSelectedSensorVelocity());  

       SmartDashboard.putNumber("Right Front Motor Speed",       rightFrontMotor.getMotorOutputPercent());  
       SmartDashboard.putNumber("Right Front Motor Voltage",     rightFrontMotor.getMotorOutputVoltage());  
       SmartDashboard.putNumber("Right Front Motor Temperature", rightFrontMotor.getTemperature());  
       SmartDashboard.putNumber("Right Front Motor Velocity",    rightFrontMotor.getSelectedSensorVelocity());  
      
       SmartDashboard.putNumber("Right Back Motor Speed",       rightRearMotor.getMotorOutputPercent());  
       SmartDashboard.putNumber("Right Back Motor Voltage",     rightRearMotor.getMotorOutputVoltage());  
       SmartDashboard.putNumber("Right Back Motor Temperature", rightRearMotor.getTemperature());  
       SmartDashboard.putNumber("Right Back Motor Velocity",    rightRearMotor.getSelectedSensorVelocity());  
    }
 */

}
