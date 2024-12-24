package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

  private boolean limelightOn = false;
  private boolean userControl = true;
  private final NetworkTable limelightTable;

  public Limelight() {
    limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  }

  @Override
  public void periodic() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(limelightOn ? 0 : 1);
  }

  public double getVerOffset() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
  }

  public double getHorOffset() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  }

  /**
   * Toggles the LEDs in the Limelight.
   * 
   * @return True if the LED were turned on, false if they were turned off.
   * @author Lucas Brunner
   */
  public boolean toggleLEDs() {
    limelightOn = !limelightOn;
    return limelightOn;
  }

  /**
   * Toggles the LEDs in the Limelight. If userControl is set to false the input
   * is ignored.
   * 
   * @return True if the LED were turned on, false if they were turned off.
   * @author Lucas Brunner
   */
  public boolean toggleLEDsUser() {
    if (userControl == true) {
      limelightOn = !limelightOn;
    }
    return limelightOn;
  }

  /**
   * Sets whether the user can control the intake.
   * 
   * @param state The state to be set to.
   * @author Lucas Brunner
   */
  public void setUserControl(boolean state) {
    userControl = state;
  }

  /**
   * Sets the state of the LEDs on the Limelight.
   * 
   * @param state The state the LEDs will be set to.
   * @author Lucas Brunner
   */
  public void setLEDs(boolean state) {
    limelightOn = state;
  }

  /**
   * @return True if the LED were turned on, false if they were turned off.
   * @author Lucas Brunner
   */
  public boolean LEDState() {
    return limelightOn;
  }

  /* */
  public double getTx() {
    return limelightTable.getEntry("tx").getDouble(0);
  }

  public double getTy() {
    return limelightTable.getEntry("ty").getDouble(0);
  }

  public double getTa() {
    return limelightTable.getEntry("ta").getDouble(0);
  }

  public double getTid() {
    return limelightTable.getEntry("tid").getDouble(-1); // Default to -1 if no tag detected
  }

  public double[] getPose() {
    return limelightTable.getEntry("botpose").getDoubleArray(new double[6]); // Pose estimation
  }

  /*
   * Support Align to Target Command
   * 
   */

   public static boolean hasTarget() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) > 0;
}

public static double getHorizontalOffset() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
}

public double getVerticalOffset() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
}

public double getTargetArea() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
} 
}
