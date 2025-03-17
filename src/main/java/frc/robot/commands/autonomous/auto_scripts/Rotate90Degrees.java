package frc.robot.commands.autonomous.auto_scripts;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.auto_commands.Algae_SpinMotor;
import frc.robot.commands.autonomous.auto_commands.Drivetrain_GyroTurn;

public class Rotate90Degrees extends SequentialCommandGroup {

  public Rotate90Degrees() {
    addCommands(new Drivetrain_GyroTurn(90));
    addCommands(new Algae_SpinMotor(1000));
  }
}