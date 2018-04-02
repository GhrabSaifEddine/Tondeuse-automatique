package util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By SAIF on 22/03/2018
 */
@Data
public class Pelouse {

    // Abscisse
    Integer abs =0;
    // Ordonné
    Integer ord=0;
    // Liste des Tondeuses
    List<TondeuseHeader> listTondeusesHeaders =new ArrayList<>();

    public Pelouse() {
    }
}
