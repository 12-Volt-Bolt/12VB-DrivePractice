// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous.auto_commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.AlgaeSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Algae_SpinMotor extends Command {
  private final double duration;
  private final boolean forward;
  private double startTime;

  public Algae_SpinMotor(double seconds, boolean forwardDirection) {
    duration = seconds;
    forward = forwardDirection;
  }

  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
    double leftSpeed = forward ? -1.0 : 1.0;
    double rightSpeed = forward ? 1.0 : -1.0;
    AlgaeSubsystem.MotorLeft.set(leftSpeed);
    AlgaeSubsystem.MotorRight.set(rightSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    AlgaeSubsystem.MotorLeft.set(0.0);
    AlgaeSubsystem.MotorRight.set(0.0);
  }

  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() - startTime >= duration;
  }
}
