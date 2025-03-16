package frc.robot.tools;

import edu.wpi.first.wpilibj.Joystick;

public class Equations {

    /**
     * Returns a -1 to 1 range based of the position of the left and right triggers. 
     * 
     * @param controller
     * 
     * @return If the left trigger is down: left trigger value, if the right trigger is down: right trigger value, else 0.
     */
    public static double triggersAsJoy(int axisID1, int axisID2, Joystick joy) {
        double axis1 = joy.getRawAxis(axisID1);
        double axis2 = joy.getRawAxis(axisID2);
        double output = 0;
        if (axis1 == 0 && axis2 == 0) 
        {
        } else if (axis1 > 0) {
            output = -axis1;
        } else if (axis2 > 0) {
            output = axis2;
        }
        return output;
    }

    /**
     * If the input is within a given amount of 0, the output is set to 0.
     * 
     * @param number
     * @param deadzone If the input is within this amount of 0 the output will be 0.
     * 
     * @return The input with a deadzone applied.
     */
    public static double deadzone(double number, double deadzone) {
        if (Math.abs(number) > Math.abs(deadzone)) {
            return number;
        } else {
            return 0;
        }
    }

    /**
     * If the input is within 0.1 of 0, the output is set to 0.
     * 
     * @param number
     * 
     * @return The input with a 0.1 deadzone applied.
     */
    public static double deadzone(double number) {
        return deadzone(number, 0.1);
    }

    /**
     * If the double input is outside the given range then it is reduced/increased to the max or min,
     * 
     * @param input
     * @param min
     * @param max
     * 
     * @return The clamped input.
     */
    public static double clamp(double input, double min, double max) {
        double output = 0;
        if (input > max) {
            output = max;
        } else if (input < min) {
            output = min;
        } else {
            output = input;
        }
        return output;
    }

    /**
     * If the integer input is outside the given range then it is reduced/increased to the max or min,
     * 
     * @param input
     * @param min
     * @param max
     * 
     * @return The clamped input.
     */
    public static int clamp(int input, int min, int max) {
        int output = 0;
        if (input > max) {
            output = max;
        } else if (input < min) {
            output = min;
        } else {
            output = input;
        }
        return output;
    }

    /**
     * Checks to see if the input is within the min/max range.
     * 
     * @param input
     * @param min
     * @param max
     * 
     * @return Whether the input is within the given range. (True/False)
     */
    public static boolean range(int input, int min, int max)
    {
        boolean output = false;
        if (input <= max && input >= min)
        {
            output = true;
        }
        return output;
    }

    /**
     * Checks to see if the input is within the min/max range.
     * 
     * @param input
     * @param min
     * @param max
     * 
     * @return Whether the input is within the given range. (True/False)
     */
    public static boolean range(double input, double min, double max)
    {
        boolean output = false;
        if (input <= max && input >= min)
        {
            output = true;
        }
        return output;
    }

    /**
     * If the input is outside the given range it is "wrapped" around by adding/subtracting the max value until it is within the given range.
     * 
     * @param input
     * @param min
     * @param max
     * 
     * @return The input wrapped to the given range.
     */
    public static int wrap(int input, int min, int max)
    {
        min--;
        int range = max - min;
        while (range(input, min, max) == false)
        {
            if (input >= max){
                input -= range;
            } else {
                input += range;
            }
        }
        return input;
    }

    public static boolean closeWrapDirectionUp(double currentValue, double goalValue, double min, double max)
    {
        double range = max - min + 1;
        currentValue %= range;
        goalValue %= range;

        double calcNum = Math.abs(goalValue - currentValue);
        boolean output = Math.abs(calcNum) > range / 2;
        return calcNum > 0 ? output : !output;
    }

    /**
     * Normalize all values if the magnitude of any value is greater than 1.0.
     */
    public static void normalize(double[] values) {
      double maxMagnitude = Math.abs(values[0]);
      for (int i = 1; i < values.length; i++) {
        double temp = Math.abs(values[i]);
        if (maxMagnitude < temp) {
          maxMagnitude = temp;
        }
      }
      if (maxMagnitude > 1.0) {
        for (int i = 0; i < values.length; i++) {
          values[i] = values[i] / maxMagnitude;
        }
      }
    }

    public static double normalize(double value, double inputRange, double outputRange)
    {
        double output = value / inputRange;
        output = output * outputRange;
        return output;
    }

    /**
     * Always otputs a positive number, even if the given power is an odd number.
     * 
     * @param input
     * @param power The exponential applied to input.
     * 
     * @return The absolute value of input^power.
     */
    public static double exponetialAbs(double input, double power) {
        return Math.abs(Math.pow(input, power));
    }

    /**
     * If input is less than 0 the output will be negative, even if the given power is an even number.
     * 
     * @param input
     * @param power The exponential applied to input.
     * 
     * @return input^power or -(input^power) if input < 0.
     */
    public static double exponentialNegativeBelowZero(double input, double power)
    {
        double output = Math.pow(input, power);
        if (input < 0)
        {
            output = -output;
        }
        return output;
    }

    /**
     * Checks if the input is 0.
     * 
     * @param input
     * 
     * @return Whether the input is 0. (True/False)
     */
    public static boolean isZero(double input) {
        if (input == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static double arraySum(double[] input)
    {
        double sum = 0;
        for (int i = 0; i < input.length; i++)
        {
            sum += input[i];
        }
        return sum;
    }

    /**
     * Returns true if value1 is closer to zero than value2
     * @param value1
     * @param value2
     * @return
     */
    public static boolean closestToZero(double value1, double value2)
    {
        boolean output = true;
        if ((value1 > 0 && value2 > 0) || (value1 < 0 && value2 < 0))
        {
            if (Math.abs(value1) > Math.abs(value2))
            {
                output = false;
            }
        }

        return output;
    }




}