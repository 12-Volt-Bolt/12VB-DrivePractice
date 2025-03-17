package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;

public class EnableGyro extends SequentialCommandGroup {

  public EnableGyro() {
    Constants.GYRO_ENABLED = true;
  }
}