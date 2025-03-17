// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.AprilTagAlignSubsystem;

public class AlgaeSubsystem extends SubsystemBase {
  public final static SparkMax MotorLeft = new SparkMax(Constants.ALGAE_MOTOR_LEFT, MotorType.kBrushless);
  public final static SparkMax MotorRight = new SparkMax(Constants.ALGAE_MOTOR_RIGHT, MotorType.kBrushless);
  private final SparkMax MotorLiftUp = new SparkMax(Constants.ALGAE_LIFT_ID, MotorType.kBrushless);

  private final XboxController controller = new XboxController(0);

  private double liftspeed = 0.0;
  private double speed = 0.0;
  private boolean IS_X_TOGGLED = false;
  public AlgaeSubsystem() {}

  @Override
  public void periodic() {
      boolean A_BUTTON = controller.getAButton();
      boolean B_BUTTON = controller.getBButton();
      boolean X_BUTTON = controller.getXButtonPressed();
      boolean Y_BUTTON = controller.getYButton();

      SmartDashboard.putBoolean("A button", A_BUTTON);
      SmartDashboard.putBoolean("B button", B_BUTTON);
      SmartDashboard.putBoolean("X Toggle State", IS_X_TOGGLED);

      if (X_BUTTON) {
        IS_X_TOGGLED = !IS_X_TOGGLED;
      }
      
      liftspeed = IS_X_TOGGLED ? 0.2 : 0.0;

      if (A_BUTTON && B_BUTTON) {
        speed = 0.0;
      } else if (A_BUTTON) {
        speed = 1.0;
      } else if (B_BUTTON) {
        speed = -1.0;
      } else {
        speed = 0.0;
      }

      MotorLiftUp.set(liftspeed);
      MotorLeft.set(speed * -1);
      MotorRight.set(speed);
  }
}
