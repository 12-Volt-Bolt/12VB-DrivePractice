package frc.robot.commands.autonomous.auto_scripts;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.basic_path_planning.Drivetrain_GyroStraight;
import frc.robot.commands.autonomous.basic_path_planning.Drivetrain_GyroTurn;

public class SquareAutonomous extends SequentialCommandGroup{
    public SquareAutonomous(){
        addCommands(new Drivetrain_GyroStraight(0.5, 0.2));
        addCommands(new Drivetrain_GyroTurn(90));
        addCommands(new Drivetrain_GyroStraight(0.5, 0.2));
        addCommands(new Drivetrain_GyroTurn(90));
        addCommands(new Drivetrain_GyroStraight(0.5, 0.2));
        addCommands(new Drivetrain_GyroTurn(90));
        addCommands(new Drivetrain_GyroStraight(0.5, 0.2));
        addCommands(new Drivetrain_GyroTurn(90));
        // Idk if this will work, but its a simple loop to shorten all that code above
        /* for (int i = 0; i < 4; i++) {
            addCommands(new Drivetrain_GyroStraight(0.5, 0.2), new Drivetrain_GyroTurn(90));
        } */
    }
}