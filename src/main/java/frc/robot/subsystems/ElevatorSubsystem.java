package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
    public static final SparkMax elevatorMotor = new SparkMax(Constants.ELEVATOR_MOTOR_ID, MotorType.kBrushless);
    private final XboxController controller = new XboxController(0);
    
    private static final double GEAR_RATIO = Constants.ELEVATOR_GEAR_RATIO;
    private static final double SPOOL_DIAMETER_INCHES = Constants.ELEVATOR_SPOOL_DIAMETER; // dont end up using this.
    private static final double MAX_HEIGHT_INCHES = Constants.ELEVATOR_MAX_HEIGHT;
    private static final double MIN_HEIGHT_INCHES = Constants.ELEVATOR_MIN_HEIGHT;
    
    private static final double[] PRESET_LEVELS = {0, 5, 8};
    private int currentLevel = 0;

    @Override
    public void periodic() {
        boolean rightBumper = controller.getRawButtonPressed(6);
        boolean leftBumper = controller.getRawButtonPressed(5);
        double currentRotations = motor.getEncoder().getPosition();

        if (rightBumper) {
            currentLevel = Math.min(currentLevel + 1, PRESET_LEVELS.length - 1);
        } else if (leftBumper) {
            currentLevel = Math.max(currentLevel - 1, 0);
        }

        double targetRotations = PRESET_LEVELS[currentLevel] * GEAR_RATIO;
        double error = targetRotations - currentRotations;
        
        double speed = Math.abs(error) > 0.1 ? Math.signum(error) * Constants.ELEVATOR_SPEED : 0.0;
        
        SmartDashboard.putNumber("Elevator Current Level", currentLevel);
        SmartDashboard.putNumber("Elevator Target Position", targetRotations);
        SmartDashboard.putNumber("Elevator Position (rotations)", currentRotations / GEAR_RATIO);
        
        motor.set(speed);
    }
}
