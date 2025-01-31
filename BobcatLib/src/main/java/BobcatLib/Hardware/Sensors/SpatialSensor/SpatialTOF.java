package BobcatLib.Hardware.Sensors.SpatialSensor;

import BobcatLib.Hardware.Sensors.SpatialSensor.Components.RangeSensor;
import java.util.HashMap;
import java.util.List;

public class SpatialTOF implements SpatialIO {
  /** Sensors as identified by "field centric" side. */
  public List<RangeSensor> mRangeSensors;

  public SpatialTOF(List<RangeSensor> sensors) {
    this.mRangeSensors = sensors;
    configAllSensors();
  }

  /**
   * Updates the gyro inputs based on external sources.
   *
   * @param inputs The inputs to update.
   */
  public void updateInputs(SpatialIOInputs inputs) {
    HashMap<String, Double> distances = detectObjects();
    inputs.front_left_distance = distances.get("left");
    inputs.front_right_distance = distances.get("right");
    inputs.isAligned = isSquared(inputs.front_left_distance, inputs.front_right_distance, 15);
  }

  /** Configure all Sensors */
  public void configAllSensors() {}

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
