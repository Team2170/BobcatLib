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
}
