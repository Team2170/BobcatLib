package BobcatLib.Hardware.Sensors.SpatialSensor;

import BobcatLib.Hardware.Sensors.SpatialSensor.Components.RangeSensor;
import BobcatLib.Hardware.Sensors.SpatialSensor.Utility.DistanceMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpatialTOF implements SpatialIO {
  /** Sensors as identified by "field centric" side. */
  public List<RangeSensor> mRangeSensors;

  public SpatialTOF(List<RangeSensor> sensors) {
    mRangeSensors = sensors;
    configAllSensors();
  }

  /**
   * Updates the gyro inputs based on external sources.
   *
   * @param inputs The inputs to update.
   */
  public void updateInputs(SpatialIOInputs inputs) {
    HashMap<String, Double> distances = detectObjects();
    inputs.front_left_distance = distances.get(0);
    inputs.front_right_distance = distances.get(1);
    inputs.isAligned = isSquared(inputs.front_left_distance, inputs.front_right_distance, 5);
  }

  /** Configure all Sensors */
  public void configAllSensors() {
    mRangeSensors.add(null);
    mRangeSensors.add(null);
  }

  /**
   * Find the object distances on all sides.
   *
   * @return range in mm
   */
  public HashMap<String, Double> detectObjects() {
    HashMap<String, Double> mDistances = new HashMap<String, Double>();

    mDistances.put("left", mRangeSensors.get(0).getRange());
    mDistances.put("right", mRangeSensors.get(1).getRange());

    return mDistances;
  }

  /**
   * Find the object distance. If the sensor is in long mode and calculated distance is less that
   * 1250mm switch to short mode otherwise keep in long mode. If in short keep in short if less than
   * 1250mm otherwise switch back to long range mode
   *
   * @return range in mm
   */
  public Double detectObject(List<RangeSensor> sensors) {
    if (sensors.size() < 1) {
      return 0.00;
    }
    ArrayList<Double> distances = new ArrayList<Double>(2);
    for (RangeSensor sensor : sensors) {
      double range = sensor.getRange();
      setCustomRangingMode(sensor);
      distances.add(range);
    }
    double avg = calculateAverage(distances);
    return avg;
  }

  public void setCustomRangingMode(RangeSensor sensor) {
    DistanceMode mode = sensor.getOptimalMode();
    sensor.configRangeSensor(mode);
  }

  /**
   * calculates the average distances of an arraylist of double
   *
   * @param sensorDistances
   * @return average distance in mm
   */
  private double calculateAverage(List<Double> sensorDistances) {
    return sensorDistances.stream().mapToDouble(d -> d).average().orElse(0.0);
  }

  public List<RangeSensor> getRangeSensors() {
    return mRangeSensors;
  }

  /**
   * Checks if two lengths are approximately equal within a given tolerance.
   *
   * @param l1 The first length in mm.
   * @param l2 The second length in mm.
   * @param tolerance The acceptable difference between the two lengths in mm
   * @return {@code true} if the absolute difference between l1 and l2 is within the tolerance,
   *     {@code false} otherwise.
   */
  public boolean isSquared(double l1, double l2, double tolerance) {
    return Math.abs(l1 - l2) <= tolerance;
  }
}
