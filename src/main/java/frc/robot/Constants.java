
package frc.robot;

import edu.wpi.first.math.util.Units;

public final class Constants {
    // Gyro
    public static boolean GYRO_ENABLED = true;
    public static final double GYRO_TURN_KI = 0.001;
    public static final double GYRO_TURN_KD = 0.1;    // Helps prevent overshoot
    public static final double GYRO_TURN_VELOCITY_THRESHOLD = 2.0;  // deg/sec
    public static final double GYRO_TURN_MAX_INTEGRAL = 10.0;
    public static final double GYRO_STRAIGHT_KP = 0.03;    // Start with original value
    public static final double GYRO_STRAIGHT_KI = 0.001;   // Start with small value
    public static final double GYRO_STRAIGHT_KD = 0.1;     // Helps maintain heading
    public static final double GYRO_STRAIGHT_VELOCITY_THRESHOLD = 0.2;  // Revolutions/sec
    public static final double GYRO_STRAIGHT_MAX_INTEGRAL = 5.0;

    // Physical Robot Constants //
    public static final double WHEEL_DIAMETER = Units.inchesToMeters(6); // Convert from inches to meters
    public static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER; // Measured in meters
	public static final double TRACK_WIDTH = Units.inchesToMeters(22.5); // Distance between centers of right and left wheels on robot
    public static final double WHEEL_BASE = Units.inchesToMeters(20.375); // Distance between centers of front and back wheels on robot

    // Controller Input Axes //
    public static final int CONTROLLER_USB_PORT_ID = 0; // USB port that the controller is plugged in to
    public static final int RIGHT_VERTICAL_JOYSTICK_AXIS = 4;
    public static final int RIGHT_HORIZONTAL_JOYSTICK_AXIS = 3;
    public static final int LEFT_VERTICAL_JOYSTICK_AXIS = 1;
    public static final int LEFT_HORIZONTAL_JOYSTICK_AXIS = 0;
    public static final int X_BUTTON = 3;
    public static final int A_BUTTON = 1;
    public static final int B_BUTTON = 2;
    public static final int Y_BUTTON = 4;
    public static final int LEFT_BUMPER = 5;
    public static final int RIGHT_BUMPER = 6;
    public static final int LEFT_TRIGGER_AXIS = 7;
    public static final int RIGHT_TRIGGER_AXIS = 8;
    public static final int PREV_BUTTON = 9;
    public static final int START_BUTTON = 10;
    public static final int LEFT_STICK_BUTTON = 9;
    public static final int RIGHT_STICK_BUTTON = 10;

    // Spark MAX CAN IDs //
    public static final int LEFT_FRONT_DRIVE_MOTOR_ID = 1; // NEO motor
    public static final int RIGHT_FRONT_DRIVE_MOTOR_ID = 2; // NEO motor
    public static final int LEFT_REAR_DRIVE_MOTOR_ID = 3; // NEO motor
    public static final int RIGHT_REAR_DRIVE_MOTOR_ID = 4; // NEO motor
    public static final int ELEVATOR_MOTOR_ID = 12; // NEO motor
    public static final int ALGAE_LIFT_ID = 6;
    public static final int ALGAE_MOTOR_LEFT = 9;
    public static final int ALGAE_MOTOR_RIGHT = 10;
    public static final int CORAL_MOTOR_ID = 5;
    

    // Elevator
    public static final double ELEVATOR_SPEED = 1.5;
    public static final double ELEVATOR_GEAR_RATIO = 60.0; // This is 60:1 ratio
    public static final double ELEVATOR_SPOOL_DIAMETER = 1.5; // Inches
    public static final double ELEVATOR_MAX_HEIGHT = 10.0; // Inches
    public static final double ELEVATOR_MIN_HEIGHT = 0; // Inches
    public static final double ELEVATOR_KP = 0.05;
    public static final double ELEVATOR_KI = 0.001;
    public static final double ELEVATOR_KD = 0.01;

    // Drivetrain Constants //
    public static final double DRIVE_ACCELERATION_LIMIT = 3;
    public static final double DRIVE_ROTATION_ACCELERATION_LIMIT = 2;
    public static final double kMAX_TURN_SPEED = 0.8;
    public static final double SPEED_LIMIT = 0.5;
    public static final double DRIVE_GEAR_RATIO = 12.75;
    public static final boolean REVERSE_LEFT_FRONT_MOTOR = true;
    public static final boolean REVERSE_LEFT_BACK_MOTOR = true;
    public static final boolean REVERSE_RIGHT_FRONT_MOTOR = false;
    public static final boolean REVERSE_RIGHT_BACK_MOTOR = false;
    public static final double GYRO_TURN_KP = 0.007; // P (Proportional) constant of a PID loop
    public static final double TURNING_THRESHOLD_DEGREES = 3;
    public static final double MAX_POWER_GYRO = 0.4;
    public static final double kP_FRONT_RIGHT_VELOCITY = 0.0010269;
	public static final double kP_FRONT_LEFT_VELOCITY = 0.0010269;
	public static final double kP_BACK_RIGHT_VELOCITY = 0.0010269;
	public static final double kP_BACK_LEFT_VELOCITY = 0.0010269;
	public static final double kP_X_CONTROLLER = 9.6421;
    public static final double kP_Y_CONTROLLER = 9.6421;
    public static final double kP_THETA_CONTROLLER = 9.6421;
	public static final double kMAX_ANGULAR_SPEED_RADIANS_PER_SECOND = 2*Math.PI;
	public static final double kMAX_ANGULAR_ACCELERATION_RADIANS_PER_SECOND_SQUARED = 2*Math.PI;

    // Apriltag Vision Constants //
    public static final double CAMERA_HEIGHT_METERS = Units.inchesToMeters(7);
    public static final double TARGET_HEIGHT_METERS = Units.inchesToMeters(18.5);
    public static final double CAMERA_PITCH_RADIANS = Units.degreesToRadians(18);
    public static final String USB_CAMERA_NAME = "Arducam_OV9782_USB_Camera";
}