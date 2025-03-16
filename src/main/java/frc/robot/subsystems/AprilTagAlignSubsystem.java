package main.java.frc.robot.subsystems;

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

    @Override
    public void periodic() {
        if (controller.getYButton()) {
            double tx = LimelightHelpers.getTX("");
            double correction = tx * kP;
            correction = Math.copySign(Math.min(Math.abs(correction), MAX_POWER), correction);
                
            SmartDashboard.putBoolean("AprilTag Detected", LimelightHelpers.hasTarget(""));
            SmartDashboard.putNumber("AprilTag TX", tx);
            SmartDashboard.putNumber("Alignment Correction", correction);
                
            drivetrain.driveCartesian(0, 0, -correction);
        } else {
            stop();
        }
    }
    
    public void stop() {
        drivetrain.driveCartesian(0, 0, 0);
    }
}