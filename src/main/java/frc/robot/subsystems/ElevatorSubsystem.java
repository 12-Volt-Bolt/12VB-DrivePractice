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
    
    private static final double[] PRESET_LEVELS = {-0.1, -2, -3.6};
    private int currentLevel = 0;
    private static final double TOLERANCE = 5;

    public double getMotorRotations() {
        return motor.getEncoder().getPosition();
    }
    
    public void setMotorSpeed(double speed) {
        motor.set(speed);
    }

    @Override
    public void periodic() {
        boolean rightBumper = controller.getRawButtonPressed(6);
        boolean leftBumper = controller.getRawButtonPressed(5);
        double currentRotations = getMotorRotations();

        if (rightBumper) {
            currentLevel = Math.min(currentLevel + 1, PRESET_LEVELS.length - 1);
        } else if (leftBumper) {
            currentLevel = Math.max(currentLevel - 1, 0);
        }

        double targetRotations = PRESET_LEVELS[currentLevel] * GEAR_RATIO;
        double error = targetRotations - currentRotations;
        
        if (Math.abs(error) > TOLERANCE) {
            setMotorSpeed(Math.signum(error) * Constants.ELEVATOR_SPEED);
        } else {
            setMotorSpeed(0);
        }

        SmartDashboard.putNumber("Elevator Current Level", currentLevel);
        SmartDashboard.putNumber("Elevator Target Position", targetRotations);
        SmartDashboard.putNumber("Elevator Position (rotations)", currentRotations / GEAR_RATIO);
    }
}
