package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

public class AprilTagAlignSubsystem extends SubsystemBase {
    private final XboxController controller = new XboxController(0);
    private final DriveSubsystem drivetrain = Robot.m_driveSubsystem;
    private final PhotonCamera camera = new PhotonCamera("photonvision");
    
    public AprilTagAlignSubsystem() {}

    @Override
    public void periodic() {
        if (controller.getYButton()) {
            PhotonPipelineResult result = camera.getLatestResult();
            boolean hasTarget = result.hasTargets();
            
            if (hasTarget) {
                PhotonTrackedTarget target = null;
                for (PhotonTrackedTarget t : result.getTargets()) {
                    if (t.getFiducialId() == Constants.Vision.DESIRED_TAG_ID) {
                        target = t;
                        break;
                    }
                }
                
                if (target != null) {
                    double yaw = target.getYaw();
                    double pitch = target.getPitch();
                    
                    double range = PhotonUtils.calculateDistanceToTargetMeters(
                        Constants.Vision.CAMERA_HEIGHT_METERS,
                        Constants.Vision.TARGET_HEIGHT_METERS,
                        Constants.Vision.CAMERA_PITCH_RADIANS,
                        Units.degreesToRadians(pitch)
                    );
                    
                    double turnCorrection = (Constants.Vision.DESIRED_YAW_DEGREES - yaw) * Constants.Vision.TURN_kP;
                    double strafeCorrection = (Constants.Vision.DESIRED_RANGE_METERS - range) * Constants.Vision.STRAFE_kP;
                    
                    turnCorrection = Math.copySign(Math.min(Math.abs(turnCorrection), 0.2), turnCorrection);
                    strafeCorrection = Math.copySign(Math.min(Math.abs(strafeCorrection), 0.2), strafeCorrection);
                    
                    SmartDashboard.putNumber("Vision/Yaw", yaw);
                    SmartDashboard.putNumber("Vision/Range", range);
                    SmartDashboard.putNumber("Vision/Turn Correction", turnCorrection);
                    SmartDashboard.putNumber("Vision/Strafe Correction", strafeCorrection);
                    
                    drivetrain.driveCartesian(strafeCorrection, 0, -turnCorrection);
                    return;
                }
            }
            stop();
        } else {
            stop();
        }
    }
    
    public void stop() {
        drivetrain.driveCartesian(0, 0, 0);
    }
}