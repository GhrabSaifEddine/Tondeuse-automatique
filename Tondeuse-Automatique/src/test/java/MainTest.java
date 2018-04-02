import exceptions.FileInvalidException;
import exceptions.InputNoFoundException;
import org.junit.Assert;
import org.junit.Test;
import util.Pelouse;
import util.TondeuseHeader;

import static junit.framework.TestCase.fail;

/**
 * Created By SAIF on 02/04/2018
 */
public class MainTest {

    @Test
    public final void setPelouseData_failed_pos_y_Test() {
        String filePath = "/instructions_FAILED_1.txt";
        Pelouse pelouse = Main.setPelouseData(filePath);
        Assert.assertEquals(pelouse, null);
    }

    @Test
    public final void setPelouseData_failed_orientation_Test() {
        String filePath = "/instructions_FAILED_2.txt";
        Pelouse pelouse = Main.setPelouseData(filePath);
        Assert.assertEquals(pelouse, null);
    }

    @Test
    public final void setPelouseData_failed_path_Test() {
        String filePath = "/instructions_FAILED_3.txt";
        Pelouse pelouse = Main.setPelouseData(filePath);
        Assert.assertEquals(pelouse, null);
    }

    @Test
    public final void setPelouseData_failed_path_not_found_Test() {
        String filePath = "/instructions_FAILED_4.txt";
        Pelouse pelouse = Main.setPelouseData(filePath);
        Assert.assertEquals(pelouse, null);
    }

    @Test
    public final void setPelouseData_SUCCESS_Test() {
        String filePath = "/instructions.txt";
        Pelouse pelouse = Main.setPelouseData(filePath);
        Assert.assertEquals(pelouse.getAbs().intValue(), 5);
        Assert.assertEquals(pelouse.getOrd().intValue(), 5);

        Assert.assertEquals(pelouse.getListTondeusesHeaders().size(), 2);
        Assert.assertEquals(pelouse.getListTondeusesHeaders().get(0).getPosX().intValue(), 1);
        Assert.assertEquals(pelouse.getListTondeusesHeaders().get(0).getPosY().intValue(), 2);
        Assert.assertEquals(pelouse.getListTondeusesHeaders().get(0).getPosOrient().charValue(), 'N');

        Assert.assertEquals(pelouse.getListTondeusesHeaders().get(1).getPosX().intValue(), 3);
        Assert.assertEquals(pelouse.getListTondeusesHeaders().get(1).getPosY().intValue(), 3);
        Assert.assertEquals(pelouse.getListTondeusesHeaders().get(1).getPosOrient().charValue(), 'E');
    }

    @Test
    public final void navigateTondeuse_change_orientation_Test() throws InputNoFoundException {
        // GIVEN
        Pelouse pelouse = new Pelouse();
        pelouse.setAbs(5);
        pelouse.setOrd(5);
        TondeuseHeader tondeuseHeader = new TondeuseHeader();
        tondeuseHeader.setPosX(1);
        tondeuseHeader.setPosY(2);
        tondeuseHeader.setPosOrient('N');

        // WHEN
        Main.navigateTondeuse(tondeuseHeader,'G',pelouse.getAbs(),pelouse.getOrd());

        // Then
        Assert.assertEquals(tondeuseHeader.getPosX().intValue(),1);
        Assert.assertEquals(tondeuseHeader.getPosY().intValue(),2);
        Assert.assertEquals(tondeuseHeader.getPosOrient().charValue(),'W');
    }

    @Test
    public final void navigateTondeuse_change_orientation_Avance_Test() throws InputNoFoundException {
        // GIVEN
        Pelouse pelouse = new Pelouse();
        pelouse.setAbs(5);
        pelouse.setOrd(5);
        TondeuseHeader tondeuseHeader = new TondeuseHeader();
        tondeuseHeader.setPosX(1);
        tondeuseHeader.setPosY(2);
        tondeuseHeader.setPosOrient('N');

        // WHEN
        Main.navigateTondeuse(tondeuseHeader,'G',pelouse.getAbs(),pelouse.getOrd());
        Main.navigateTondeuse(tondeuseHeader,'A',pelouse.getAbs(),pelouse.getOrd());

        // Then
        Assert.assertEquals(tondeuseHeader.getPosX().intValue(),0);
        Assert.assertEquals(tondeuseHeader.getPosY().intValue(),2);
        Assert.assertEquals(tondeuseHeader.getPosOrient().charValue(),'W');
    }

