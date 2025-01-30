package BobcatLib.Hardware.Sensors.SpatialSensor;

import BobcatLib.Hardware.Sensors.SpatialSensor.Components.RangeSensor;
import java.util.List;
import org.littletonrobotics.junction.Logger;

public class Spatial {
  private final SpatialIO io;
  private final SpatialIOInputsAutoLogged inputs = new SpatialIOInputsAutoLogged();
  public boolean isEnabled = false;

  /** continuously gets the distances */
  public Spatial(SpatialIO io) {
    this.io = io;
  }

  public void periodic() {
    io.updateInputs(inputs, isEnabled);
    Logger.processInputs("Spatial", inputs);
  }

  public List<RangeSensor> getSpatialSensors() {
    return io.getRangeSensors();
  }
}
