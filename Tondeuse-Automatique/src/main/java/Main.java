import exceptions.FileInvalidException;
import exceptions.InputNoFoundException;
import util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * Created By SAIF on 22/03/2018
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String FILE_PATH = "/instructions.txt";
    static Pelouse pelouse =  new Pelouse();

    public static void main(String[] args) {
        pelouse = setPelouseData(FILE_PATH);
        if (pelouse != null) {
            pelouse.getListTondeusesHeaders().forEach(tondeuseHeader -> {
                try {
                    if (Convertor.checkPath(tondeuseHeader.getTondeusePath()) != null) {
                        tondeuseHeader.getTondeusePath().chars().forEach(path -> {
                            try {
                                navigateTondeuse(tondeuseHeader, (char) path, pelouse.getAbs(), pelouse.getOrd());
                            } catch (InputNoFoundException e) {
                                LOGGER.warning(e.toString());
                            }
                        });
                        System.out.println(tondeuseHeader.getPosX() + " " + tondeuseHeader.getPosY() + " " + tondeuseHeader.getPosOrient());
                    } else {

                    }
                } catch (FileInvalidException e) {
                    LOGGER.warning(e.getMessage());
                }
            });
        }
    }

    public static void navigateTondeuse(TondeuseHeader tondeuseHeader, char orientation, int maxAbs, int maxOrd) throws InputNoFoundException {
        switch (orientation) {
            case 'D':
            case 'G':
                tondeuseHeader.setPosOrient((char) (new OrientationRule().getOrientationRules()
                        .get((new StringBuilder().append(tondeuseHeader.getPosOrient()).append(" ").append(orientation)).toString())));
                break;
            case 'A':
                // abs
                if (tondeuseHeader.getPosOrient() == 'N' && tondeuseHeader.getPosY() < maxOrd) {
                    tondeuseHeader.setPosY(tondeuseHeader.getPosY() + 1);
                }
                if (tondeuseHeader.getPosOrient() == 'S' && tondeuseHeader.getPosY() > 0) {
                    tondeuseHeader.setPosY(tondeuseHeader.getPosY() - 1);
                }
                // ord
                if (tondeuseHeader.getPosOrient() == 'E' && tondeuseHeader.getPosX() < maxAbs) {
                    tondeuseHeader.setPosX(tondeuseHeader.getPosX() + 1);
                }
                if (tondeuseHeader.getPosOrient() == 'W' && tondeuseHeader.getPosX() > 0) {
                    tondeuseHeader.setPosX(tondeuseHeader.getPosX() - 1);
                }
                break;
            default:
                // tondeuseHeader = null;
                Convertor.getOrientationValue(String.valueOf(orientation));
        }
    }

    public static Pelouse setPelouseData(String filePath) {
        Pelouse pelouse = new Pelouse();
        // Get the instruction's file
        InputStream inputStreamForFile = Main.class.getResourceAsStream(filePath);
        InputStreamReader streamReaderForFile = new InputStreamReader(inputStreamForFile, StandardCharsets.UTF_8);
        try (BufferedReader fileFeader = new BufferedReader(streamReaderForFile)) {
            // Set pelouse with data from file
            String line;
            String nextStep = TondeuseConstants.PELOUSE_HEADER;
            TondeuseHeader tondeuseHeader = new TondeuseHeader();
            while ((line = fileFeader.readLine()) != null) {
                if (nextStep == TondeuseConstants.PELOUSE_HEADER && line != null) {
                    String[] splitedPelouseHeader = line.split("\\s+");
                    pelouse.setAbs(Convertor.getIntegerValue(splitedPelouseHeader[0]));
                    pelouse.setOrd(Convertor.getIntegerValue(splitedPelouseHeader[1]));
                    if (pelouse.getAbs() == null || pelouse.getOrd() == null) {
                        LOGGER.warning(" Invalid dimensions !");
                        return null;
                    }
                    nextStep = TondeuseConstants.TONDEUSE_HEADER;
                    line = null;
                }
                if (nextStep == TondeuseConstants.TONDEUSE_HEADER && line != null) {
                    String[] splitedTondeuseHeader = line.split("\\s+");
                    tondeuseHeader = new TondeuseHeader();
                    tondeuseHeader.setPosX(Convertor.getIntegerValue(splitedTondeuseHeader[0]));
                    tondeuseHeader.setPosY(Convertor.getIntegerValue(splitedTondeuseHeader[1]));
                    try {
                        tondeuseHeader.setPosOrient(Convertor.getOrientationValue(splitedTondeuseHeader[2]));
                    } catch (InputNoFoundException e) {
                        LOGGER.warning(e.toString());
                    }
                    if (tondeuseHeader.getPosX() == null || tondeuseHeader.getPosY() == null || tondeuseHeader.getPosOrient() == null) {
                        LOGGER.warning(" Invalid position of Tondeuse !");
                        return null;
                    }
                    pelouse.getListTondeusesHeaders().add(tondeuseHeader);
                    nextStep = TondeuseConstants.TONDEUSE_BODY;
                    line = null;
                }
                if (nextStep == TondeuseConstants.TONDEUSE_BODY && line != null) {
                    try {
                        tondeuseHeader.setTondeusePath(Convertor.getPathValue(line));
                    } catch (InputNoFoundException e) {
                        LOGGER.warning(e.toString());
                    }
                    nextStep = TondeuseConstants.TONDEUSE_HEADER;
                    line = null;
                }

            }
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
            return null;
        }
        // Check all vars of tondeuse
        if(pelouse.getListTondeusesHeaders() !=null){
            for(TondeuseHeader tondeuseHeader:pelouse.getListTondeusesHeaders()){
                if(tondeuseHeader.getPosX() == null || tondeuseHeader.getPosY()==null
                        || tondeuseHeader.getPosOrient()==null || tondeuseHeader.getTondeusePath()==null)
                    return null;
            }
        }
        return pelouse;
    }
}
