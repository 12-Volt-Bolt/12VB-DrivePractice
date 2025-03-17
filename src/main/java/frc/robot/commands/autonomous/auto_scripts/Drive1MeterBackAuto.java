package frc.robot.commands.autonomous.auto_scripts;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.auto_commands.Drivetrain_GyroStraight;

public class Drive1MeterBackAuto extends SequentialCommandGroup {

  public Drive1MeterBackAuto() {
    addCommands(new Drivetrain_GyroStraight(1.0, -0.3));
  }
}