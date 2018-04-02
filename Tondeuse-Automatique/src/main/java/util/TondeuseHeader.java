package util;

import lombok.Data;

/**
 * Created By SAIF on 22/03/2018
 */
@Data
public class TondeuseHeader {

    Integer posX=0;

    Integer posY=0;

    Character posOrient;

    String tondeusePath;

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public Character getPosOrient() {
        return posOrient;
    }

    public void setPosOrient(Character posOrient) {
        this.posOrient = posOrient;
    }

    public String getTondeusePath() {
        return tondeusePath;
    }

    public void setTondeusePath(String tondeusePath) {
        this.tondeusePath = tondeusePath;
    }
}
