package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController mockC;
    TrainUser mockU;
    TrainSensor sensor;

    @Before
    public void before() {
        mockC = mock(TrainController.class);
        mockU = mock(TrainUser.class);
        sensor = new TrainSensorImpl(mockC, mockU);
    }

    @Test
    public void CheckLowerAbsoluteBound() {
        sensor.overrideSpeedLimit(-1);
        verify(mockU,times(1)).setAlarmState(true);
    }

    @Test
    public void CheckUpperAbsoluteBound() {
        sensor.overrideSpeedLimit(501);
        verify(mockU,times(1)).setAlarmState(true);
    }

    @Test
    public void CheckLowerThanRelativeBound() {
        sensor.overrideSpeedLimit(150);
        sensor.overrideSpeedLimit(50);
        verify(mockU,times(1)).setAlarmState(true);
    }

    @Test
    public void CheckCorrectValue() {
        sensor.overrideSpeedLimit(150);
        sensor.overrideSpeedLimit(75);
        verify(mockU,times(0)).setAlarmState(true);
    }
}
