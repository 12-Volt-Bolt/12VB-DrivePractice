package frc.robot.commands.autonomous.auto_commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralSubsystem;

public class Coral_MoveToAngle extends Command {
    private final double targetDegrees;
    private final double GEAR_RATIO = 10.0;
    private final double TOLERANCE = 2.0; // Degrees tolerance
    
    public Coral_MoveToAngle(double degrees) {
        targetDegrees = degrees;
        addRequirements(CoralSubsystem.get());
    }

    @Override
    public void initialize() {
        // Convert degrees to motor rotations (1:10 gear ratio)
        double motorRotations = (targetDegrees - 90) * GEAR_RATIO / 360;
        CoralSubsystem.get().getMotor().getEncoder().setPosition(0);
        CoralSubsystem.get().getMotor().getPIDController().setReference(
            motorRotations, 
            com.revrobotics.CANSparkBase.ControlType.kPosition
        );
    }

    @Override
    public boolean isFinished() {
        // Get current position in rotations and convert back to degrees
        double currentRotations = CoralSubsystem.get().getMotor().getEncoder().getPosition();
        double currentDegrees = (currentRotations * 360 / GEAR_RATIO) + 90;
        return Math.abs(currentDegrees - targetDegrees) <= TOLERANCE;
    }

    @Override
    public void end(boolean interrupted) {
        CoralSubsystem.get().getMotor().stopMotor();
    }
}