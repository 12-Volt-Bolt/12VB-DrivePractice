package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;

public class DisableGyro extends SequentialCommandGroup {

  public DisableGyro() {
    Constants.GYRO_ENABLED = false;
  }
}