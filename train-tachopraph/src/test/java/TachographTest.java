import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TachographTest {
    Tachograph tac;

    @Before
    public void before() {
        tac = new Tachograph();
    }

    @Test
    public void TachoTest() {
        tac.fo(12,12,5);
        Assert.assertEquals(12, tac.getJoystick(12));
        Assert.assertEquals(5, tac.getRef(12));
    }
}
