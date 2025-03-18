package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import frc.robot.Robot;

public class AprilTagAlignSubsystem extends SubsystemBase {
    private final XboxController controller = new XboxController(0);
    private final DriveSubsystem drivetrain = Robot.m_driveSubsystem;
    
    private static final double kP = 0.03;
    private static final double MAX_POWER = 0.2;
    
    public AprilTagAlignSubsystem() {}

    private static final double DESIRED_DISTANCE_METERS = 0.5;
    private static final double kP_STRAFE = 0.1;
    private static final double kP_FORWARD = 0.1;
    private static final double kP_ROTATION = 0.03;
    private static final double MAX_STRAFE = 0.2;
    private static final double MAX_FORWARD = 0.2;
    private static final double MAX_ROTATION = 0.2;
    private static final double DEADBAND = 0.05;

    @Override
    public void periodic() {
        if (controller.getYButton()) {
            if (LimelightHelpers.getTV("limelight_front")) {
                double[] targetPose = LimelightHelpers.getTargetPose_RobotSpace("limelight_front");
                
                double strafeError = -targetPose[0];
                double forwardError = DESIRED_DISTANCE_METERS - targetPose[2];
                double rotationError = -LimelightHelpers.getTX("limelight_front");

                // Rest of the calculations remain the same
                double strafeCorrection = clamp(strafeError * kP_STRAFE, MAX_STRAFE);
                double forwardCorrection = clamp(forwardError * kP_FORWARD, MAX_FORWARD);
                double rotationCorrection = clamp(rotationError * kP_ROTATION, MAX_ROTATION);

                if (Math.abs(strafeCorrection) < DEADBAND) strafeCorrection = 0;
                if (Math.abs(forwardCorrection) < DEADBAND) forwardCorrection = 0;
                if (Math.abs(rotationCorrection) < DEADBAND) rotationCorrection = 0;

                // Update debug outputs
                SmartDashboard.putNumber("3D Distance Z", targetPose[2]);
                SmartDashboard.putNumber("3D Position X", targetPose[0]);

                drivetrain.driveCartesian(forwardCorrection, strafeCorrection, rotationCorrection);
            } else {
                stop();
            }
        } else {
            stop();
        }
    }
    
    public void stop() {
        drivetrain.driveCartesian(0, 0, 0);
    }
}
