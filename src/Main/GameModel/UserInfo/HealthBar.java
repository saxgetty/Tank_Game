package Main.GameModel.UserInfo;
import Main.GameConstants;
import Main.GameModel.NonStationary.Tank.Tank;
import Main.GameModel.Resource;
import java.awt.*;
import java.util.ArrayList;

public class HealthBar extends Main.GameModel.UserInfo.UserInfo {

    @Override
    public void drawImage(Graphics2D g2, ArrayList<Tank> tanks) {

        for (int i = 0; i < tanks.get(0).getHealth(); i++) {

            g2.drawImage(Resource.getResourceImage("HPBar"), 20 * i + GameConstants.GAME_SCREEN_WIDTH / 7, GameConstants.GAME_SCREEN_HEIGHT + 15, null);
        }

        for (int i = 0; i < tanks.get(1).getHealth(); i++) {

            g2.drawImage(Resource.getResourceImage("HPBar"), (GameConstants.GAME_SCREEN_WIDTH / 2 - 135) + 20 * i + GameConstants.GAME_SCREEN_WIDTH / 4, GameConstants.GAME_SCREEN_HEIGHT + 15, null);
        }
    }
}