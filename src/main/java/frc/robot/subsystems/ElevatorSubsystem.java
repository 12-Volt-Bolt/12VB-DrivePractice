package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
    private final SparkMax motor = new SparkMax(Constants.ELEVATOR_MOTOR_ID, MotorType.kBrushless);
    
    // Add these methods to properly encapsulate motor access
    public double getMotorRotations() {
        return motor.getEncoder().getPosition();
    }
    
    public void setMotorSpeed(double speed) {
        motor.set(speed);
    }

    @Override
    public void periodic() {
        // Fix incorrect motor reference (changed from 'motor' to actual field name)
        double currentRotations = this.motor.getEncoder().getPosition();

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
