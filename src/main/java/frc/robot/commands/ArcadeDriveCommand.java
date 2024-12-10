package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

// This command will control the drive subsystem by reading the joystick values and sending them to the motors.
public class ArcadeDriveCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final XboxController controller;

    public ArcadeDriveCommand(DriveSubsystem subsystem, XboxController controller) {
        this.driveSubsystem = subsystem;
        this.controller = controller;
        addRequirements(subsystem);  // Declares subsystem dependencies
    }

    @Override
    public void execute() {
        // Get joystick inputs and drive the robot
/*         double leftSpeed = -controller.getLeftY();
        double rightSpeed = -controller.getRightY();
        driveSubsystem.tankDrive(leftSpeed, rightSpeed);  */
        double speed = -controller.getLeftY();
        double rotation = controller.getRightX();
        driveSubsystem.arcadeDrive(speed, rotation);
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the robot when the command ends
        driveSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;  // Runs until interrupted
    }
}
