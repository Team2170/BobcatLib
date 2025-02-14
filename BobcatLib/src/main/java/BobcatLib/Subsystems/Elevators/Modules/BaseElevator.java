package BobcatLib.Subsystems.Elevators.Modules;

import BobcatLib.Utilities.SetPointWrapper;
import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.Logger;

public class BaseElevator {
  private final ElevatorModuleIO io;
  private final ElevatorIOInputsAutoLogged inputs = new ElevatorIOInputsAutoLogged();
  private Rotation2d currentSetPoint = new Rotation2d();

  public BaseElevator(ElevatorModuleIO io) {
    this.io = io;
  }

  public void update() {
    io.setCurrentSetPoint(currentSetPoint.getRotations());
    io.updateInputs(inputs);
    Logger.processInputs("Elevator/Module", inputs);
  }

  public void moveElevatorToNext(SetPointWrapper setPoints) {
    Rotation2d currentPosition = Rotation2d.fromRotations(io.getPosition().getRotations());
    Rotation2d nextPosition =
        Rotation2d.fromRotations(setPoints.getSurroundingPoints(currentPosition).get(1));
    currentSetPoint = nextPosition;
  }

  public void moveElevatorToPrevious(SetPointWrapper setPoints) {
    Rotation2d currentPosition = Rotation2d.fromRotations(io.getPosition().getRotations());
    Rotation2d previousPosition =
        Rotation2d.fromRotations(setPoints.getSurroundingPoints(currentPosition).get(0));
    currentSetPoint = previousPosition;
  }

  public void holdPosition() {
    Rotation2d currentPosition = Rotation2d.fromRotations(io.getPosition().getRotations());
    currentSetPoint = currentPosition;
  }

  public Rotation2d getCurrentSetPoint() {
    return currentSetPoint;
  }

  public void runElevator(double velocity) {
    io.runElevator(velocity);
  }

  public void runElevator() {
    io.moveElevator(currentSetPoint);
  }
  /** Stops the elevators motor immediately. */
  public void stop() {
    io.stop();
  }
}
