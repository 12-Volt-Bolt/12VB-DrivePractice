package frc.robot.commands.autonomous.auto_scripts;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.auto_commands.Drivetrain_GyroStraight;
import frc.robot.commands.autonomous.auto_commands.Drivetrain_GyroTurn;

public class FullAutoTest extends SequentialCommandGroup {

  public FullAutoTest() {
    

    
    addCommands(new Drivetrain_GyroStraight(0.5, 0.2));
    addCommands(new Drivetrain_GyroTurn(120));
    addCommands(new Drivetrain_GyroStraight(0.5, 0.2));
    addCommands(new Drivetrain_GyroTurn(120));
    addCommands(new Drivetrain_GyroStraight(0.5, 0.2));
    addCommands(new Drivetrain_GyroTurn(120));
    addCommands(new Drivetrain_GyroStraight(0.5, 0.2));
    addCommands(new Drivetrain_GyroTurn(120));
    }
}