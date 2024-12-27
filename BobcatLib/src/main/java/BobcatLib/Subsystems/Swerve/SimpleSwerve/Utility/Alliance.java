package BobcatLib.Subsystems.Swerve.SimpleSwerve.Utility;

import edu.wpi.first.wpilibj.DriverStation;

public class Alliance {
  private DriverStation.Alliance alliance;

  public Alliance() {
    this.alliance = DriverStation.getAlliance().get();
  }

  public Alliance(DriverStation.Alliance ally) {
    alliance = ally;
  }

  public boolean isBlueAlliance() {
    return alliance == DriverStation.Alliance.Blue;
  }

  public boolean isRedAlliance() {
    return alliance == DriverStation.Alliance.Red;
  }

  public DriverStation.Alliance get() {
    return alliance;
  }
}
