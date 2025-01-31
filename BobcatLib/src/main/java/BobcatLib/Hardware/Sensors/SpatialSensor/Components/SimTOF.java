package BobcatLib.Hardware.Sensors.SpatialSensor.Components;

import BobcatLib.Hardware.Sensors.SpatialSensor.Utility.DistanceMode;
import BobcatLib.Logging.Alert;
import BobcatLib.Logging.Alert.AlertType;
import edu.wpi.first.hal.SimDevice;
import edu.wpi.first.hal.SimDevice.Direction;
import edu.wpi.first.hal.SimDeviceJNI;
import edu.wpi.first.hal.SimDouble;

public class SimTOF implements RangeSensor {
  public int id;
  public DistanceMode mode;
  public double range = 100;
  public Alert sensorAlert;
  public double sampleTime;
  public SimDevice simDevice;

  // Sim Stuff
  public SimDouble simRange;

  public SimTOF(int id, DistanceMode mode, double sampleTime) {
    this.id = id;
    this.sampleTime = sampleTime;
    this.mode = mode;
    simDevice = new SimDevice(SimDeviceJNI.createSimDevice("SimTOF [" + id + "]"));
    simRange = simDevice.createDouble("distance", Direction.kBidir, 0.00);
    try {
      configRangeSensor();
    } catch (Exception e) {
      // TODO: handle exception
      AlertType level = AlertType.INFO;
      sensorAlert = new Alert("TOF", "TOF " + id + " hardware fault occured", level);
      sensorAlert.set(true);
    }
  }

  /**
   * Gets the range in front of the sensor.
   *
   * @return range in mm
   */
  public double getRange() {
    range = simRange.get();
    return range;
  }

  public void configRangeSensor() {}

  public void configRangeSensor(DistanceMode m) {
    this.mode = m;
  }

  public DistanceMode getMode() {
    return mode;
  }

  /**
   * Sets the distance mode of the sensor based on the provided distance.
   *
   * <p>RangingMode.Short: if distance less than 1250 mm RangingMode.Medium: if 1250 mm less than or
   * equal too distance less than 2250 mm RangingMode.Long: if distance greater than or equal to
   * 2250 mm
   */
  public DistanceMode getOptimalMode() {
    DistanceMode distanceMode = new DistanceMode(DistanceMode.modes.SHORT);
    return distanceMode;
  }
}
