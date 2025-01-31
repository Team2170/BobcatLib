package frc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import BobcatLib.Hardware.Sensors.SpatialSensor.Spatial;
import BobcatLib.Hardware.Sensors.SpatialSensor.SpatialTOF;
import BobcatLib.Hardware.Sensors.SpatialSensor.Components.RangeSensor;
import BobcatLib.Hardware.Sensors.SpatialSensor.Components.SimTOF;
import BobcatLib.Hardware.Sensors.SpatialSensor.Utility.DistanceMode;
import BobcatLib.Hardware.Sensors.SpatialSensor.Utility.DistanceMode.modes;

public class ValidateHardwareAlignment {
    /**
     * @hidden
     */
    @Test
    void validateIsAligned() {
        boolean isAligned = false;
        SimTOF stof1 = new SimTOF(1, new DistanceMode(modes.SHORT), 20);
        SimTOF stof2 = new SimTOF(2, new DistanceMode(modes.SHORT), 20);
        List<RangeSensor> distanceSensors = new ArrayList<RangeSensor>();
        distanceSensors.add(stof1);
        distanceSensors.add(stof2);
        SpatialTOF stof = new SpatialTOF(distanceSensors);
        Spatial distanceSensing = new Spatial(stof);
        distanceSensing.periodic();
        assertNotNull(distanceSensing.isSquared() == false);
    }

    /**
     * @hidden
     */
    @Test
    void validateIsRotateCCW() {
        boolean isAligned = false;
        SimTOF stof1 = new SimTOF(1, new DistanceMode(modes.SHORT), 20);
        SimTOF stof2 = new SimTOF(2, new DistanceMode(modes.SHORT), 20);
        List<RangeSensor> distanceSensors = new ArrayList<RangeSensor>();
        distanceSensors.add(stof1);
        distanceSensors.add(stof2);
    }

    /**
     * @hidden
     */
    @Test
    void validateIsRotateCW() {
        boolean isAligned = false;
        SimTOF stof1 = new SimTOF(1, new DistanceMode(modes.SHORT), 20);
        SimTOF stof2 = new SimTOF(2, new DistanceMode(modes.SHORT), 20);
        List<RangeSensor> distanceSensors = new ArrayList<RangeSensor>();
        distanceSensors.add(stof1);
        distanceSensors.add(stof2);
    }
}