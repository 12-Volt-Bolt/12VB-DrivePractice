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
    private double speed = 0.0;
    @Override
    public void periodic() {
        boolean leftBumper = controller.getRawButton(5);
        boolean rightBumper = controller.getRawButton(6);
        
        if (leftBumper) {
            SmartDashboard.putBoolean("Controller: Left Bumper", true);
            speed = Constants.ELEVATOR_SPEED;
        } else if (rightBumper) {
            SmartDashboard.putBoolean("Controller: Right Bumper", true);
            speed = -Constants.ELEVATOR_SPEED;
        } else {
            SmartDashboard.putBoolean("Controller: Left Bumper", false);
            SmartDashboard.putBoolean("Controller: Right Bumper", false);
            speed = 0.0;
        }

        motor.set(speed);
    }
}
