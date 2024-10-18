package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {
    private final DriveSubsystem driveSubsystem = new DriveSubsystem();
    private final XboxController controller = new XboxController(Constants.CONTROLLER_PORT);

    public RobotContainer() {
        // Configure default commands
        driveSubsystem.setDefaultCommand(new ArcadeDriveCommand(driveSubsystem, controller));
    }

    // Optionally, define autonomous command here
    public Command getAutonomousCommand() {
        // Return an autonomous command if needed
        return null;
    }

/** Used for defining button actions. */
public void configureButtonBindings() {

// controller.getXButton().onTrue(new InstantCommand(() -> swerve.zeroGyro()));
// controller.getXButtonPressed().onTrue(new InstantCommand(() -> swerve.zeroGyro()));
// controller.getXButtonReleased().onTrue(new InstantCommand(() -> swerve.zeroGyro()));
// controller.getLeftBumper().onTrue(leds.switchLEDMode());

// controller.getRawButton(1).whileTrue(intake.stopIntake());
// controller.getRawButton(2).onTrue(superstructure.enableManualControl());
// controller.getRawButton(3).whileTrue(intake.intakeGamePiece());
// controller.getRawButton(4).whileTrue(intake.outtakeGamePiece());
// controller.getRawButton(5).onTrue(superstructure.goToPreset(ScoringEnum.STOW));
// controller.getRawButton(6).onTrue(superstructure.goToPreset(ScoringEnum.INTAKE));
// controller.getRawButton(7).onTrue(superstructure.goToPreset(ScoringEnum.SCORE_CONE_L2));
// controller.getRawButton(8).onTrue(superstructure.goToPreset(ScoringEnum.SCORE_CONE_L3));
// controller.getRawButton(9).whileTrue(intake.halfOuttakeGamePiece());
// controller.getRawButton(10).onTrue(superstructure.goToPreset(ScoringEnum.SCORE_CUBE_L3));
// controller.getRawButton(11).onTrue(superstructure.goToPreset(ScoringEnum.CONE_PLAYER_STATION));
// controller.getRawButton(12).onTrue(superstructure.goToPreset(ScoringEnum.CUBE_PLAYER_STATION));
}

}
