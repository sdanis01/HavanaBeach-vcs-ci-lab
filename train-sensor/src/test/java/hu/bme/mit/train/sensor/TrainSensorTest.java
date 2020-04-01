package hu.bme.mit.train.sensor;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.interfaces.TrainController;

public class TrainSensorTest {

    private TrainSensorImpl ts;
    private TrainUser mtu;
    private TrainController mtc;

    @Before
    public void before() {
        //tc = new TrainControllerImpl();
        //tu = new TrainUserImpl(tc);
        this.mtc = mock(TrainController.class);
        this.mtu = mock(TrainUser.class);
        this.ts = new TrainSensorImpl(mtc, mtu);
    }

    //tesztesetek a határértékeknél

    @Test
    public void belowLowerAbsoluteMargin(){
        ts.overrideSpeedLimit(-1);
        verify(mtu, times(1)).setAlarmState(true);
    }

    @Test
    public void aboveUpperAbsoluteMargin(){
        ts.overrideSpeedLimit(501);
        verify(mtu, times(1)).setAlarmState(true);
    }

    @Test
    public void aboveLowerAbsoluteMargin(){
        ts.overrideSpeedLimit(1);
        verify(mtu, times(1)).setAlarmState(false);
    }

    @Test
    public void belowUpperAbsoluteMargin(){
        ts.overrideSpeedLimit(499);
        verify(mtu, times(1)).setAlarmState(false);
    }

    // 10 nek az 50%-a felett
    @Test
    public void aboveRelativeMargin(){
        ts.overrideSpeedLimit(6);
        verify(mtu, times(1)).setAlarmState(false);
    }

    // 10 nek az 50%-a alatt
    @Test
    public void belowRelativeMargin(){
        ts.overrideSpeedLimit(4);
        verify(mtu, times(1)).setAlarmState(true);
    }
}
