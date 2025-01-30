package BobcatLib.Hardware.Sensors.SpatialSensor;

import BobcatLib.Hardware.Sensors.SpatialSensor.Components.RangeSensor;
import java.util.List;
import org.littletonrobotics.junction.AutoLog;

public interface SpatialIO {
  /** Represents the inputs for the Spatial sensor. */
  @AutoLog
  public static class SpatialIOInputs {
    public double front_left_distance = 0.0;
    public double front_right_distance = 0.0;
    public boolean isAligned = false;
  }

  /**
   * Updates the gyro inputs based on external sources.
   *
   * @param inputs The inputs to update.
   */
  public default void updateInputs(SpatialIOInputs inputs, boolean isEnabled) {}

  public default List<RangeSensor> getRangeSensors() {
    return null;
  }
  ;

  /**
   * Checks if two lengths are approximately equal within a given tolerance.
   *
   * @param l1 The first length in mm.
   * @param l2 The second length in mm.
   * @param tolerance The acceptable difference between the two lengths in mm
   * @return {@code true} if the absolute difference between l1 and l2 is within the tolerance,
   *     {@code false} otherwise.
   */
  public default boolean isSquared(double l1, double l2, double tolerance) {
    return false;
  }
}
