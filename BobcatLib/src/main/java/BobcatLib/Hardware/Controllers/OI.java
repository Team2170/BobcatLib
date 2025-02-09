package BobcatLib.Hardware.Controllers;

import BobcatLib.Hardware.Controllers.parser.ControllerJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import java.io.File;
import java.io.IOException;

/**
 * The Operator Interface (OI) class manages the controller inputs and button mappings for the
 * robot's driver. It supports various controller types and their configurations.
 */
public class OI {
  /** The driver joystick. */
  public Joystick single;

  /** The driver joystick. */
  public Joystick split_two;

  /** Driver Buttons */
  public Trigger fieldCentric;

  /** Driver Zero Gyros */
  public Trigger zeroGyro;

  /* controller configuration */
  public ControllerJson controllerJson;

  /** The wrapper for the driver controller, supporting different controller types. */
  public ControllerWrapper first_controller;

  public ControllerWrapper second_controller;

  private String robotName;
  /**
   * Constructs the Operator Interface (OI) with the specified driver port and configuration.
   * Initializes controller inputs and button mappings.
   */
  public OI(String robotName) {
    this.robotName = robotName;
    loadConfigurationFromFile();
    int driverPort = controllerJson.single.id;
    String type = controllerJson.single.type;
    single = new Joystick(driverPort);
    split_two = single;
    first_controller = init(type, driverPort);
    second_controller = first_controller;
    if (controllerJson.isDual) {
      driverPort = controllerJson.split_one.id;
      type = controllerJson.split_one.type;
      split_two = new Joystick(driverPort);
      second_controller = init(type, driverPort);
      fieldCentric = first_controller.getTopButton();
      zeroGyro = second_controller.getTopButton();
    }
  }

  public ControllerWrapper init(String type, int driverPort) {
    ControllerWrapper tmp;
    switch (type) {
      case "xbox":
        tmp = new XboxControllerWrapper(driverPort);
        fieldCentric = tmp.getAorCross();
        zeroGyro = tmp.getBorCircle();
        break;
      case "ps4":
        tmp = new PS4ControllerWrapper(driverPort);
        fieldCentric = tmp.getAorCross();
        zeroGyro = tmp.getBorCircle();
        break;
      case "ps5":
        tmp = new PS5ControllerWrapper(driverPort);
        fieldCentric = tmp.getAorCross();
        zeroGyro = tmp.getBorCircle();
        break;
      case "ruffy":
        tmp = new Ruffy(driverPort);
        break;
      case "logitech":
        tmp = new Logitech(driverPort);
        break;
      case "eightbitdo":
        tmp = new EightBitDo(driverPort);
        fieldCentric = tmp.getAorCross();
        zeroGyro = tmp.getBorCircle();
        break;
      default:
        tmp = new XboxControllerWrapper(driverPort);
        fieldCentric = tmp.getAorCross();
        zeroGyro = tmp.getBorCircle();
        break;
    }
    return tmp;
  }

  /**
   * Loads Configuration From Deployed File
   *
   * @return ControllerJson
   */
  public ControllerJson loadConfigurationFromFile() {
    String name = "oi.json";
    File deployDirectory = Filesystem.getDeployDirectory();
    assert deployDirectory.exists();
    File directory = new File(deployDirectory, "configs/swerve/" + robotName + "/");
    assert new File(directory, name).exists();
    File moduleFile = new File(directory, name);
    assert moduleFile.exists();
    controllerJson = new ControllerJson();
    try {
      controllerJson = new ObjectMapper().readValue(moduleFile, ControllerJson.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return controllerJson;
  }

  /**
   * Gets Driver LeftY Axis
   *
   * @return double
   */
  public double getLeftYValue() {
    return first_controller.getLeftYAxis();
  }

  /**
   * Gets Driver LeftX Axis
   *
   * @return double
   */
  public double getLeftXValue() {
    return first_controller.getLeftXAxis();
  }

  /**
   * Gets Driver RightX Axis
   *
   * @return double
   */
  public double getRightXValue() {
    if (controllerJson.isDual) {
      return second_controller.getRightXAxis();
    }
    return first_controller.getRightXAxis();
  }
  /**
   * Gets Driver RightY Axis
   *
   * @return double
   */
  public double getRightYValue() {
    if (controllerJson.isDual) {
      return second_controller.getRightYAxis();
    }
    return first_controller.getRightYAxis();
  }
}
