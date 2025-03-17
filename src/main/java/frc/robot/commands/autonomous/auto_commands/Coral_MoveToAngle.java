package frc.robot.commands.autonomous.auto_commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralSubsystem;

public class Coral_MoveToAngle extends Command {
    private final double targetDegrees;
    private final double GEAR_RATIO = 10.0;
    private final double TOLERANCE = 2.0; // Degrees tolerance
    private final double kP = 0.05; // Proportional control constant
    private final CoralSubsystem coralSubsystem;
    
    public Coral_MoveToAngle(double degrees, CoralSubsystem subsystem) {
        targetDegrees = degrees;
        coralSubsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        // Calculate current angle and control speed
        double currentRotations = CoralSubsystem.motor.getEncoder().getPosition();
        double currentDegrees = (currentRotations * 360 / GEAR_RATIO) + 90;
        double error = targetDegrees - currentDegrees;
        double speed = error * kP;
        
        // Clamp speed to ±30% for safety
        CoralSubsystem.motor.set(Math.max(-0.3, Math.min(0.3, speed)));
    }

    @Override
    public boolean isFinished() {
        double currentRotations = CoralSubsystem.motor.getEncoder().getPosition();
        double currentDegrees = (currentRotations * 360 / GEAR_RATIO) + 90;
        return Math.abs(currentDegrees - targetDegrees) <= TOLERANCE;
    }

    @Override
    public void end(boolean interrupted) {
        CoralSubsystem.motor.stopMotor();
    }
}