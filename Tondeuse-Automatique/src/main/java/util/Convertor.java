package util;

import exceptions.FileInvalidException;
import exceptions.InputNoFoundException;

import java.util.logging.Logger;

/**
 * Created By SAIF on 31/03/2018
 */
public class Convertor {

    private static char[] orientationsCharacters = new char[]{'N', 'S', 'E', 'W'};
    private static char[] pathCharacters = new char[]{'A', 'D', 'G'};
    private static final Logger LOGGER = Logger.getLogger(Convertor.class.getName());

    public static Integer getIntegerValue(String value) {
        Integer resultValue = null;
        try {
            resultValue = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            LOGGER.warning(e.getMessage());
        }
        return resultValue;
    }

    public static Character getOrientationValue(String value) throws InputNoFoundException {
        Character resultValue = null;
        if (value == null || value.length() == 0 || !(new String(orientationsCharacters).contains(value.toString())))
            throw new InputNoFoundException(value + " is not a correct orientation!");
        else
            resultValue = value.charAt(0);
        return resultValue;
    }

    public static String getPathValue(String value) throws InputNoFoundException {
        String valueToCheck = value;
        for (int i = 0; i < value.length(); i++) {
            if (!new String(pathCharacters).contains(String.valueOf(value.charAt(i)))) {
                throw new InputNoFoundException(value + " is not a correct path!");
            }
        }
        return valueToCheck;
    }

    public static String checkPath(String value) throws FileInvalidException {
        String resultValue = null;
        if (value == null || value.length()==0)
            throw new FileInvalidException("The path was not given!");
        else
            resultValue = value;
        return resultValue;
    }
}
