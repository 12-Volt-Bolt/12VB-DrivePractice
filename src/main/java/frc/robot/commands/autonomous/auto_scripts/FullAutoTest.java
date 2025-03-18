package frc.robot.commands.autonomous.auto_scripts;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.auto_commands.Algae_SpinMotor;
import frc.robot.commands.autonomous.auto_commands.Coral_MoveToAngle;
import frc.robot.commands.autonomous.auto_commands.Drivetrain_GyroStraight;
import frc.robot.commands.autonomous.auto_commands.Drivetrain_GyroTurn;
import frc.robot.commands.autonomous.auto_commands.ElevatorPosition;
import frc.robot.subsystems.ElevatorSubsystem;

public class FullAutoTest extends SequentialCommandGroup {
    
    public FullAutoTest(ElevatorSubsystem elevatorSubsystem) {
        addCommands(new ElevatorPosition(elevatorSubsystem, 10));
        addCommands(new Algae_SpinMotor(2, true));
    }
}