package frc.robot.commands.autonomous.auto_scripts;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.basic_path_planning.Drivetrain_GyroStraight;

public class Drive1MeterAuto extends SequentialCommandGroup {

  public Drive1MeterAuto() {
    addCommands(new Drivetrain_GyroStraight(1.0, 0.3));
  }
}