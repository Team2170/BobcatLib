package BobcatLib.Hardware.Motors.Utility;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.revrobotics.spark.config.SparkMaxConfig;

public class MotorConfigurator {
  private TalonFXConfiguration ctreConfig;
  private SparkMaxConfig revConfig;

  public MotorConfigurator() {
    this.ctreConfig = new TalonFXConfiguration();
    this.revConfig = new SparkMaxConfig();
  }

  public MotorConfigurator(TalonFXConfiguration ctreConfig) {
    this.ctreConfig = ctreConfig;
  }

  public MotorConfigurator(SparkMaxConfig revConfig) {
    this.revConfig = revConfig;
  }

  public TalonFXConfiguration getCtreConfig() {
    return ctreConfig;
  }

  public SparkMaxConfig getRevConfig() {
    return revConfig;
  }

  public void update(TalonFXConfiguration ctreConfig) {
    this.ctreConfig = ctreConfig;
  }

  public void update(SparkMaxConfig revConfig) {
    this.revConfig = revConfig;
  }
}
