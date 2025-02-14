package BobcatLib.Subsystems.Elevators.Modules;

import BobcatLib.Subsystems.Elevators.Modules.Motors.BaseElevatorMotor;
import edu.wpi.first.math.geometry.Rotation2d;

public class ElevatorModuleReal implements ElevatorModuleIO {
  private BaseElevatorMotor motor;
  private double currentSetPoint;

  public ElevatorModuleReal() {}

  public void updateInputs(ElevatorIOInputs inputs) {
    inputs.elevatorPosition = getPosition().getRotations();
    inputs.currentSetPoint = currentSetPoint;
  }

  public void moveElevator(Rotation2d setPoint) {
    motor.setAngle(setPoint.getRotations());
  }

  public void runElevator(double velocity) {
    motor.setControl(velocity);
  }

  public Rotation2d getPosition() {
    return Rotation2d.fromRotations(motor.getPosition());
  }

  /** Stops the elevator motor immediately. */
  public void stop() {
    motor.stopMotor();
  }

  public void setCurrentSetPoint(double rotations) {
    currentSetPoint = rotations;
  }
}
