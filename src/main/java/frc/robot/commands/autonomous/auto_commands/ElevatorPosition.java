package frc.robot.commands.autonomous.auto_commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorPosition extends Command {
    private final ElevatorSubsystem elevator;
    private final double targetRotations;
    private static final double TOLERANCE = 0.5; // Rotations tolerance
    
    public ElevatorPosition(ElevatorSubsystem elevator, double elevatorRotations) {
        this.elevator = elevator;
        this.targetRotations = elevatorRotations * Constants.ELEVATOR_GEAR_RATIO;
        addRequirements(elevator);
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        double current = elevator.getMotorRotations();
        double error = targetRotations - current;
        double speed = Math.abs(error) > TOLERANCE ? 
                      Math.signum(error) * Constants.ELEVATOR_SPEED : 0.0;
        
        elevator.setSpeedManual(speed);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(elevator.getMotorRotations() - targetRotations) <= TOLERANCE;
    }

    @Override
    public void end(boolean interrupted) {
        elevator.setSpeedManual(0);
    }
}