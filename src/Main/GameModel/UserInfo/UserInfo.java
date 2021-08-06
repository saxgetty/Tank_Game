package Main.GameModel.UserInfo;
import Main.GameModel.NonStationary.Tank.Tank;
import java.awt.*;
import java.util.ArrayList;

public abstract class UserInfo {

    public abstract void drawImage(Graphics2D g2, ArrayList<Tank> tanks);
}