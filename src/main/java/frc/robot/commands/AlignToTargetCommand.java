package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Limelight;

public class AlignToTargetCommand extends CommandBase {
    private final DriveSubsystem drivetrain;
    private final Limelight limelight;

    public AlignToTargetCommand(DriveSubsystem drivetrain, Limelight limelight) {
        this.drivetrain = drivetrain;
        this.limelight = limelight;
        addRequirements(drivetrain, limelight);
    }

    @Override
    public void execute() {
        if (limelight.hasTarget()) {
            double xOffset = limelight.getHorizontalOffset();
            double area = limelight.getTargetArea();

            double rotationSpeed = xOffset * Constants.ALIGN_KP;
            double forwardSpeed = (1.0 - area) * Constants.FORWARD_KP;

            drivetrain.arcadeDrive(forwardSpeed, rotationSpeed);
        } else {
            drivetrain.stop();
        }
    }

    @Override
    public boolean isFinished() {
        return limelight.hasTarget() && Math.abs(limelight.getHorizontalOffset()) < 1.0;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }
}
