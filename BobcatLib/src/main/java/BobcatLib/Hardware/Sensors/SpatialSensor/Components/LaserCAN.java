package BobcatLib.Hardware.Sensors.SpatialSensor.Components;

import BobcatLib.Hardware.Sensors.SpatialSensor.Utility.DistanceMode;
import BobcatLib.Logging.Alert;
import BobcatLib.Logging.Alert.AlertType;
import au.grapplerobotics.ConfigurationFailedException;
import au.grapplerobotics.LaserCan;
import au.grapplerobotics.interfaces.LaserCanInterface.RangingMode;

public class LaserCAN implements RangeSensor {
  public int id;
  public LaserCan tof;
  public DistanceMode mode;
  public double range;
  public Alert sensorAlert;
  public double sampleTime;

  public LaserCAN(int id, DistanceMode mode, double sampleTime) {
    this.id = id;
    this.sampleTime = sampleTime;
    this.mode = mode;
    try {
      tof = new LaserCan(id);
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
    range = 0;
    LaserCan.Measurement measurement = tof.getMeasurement();
    if (measurement != null && measurement.status == LaserCan.LASERCAN_STATUS_VALID_MEASUREMENT) {
      range = measurement.distance_mm;
    }
    return range;
  }

  public void configRangeSensor() {
    // Optionally initialise the settings of the LaserCAN, if you haven't already
    // done so in GrappleHook
    try {
      tof.setRangingMode(mode.asLaserCAN());
      tof.setRegionOfInterest(new LaserCan.RegionOfInterest(0, 0, 16, 16));
      tof.setTimingBudget(LaserCan.TimingBudget.TIMING_BUDGET_20MS);
    } catch (ConfigurationFailedException e) {
      e.printStackTrace();
    }
  }

  public void configRangeSensor(DistanceMode m) {
    this.mode = m;
    // Optionally initialise the settings of the LaserCAN, if you haven't already
    // done so in GrappleHook
    try {
      tof.setRangingMode(m.asLaserCAN());
      tof.setRegionOfInterest(new LaserCan.RegionOfInterest(0, 0, 16, 16));
      tof.setTimingBudget(LaserCan.TimingBudget.TIMING_BUDGET_20MS);
    } catch (ConfigurationFailedException e) {
      e.printStackTrace();
    }
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
    double distance = getRange();
    RangingMode mode =
        (distance < 1000)
            ? RangingMode.SHORT
            : (distance < 1000) ? RangingMode.SHORT : RangingMode.LONG;
    DistanceMode distanceMode = new DistanceMode();
    return distanceMode.fromLaserCAN(mode);
  }

  public void updateFromDirectional(double translation) {}
}
