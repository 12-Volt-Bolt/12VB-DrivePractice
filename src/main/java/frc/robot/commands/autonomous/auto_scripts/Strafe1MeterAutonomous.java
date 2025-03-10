package frc.robot.commands.autonomous.auto_scripts;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.auto_commands.Drivetrain_GyroStrafe;
import frc.robot.commands.autonomous.auto_commands.Drivetrain_GyroStraight;



/** Autonomous Mode (Default) ******************************************************
 * This basic autonomous routine drives forward 1 meter using encoder feedback */
public class Strafe1MeterAutonomous extends SequentialCommandGroup {

  public Strafe1MeterAutonomous() {
    addCommands(new Drivetrain_GyroStrafe(1.0, 0.3));
  }
}