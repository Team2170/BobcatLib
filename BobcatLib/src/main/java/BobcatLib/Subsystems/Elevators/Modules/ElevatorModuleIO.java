package BobcatLib.Subsystems.Elevators.Modules;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

public interface ElevatorModuleIO {

  /**
   * Data structure for storing inputs related to the climber mechanism.
   *
   * <p>This is used for AdvantageKit logging.
   */
  @AutoLog
  public static class ElevatorIOInputs {
    /** The position of the climber motor represented as a double . */
    public double elevatorPosition = 0;

    public double currentSetPoint = 0;
  }

  public default void moveElevator(Rotation2d setPoint) {}

  public default void runElevator(double velocity) {}

  /** Stops the elevator motor immediately. */
  public default void stop() {}

  public default Rotation2d getPosition() {
    return new Rotation2d();
  }

  public default void updateInputs(ElevatorIOInputs inputs) {}

  public default void setCurrentSetPoint(double rotations) {}
}
