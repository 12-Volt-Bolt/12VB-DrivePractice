/* package frc.robot.commands.autonomous.auto_commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorPosition extends Command {
    private final ElevatorSubsystem elevator;
    private final double targetRotations;
    private double startingPosition;
    private double lastError;
    private double integral;

    private static final double kP = Constants.ELEVATOR_KP;
    private static final double kI = Constants.ELEVATOR_KI;
    private static final double kD = Constants.ELEVATOR_KD;
    private static final double GEAR_RATIO = Constants.ELEVATOR_GEAR_RATIO;
    private static final double TOLERANCE = 0.5; // Rotations tolerance
    private static final double MAX_INTEGRAL = 0.5;

    public ElevatorPosition(double rotations) {
        ElevatorSubsystem = elevator;
        this.targetRotations = rotations * GEAR_RATIO; // Convert to motor rotations
        addRequirements(elevator);
    }

    @Override
    public void initialize() {
        startingPosition = elevator.getMotorRotations();
        lastError = targetRotations;
        integral = 0;
    }

    @Override
    public void execute() {
        double current = elevator.getMotorRotations() - startingPosition;
        double error = targetRotations - current;
        integral += error;
        integral = Math.max(Math.min(integral, MAX_INTEGRAL), -MAX_INTEGRAL);
        double derivative = error - lastError;
        
        double output = (kP * error) + (kI * integral) + (kD * derivative);
        output = Math.max(-1, Math.min(1, output)); // Clamp output
        
        elevator.setSpeedManual(output);
        lastError = error;
    }

    @Override
    public boolean isFinished() {
        double currentError = Math.abs(targetRotations - (elevator.getMotorRotations() - startingPosition));
        return currentError <= TOLERANCE && Math.abs(elevator.getMotorVelocity()) < 0.1;
    }

    @Override
    public void end(boolean interrupted) {
        elevator.setSpeedManual(0);
    }
} */