package util;

import java.util.HashMap;

/**
 * Created By SAIF on 27/03/2018
 */
    /*
    Here we specify the orientation of every move which is the key of the map. Every key (move) is composed of 2 fieds : the first is
    the initial orientation and the second is the next direction. The result of the value, which is the value of the map is the next
    orientation. ==> Dictionnary of rules. For example: if the initial orientation is "Nord" and the direction is "D", the result is East.
    */

public class OrientationRule {

    // List of OrientationRule
    HashMap orientationRules = new HashMap();

    public OrientationRule() {
        HashMap initMap = new HashMap();
        initMap.put("N D", 'E');
        initMap.put("S D", 'W');
        initMap.put("E D", 'S');
        initMap.put("W D", 'N');
        initMap.put("N G", 'W');
        initMap.put("S G", 'E');
        initMap.put("E G", 'N');
        initMap.put("W G", 'S');
        orientationRules = initMap;
    }

    public HashMap getOrientationRules() {
        return orientationRules;
    }
}
