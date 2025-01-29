package main.java.frc.robot.commands.autonomous.auto_scripts;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.basic_path_planning.Drivetrain_GyroStraight;
import frc.robot.commands.autonomous.basic_path_planning.Drivetrain_GyroTurn;

public class FullAutoTest extends SequentialCommandGroup {

  public FullAutoTest() {
    addCommands(new Drivetrain_GyroStraight(1.1, 0.2));
    //addCommands(new Drivetrain_GyroTurn(132));
    addCommands(new Drivetrain_GyroStraight(0.8, 0.2));
    //addCommands(new Drivetrain_GyroTurn(123));
    addCommands(new Drivetrain_GyroStraight(0.7, 0.1));
    //turn
    addCommands(new Drivetrain_GyroStraight(1.5, 0.3));
    //turn
    addCommands(new Drivetrain_GyroStraight(1.7, 0.3));
    //turn
    addCommands(new Drivetrain_GyroStraight(0.6, 0.1));
  }
}