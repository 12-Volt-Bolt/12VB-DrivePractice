// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Limelight;

public class ToggleLEDCommand extends InstantCommand {
  /** Creates a new ToggleLEDCommand. */
  public ToggleLEDCommand(Limelight limelightSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    super(limelightSubsystem::toggleLEDs);
  }

}
