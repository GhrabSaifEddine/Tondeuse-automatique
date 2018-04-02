import exceptions.FileInvalidException;
import exceptions.InputNoFoundException;
import org.junit.Assert;
import org.junit.Test;
import util.Convertor;

/**
 * Created By SAIF on 02/04/2018
 */
public class ConvertorTest {

    @Test
    public final void getIntegerValue_Valid_Test(){
        String value="1";
        Integer resultValue = Convertor.getIntegerValue(value);
        Assert.assertEquals(resultValue.intValue(),1);
    }
    @Test  (expected = NumberFormatException.class)
    public final void getIntegerValue_Invalid_Test(){
        String value="A";
        Convertor.getIntegerValue(value);
        Assert.assertFalse(throwException());
    }

    @Test
    public final void getOrientationValue_Valid_Test() throws InputNoFoundException {
        String value="N";
        Character resultValue = Convertor.getOrientationValue(value);
        Assert.assertEquals(resultValue.charValue(),'N');
    }

    @Test  (expected = InputNoFoundException.class)
    public final void getOrientationValue_Invalid_Test() throws InputNoFoundException{
        String value="B";
        Convertor.getOrientationValue(value);
        Assert.assertFalse(throwInputNoFoundException(value + " is not a correct orientation!"));
    }

    @Test
    public final void getPathValue_Valid_Test() throws InputNoFoundException{
        String value="AGAGADA";
        String resultValue = Convertor.getPathValue(value);
        Assert.assertEquals(resultValue,value);
    }

    @Test  (expected = InputNoFoundException.class)
    public final void getPathValue_Invalid_Test() throws InputNoFoundException{
        String value="SABAZDA";
        Convertor.getPathValue(value);
        Assert.assertFalse(throwInputNoFoundException(value + " is not a correct path!"));
    }

    @Test
    public final void getCheckPath_Valid_Test() throws  FileInvalidException {
        String value="AGAGADA";
        String resultValue = Convertor.checkPath(value);
        Assert.assertEquals(resultValue,value);
    }

    @Test  (expected = FileInvalidException.class)
    public final void getCheckPath_Invalid_Test() throws FileInvalidException{
        String value="";
        Convertor.checkPath(value);
        Assert.assertFalse(throwFileInvalidException("The path was not given!"));
    }

    private boolean throwException(){
        throw new NumberFormatException();
    }

    private boolean throwInputNoFoundException(String msg) throws InputNoFoundException {
        throw new InputNoFoundException(msg);
    }

    private boolean throwFileInvalidException(String msg) throws FileInvalidException {
        throw new FileInvalidException(msg);
    }
}
