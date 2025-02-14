package BobcatLib.Subsystems.Elevators;

import BobcatLib.Subsystems.Elevators.Modules.BaseElevator;
import BobcatLib.Utilities.SetPointWrapper;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
  private BaseElevator elevator;
  public SetPointWrapper setPoints;
  public double lowerLimit = 0;
  public double upperLimit = 0;
  public double currentSetPoint = 0;
  public String name;
  public double maxVelocity = 0.25; // Max Speed to Raise / lower in MPS

  public ElevatorSubsystem(String name, BaseElevator elevator, SetPointWrapper points) {
    this.name = name;
    this.elevator = elevator;
    this.setPoints = points;
    this.lowerLimit = setPoints.getPoints().get(0);
    int size = setPoints.getPoints().size();
    this.upperLimit = setPoints.getPoints().get(size - 1);
  }

  public ElevatorSubsystem(
      String name, BaseElevator elevator, SetPointWrapper points, double lower, double upper) {
    this.name = name;
    this.elevator = elevator;
    this.setPoints = points;
    this.lowerLimit = lower;
    this.upperLimit = upper;
  }

  public ElevatorSubsystem withLimits(double lower, double upper) {
    this.lowerLimit = lower;
    this.upperLimit = upper;
    return this;
  }

  public ElevatorSubsystem withMaxVelocity(double maxVelocity) {
    this.maxVelocity = maxVelocity;
    return this;
  }

  @Override
  public void periodic() {
    elevator.update();
  }

  public void setNextPosition() {
    elevator.moveElevatorToNext(setPoints);
  }

  public void setPrevPosition() {
    elevator.moveElevatorToPrevious(setPoints);
  }

  public void runElevator() {
    elevator.runElevator();
  }

  public void runElevator(double velocity) {
    if (velocity > maxVelocity) {
      velocity = maxVelocity;
    }
    elevator.runElevator(velocity);
  }

  public void holdPosition() {
    elevator.holdPosition();
  }

  public void holdPositionMechanically(double velocity) {
    runElevator(velocity);
  }
}