    @Test
    public final void navigateTondeuse_Avance_Check_Over_Limit_Test() throws InputNoFoundException {
        // GIVEN
        Pelouse pelouse = new Pelouse();
        pelouse.setAbs(5);
        pelouse.setOrd(5);
        TondeuseHeader tondeuseHeader = new TondeuseHeader();
        tondeuseHeader.setPosX(1);
        tondeuseHeader.setPosY(2);
        tondeuseHeader.setPosOrient('N');

        // WHEN
        Main.navigateTondeuse(tondeuseHeader,'G',pelouse.getAbs(),pelouse.getOrd());
        Main.navigateTondeuse(tondeuseHeader,'A',pelouse.getAbs(),pelouse.getOrd());
        Main.navigateTondeuse(tondeuseHeader,'A',pelouse.getAbs(),pelouse.getOrd());

        // Then
        Assert.assertEquals(tondeuseHeader.getPosX().intValue(),0);
        Assert.assertEquals(tondeuseHeader.getPosY().intValue(),2);
        Assert.assertEquals(tondeuseHeader.getPosOrient().charValue(),'W');
    }

    @Test  (expected = InputNoFoundException.class)
    public final void navigateTondeuse_Failed_Orientation_Test() throws InputNoFoundException {
        // GIVEN
        Pelouse pelouse = new Pelouse();
        pelouse.setAbs(5);
        pelouse.setOrd(5);
        TondeuseHeader tondeuseHeader = new TondeuseHeader();
        tondeuseHeader.setPosX(1);
        tondeuseHeader.setPosY(2);
        tondeuseHeader.setPosOrient('N');

        // WHEN
        Main.navigateTondeuse(tondeuseHeader,'S',pelouse.getAbs(),pelouse.getOrd());

        // Then
        Assert.assertFalse(throwInputNoFoundException("S is not a correct orientation!"));
    }

    @Test
    public final void main_Test() throws InputNoFoundException {
        // GIVEN
       Pelouse pelouse = initData();

        // WHEN
        Main.main(new String[]{});

        // Then
        Assert.assertEquals(pelouse.getAbs().intValue(),5);
        Assert.assertEquals(pelouse.getOrd().intValue(),5);

        Assert.assertEquals(Main.pelouse.getListTondeusesHeaders().size(), 2);
        Assert.assertEquals(Main.pelouse.getListTondeusesHeaders().get(0).getPosX().intValue(), 1);
        Assert.assertEquals(Main.pelouse.getListTondeusesHeaders().get(0).getPosY().intValue(), 3);
        Assert.assertEquals(Main.pelouse.getListTondeusesHeaders().get(0).getPosOrient().charValue(), 'N');

        Assert.assertEquals(Main.pelouse.getListTondeusesHeaders().get(1).getPosX().intValue(), 5);
        Assert.assertEquals(Main.pelouse.getListTondeusesHeaders().get(1).getPosY().intValue(), 1);
        Assert.assertEquals(Main.pelouse.getListTondeusesHeaders().get(1).getPosOrient().charValue(), 'E');
    }

    private Pelouse initData() {
        Pelouse pelouse = new Pelouse();
        pelouse.setAbs(5);
        pelouse.setOrd(5);

        TondeuseHeader tondeuseHeader1 = new TondeuseHeader();
        tondeuseHeader1.setPosX(1);
        tondeuseHeader1.setPosY(2);
        tondeuseHeader1.setPosOrient('N');
        tondeuseHeader1.setTondeusePath("GAGAGAGAA");
        pelouse.getListTondeusesHeaders().add(tondeuseHeader1);

        TondeuseHeader tondeuseHeader2 = new TondeuseHeader();
        tondeuseHeader2.setPosX(3);
        tondeuseHeader2.setPosY(3);
        tondeuseHeader2.setPosOrient('E');
        tondeuseHeader2.setTondeusePath("AADAADADDA");
        pelouse.getListTondeusesHeaders().add(tondeuseHeader2);

        return pelouse;
    }

    private boolean throwInputNoFoundException(String msg) throws InputNoFoundException {
        throw new InputNoFoundException(msg);
    }
//    @Test
//    public final void main_Test()
//    {
//        fail("Not yet implemented"); // TODO
//    }
//

}
