package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
    private final SparkMax motor = new SparkMax(Constants.ELEVATOR_MOTOR_ID, MotorType.kBrushless);
    private final XboxController controller = new XboxController(0);
    
    private static final double GEAR_RATIO = Constants.ELEVATOR_GEAR_RATIO;
    private static final double SPOOL_DIAMETER_INCHES = Constants.ELEVATOR_SPOOL_DIAMETER;
    private static final double MAX_HEIGHT_INCHES = Constants.ELEVATOR_MAX_HEIGHT;
    private static final double MIN_HEIGHT_INCHES = Constants.ELEVATOR_MIN_HEIGHT;
    
    private double speed = 0.0;

    @Override
    public void periodic() {
        boolean leftBumper = controller.getRawButton(5);
        boolean rightBumper = controller.getRawButton(6);
        
        double currentPosition = (motor.getEncoder().getPosition() / GEAR_RATIO) * 
                               (Math.PI * SPOOL_DIAMETER_INCHES);

        SmartDashboard.putBoolean("Controller: Left Bumper", false);
        SmartDashboard.putBoolean("Controller: Right Bumper", false);

        if (leftBumper && rightBumper) {
            speed = 0.0;
            SmartDashboard.putBoolean("Controller: Right Bumper", false);
            SmartDashboard.putBoolean("Controller: Left Bumper", false);
        } else if (leftBumper && currentPosition < (MAX_HEIGHT_INCHES - 0.5)) {
            SmartDashboard.putBoolean("Controller: Left Bumper", true);
            speed = Constants.ELEVATOR_SPEED;
        } else if (rightBumper && currentPosition > (MIN_HEIGHT_INCHES + 0.5)) {
            SmartDashboard.putBoolean("Controller: Right Bumper", true);
            speed = -Constants.ELEVATOR_SPEED;
        } else {
            speed = 0.0;
        }

        SmartDashboard.putNumber("Elevator Position (inches)", currentPosition);
        SmartDashboard.putBoolean("Elevator At Max Height", currentPosition >= MAX_HEIGHT_INCHES);
        SmartDashboard.putBoolean("Elevator At Min Height", currentPosition <= MIN_HEIGHT_INCHES);
        
        motor.set(speed);
    }
}
