// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous.auto_commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Algae_SpinMotor extends Command {
  private int timeset = 0;
  private double speed = 0.0;
  private int ticks;

  /** Creates a new Algae_SpinMotor. */
  public Algae_SpinMotor(int tickamount) {
    ticks = tickamount;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timeset = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while (timeset < ticks) {
      speed = 1.0;
    }
    AlgaeSubsystem.MotorLeft.set(speed * -1);
    AlgaeSubsystem.MotorRight.set(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    speed = 0.0;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (ticks < timeset) {
      return true;
    } else {
      return false;
    }
  }
}
