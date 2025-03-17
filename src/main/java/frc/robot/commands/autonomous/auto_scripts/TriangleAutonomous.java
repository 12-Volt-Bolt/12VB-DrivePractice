package frc.robot.commands.autonomous.auto_scripts;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.auto_commands.Drivetrain_GyroStraight;
import frc.robot.commands.autonomous.auto_commands.Drivetrain_GyroTurn;

public class TriangleAutonomous extends SequentialCommandGroup{
    public TriangleAutonomous(){


        addCommands(new Drivetrain_GyroStraight(1.0, 0.3));
        addCommands(new Drivetrain_GyroTurn(120));
        addCommands(new Drivetrain_GyroStraight(1.0, 0.3));
        addCommands(new Drivetrain_GyroTurn(120));
        addCommands(new Drivetrain_GyroStraight(1.0, 0.3));
        addCommands(new Drivetrain_GyroTurn(120)); 
        // Idk if this will work, but its a simple loop to shorten all that code above
        /* for (int i = 0; i < 3; i++) {
            addCommands(new Drivetrain_GyroStraight(0.5, 0.2), new Drivetrain_GyroTurn(120));
        } */
    }
}