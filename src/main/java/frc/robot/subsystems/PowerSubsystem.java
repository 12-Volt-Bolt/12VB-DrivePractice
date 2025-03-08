package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.commands.BunnyBoard;

public class PowerSubsystem extends SubsystemBase {

  private PowerDistribution m_revPDH;
  private double voltage, /*temperatureCelsius,*/ totalCurrent, totalPower/*, totalEnergy*/;
  private double currentsArray[]; 
  private boolean switchedChannelState;
  private double startTime;
  private boolean BunnyBoardran = false;

  /** Subsystem for controlling the power of the robot */
  public PowerSubsystem() {
    m_revPDH = new PowerDistribution(1, ModuleType.kRev);
    currentsArray = new double[20];
    switchedChannelState = true;
    startTime = Timer.getFPGATimestamp();
  }

  public void setPower(double power) {} // Set the power of the robot


  
  @Override
  
    public void periodic() {
        
        voltage = m_revPDH.getVoltage();
        totalCurrent = m_revPDH.getTotalCurrent(); // Get the total current of all channels.
        totalPower = m_revPDH.getTotalPower(); // Get the total power of all channels, the bus voltage multiplied by the current with the units Watts.
        double temperatureCelsius = m_revPDH.getTemperature(); // Retrieves the temperature of the PDP, in degrees Celsius.
                // totalEnergy = m_revPDH.getTotalEnergy(); // Get the total energy of all channels with units Joules.

        double timesofar = Timer.getFPGATimestamp() - startTime;

        if (!BunnyBoardran) {
            BunnyBoard.sendData("time", String.valueOf(timesofar));
            BunnyBoard.sendData("voltage", String.valueOf(voltage));
            BunnyBoard.sendData("temperature", temperatureCelsius + " C°");
        }

        


        SmartDashboard.putNumber("Voltage", voltage);
        SmartDashboard.putNumber("Total Current", totalCurrent);
        SmartDashboard.putNumber("Total Power", totalPower);
        SmartDashboard.putNumber("Runtime (Seconds)", timesofar);
        // SmartDashboard.putNumber("Total Energy", totalEnergy);
        // SmartDashboard.putNumber("PDH Temperature", temperatureCelsius);
        
        // Get the current going through all channels, in Amperes.
        // The PDP returns the current in increments of 0.125A.
        // At low currents the current readings tend to be less accurate.
        for(int i = 0; i < currentsArray.length; i++) {
            currentsArray[i] = m_revPDH.getCurrent(i);
        }
        // SmartDashboard.putNumberArray("Currents", currentsArray);
        // SmartDashboard.putNumber("Num Channels", m_revPDH.getNumChannels());

/*         SmartDashboard.putNumber("Left Front Drive Motor Current", currentsArray[Constants.LEFT_FRONT_DRIVE_MOTOR_PDH_CHANNEL]);
        SmartDashboard.putNumber("Right Front Drive Motor Current", currentsArray[Constants.RIGHT_FRONT_DRIVE_MOTOR_PDH_CHANNEL]);
        SmartDashboard.putNumber("Left Back Drive Motor Current", currentsArray[Constants.LEFT_BACK_DRIVE_MOTOR_PDH_CHANNEL]);
        SmartDashboard.putNumber("Right Back Drive Motor Current", currentsArray[Constants.RIGHT_BACK_DRIVE_MOTOR_PDH_CHANNEL]);
        SmartDashboard.putNumber("Intake Bar Motor Current", currentsArray[Constants.INTAKE_BAR_MOTOR_PDH_CHANNEL]);
        SmartDashboard.putNumber("Intake Deploy Motor Current", currentsArray[Constants.INTAKE_DEPLOY_MOTOR_PDH_CHANNEL]);
        SmartDashboard.putNumber("Elevator Stage 1 Motor Current", currentsArray[Constants.ELEVATOR_STAGE_1_MOTOR_PDH_CHANNEL]);
        SmartDashboard.putNumber("Elevator Stage 2 Motor Current", currentsArray[Constants.ELEVATOR_STAGE_2_MOTOR_PDH_CHANNEL]);
        SmartDashboard.putNumber("Elevator Arm Motor Current", currentsArray[Constants.ELEVATOR_ARM_MOTOR_PDH_CHANNEL]);
        SmartDashboard.putNumber("Elevator Wheel Motor Current", currentsArray[Constants.ELEVATOR_WHEEL_MOTOR_PDH_CHANNEL]); */

        // SmartDashboard.putBoolean("Switched Channel State", switchedChannelState);
        // SmartDashboard.putBoolean("Reported Switched Channel State", getSwitchedChannelState());
    }

    public void setSwitchedChannel(boolean state) {
        switchedChannelState = state;
        m_revPDH.setSwitchableChannel(state);
    }

    public void toggleSwitchedChannel() {
        m_revPDH.setSwitchableChannel(!switchedChannelState);
        switchedChannelState = !switchedChannelState;
    }

    public boolean getSwitchedChannelState() {
        return m_revPDH.getSwitchableChannel();
    }
}


