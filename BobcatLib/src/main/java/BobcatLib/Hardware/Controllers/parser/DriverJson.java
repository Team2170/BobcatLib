package BobcatLib.Hardware.Controllers.parser;
/** Driver Json for parser for controller used by driver station. */
public class DriverJson {
  /** Constructer for Driver Json */
  public DriverJson() {}

  /**
   * Constructor for Driver Station
   *
   * @param id id
   */
  public DriverJson(int id) {
    this.id = id;
    this.deadband = 0.1;
  }

  /** The device type, e.g. xbox,ps4, etc */
  public String type = "";
  /** The CAN ID or pin ID of the device. */
  public int id = 0;
  /** Deadband for controller input */
  public double deadband = 0.1;
}
