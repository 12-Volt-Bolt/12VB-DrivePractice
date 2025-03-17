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
    private static final double kP_X = 0.1;
    private static final double kP_Y = 0.1;
    private static final double MAX_FORWARD_POWER = 0.2;
    private static final double MAX_STRAFE_POWER = 0.2;

    @Override
    public void periodic() {
        if (controller.getYButton()) {
            // dw these comments are jokes
            if (LimelightHelpers.getTV("limelight_front")) {
                LimelightResults results = LimelightHelpers.getLatestResults("limelight_front");
                
                // Get the first detected fiducial (AprilTag)
                if (results.targets_Fiducials.length > 0) {
                    LimelightTarget_Fiducial target = results.targets_Fiducials[0];
                    Pose3d targetPose = target.getTargetPose_RobotSpace();
                    
                    // uh so this i think is the x and y position of the april tag relative to the robot but idrk
                    double currentX = targetPose.getTranslation().getX();
                    double currentY = targetPose.getTranslation().getY();
                    double tx = target.tx;

                // yeah i learned this in fish class
                double xError = currentX - DESIRED_DISTANCE_METERS;
                double correctionX = xError * kP_X;
                correctionX = Math.copySign(Math.min(Math.abs(correctionX), MAX_FORWARD_POWER), correctionX);

                // x axis?
                double correctionY = currentY * kP_Y;
                correctionY = Math.copySign(Math.min(Math.abs(correctionY), MAX_STRAFE_POWER), correctionY);

                // oh nvm this has to be the x axis, no way its not
                double correctionZ = tx * kP;
                correctionZ = Math.copySign(Math.min(Math.abs(correctionZ), MAX_POWER), correctionZ);

                // hmm maybe isaiah would know
                if (currentX < DESIRED_DISTANCE_METERS) {
                    correctionX = Math.min(correctionX, 0);
                }

                SmartDashboard.putNumber("AprilTag X Error", xError);
                SmartDashboard.putNumber("AprilTag Y Position", currentY);
                SmartDashboard.putNumber("Alignment Correction X", correctionX);
                SmartDashboard.putNumber("Alignment Correction Y", correctionY);

                drivetrain.driveCartesian(correctionY, correctionX, -correctionZ);
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