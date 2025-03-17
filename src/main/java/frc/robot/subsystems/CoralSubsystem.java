// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class CoralSubsystem extends SubsystemBase {
  public final static SparkMax motor = new SparkMax(Constants.CORAL_MOTOR_ID, MotorType.kBrushless);
  private final static XboxController controller = new XboxController(0);

  private double speed = 0.0;
  public CoralSubsystem() {}

  public boolean getDPadUp() {
    int dPadValue = controller.getPOV();
    var direction = 0;
    return (dPadValue == direction) || (dPadValue == (direction + 45) % 360)
        || (dPadValue == (direction + 315) % 360);
  }

  public boolean getDPadDown() {
    int dPadValue = controller.getPOV();
    var direction = 180;
    return (dPadValue == direction) || (dPadValue == (direction + 45) % 360)
        || (dPadValue == (direction + 315) % 360);
  }

  @Override
  public void periodic() {

    boolean upDpad = getDPadUp();
    boolean downDpad = getDPadDown();

    SmartDashboard.putBoolean("Controller: Down Dpad", downDpad);
    SmartDashboard.putBoolean("Controller: Up Dpad", upDpad);

    if (upDpad) {
      speed = 0.1;
    } else if (downDpad) {
      speed = -0.1;
    } else {
      speed = 0.0;
    }

    motor.set(speed);
  }
}
