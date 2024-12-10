package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.CommandBase;
/*
 * 1. Configure the Limelight for AprilTags
Update the Limelight firmware and software:

Make sure your Limelight is running the latest firmware that supports AprilTags (Limelight OS 2023+).
Download and install the Limelight Web Interface from the Limelight website.
Enable AprilTag detection:

Access the Limelight configuration interface via its IP address (e.g., http://10.TE.AM.11).
Under the pipeline settings, set the target type to "AprilTag" and select the family of tags you’re using (e.g., Tag16h5).
 */
public class DriveToAprilTag extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final Limelight limelightSubsystem;

    public DriveToAprilTag(DriveSubsystem driveSubsystem, Limelight limelightSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        addRequirements(driveSubsystem, limelightSubsystem);
    }

    /*The Limelight can provide a robot's pose relative to the AprilTag using the botpose field. This returns an array:

      [x, y, z]: Translation (in meters).
      [roll, pitch, yaw]: Rotation (in degrees).

        @Override
    public void execute() {
        double[] pose = limelightSubsystem.getPose();
        if (pose.length == 6) {
            double x = pose[0]; // Forward distance
            double y = pose[1]; // Side distance
            double yaw = pose[5]; // Orientation
            
            double speed = Math.max(0.1, 0.5 - Math.abs(x) * 0.2);
            double turn = yaw * 0.05; // Adjust turn based on yaw
            
            driveSubsystem.arcadeDrive(speed, turn);
        } else {
            driveSubsystem.stop();
        }
    }
     */
    @Override
    public void execute() {
        double tx = limelightSubsystem.getTx();
        double ta = limelightSubsystem.getTa();
        double tid = limelightSubsystem.getTid();

        if (tid != -1) { // Tag detected
            double turn = tx * 0.05; // Proportional turning
            double speed = Math.max(0.1, 0.5 - ta * 0.1); // Approach based on area
            driveSubsystem.arcadeDrive(speed, turn);
        } else {
            driveSubsystem.stop(); // No tag detected
        }
    }
  
    
    @Override
    public boolean isFinished() {
        // Add logic to determine when to stop
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }
}