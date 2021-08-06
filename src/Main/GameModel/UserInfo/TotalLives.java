package Main.GameModel.UserInfo;
import Main.GameConstants;
import Main.GameModel.NonStationary.Tank.Tank;
import Main.GameModel.Resource;
import java.awt.*;
import java.util.ArrayList;

public class TotalLives extends Main.GameModel.UserInfo.UserInfo {

    @Override
    public void drawImage(Graphics2D g2, ArrayList<Tank> tanks) {

        for (int i = 0; i < tanks.get(0).getLife(); i++) {

            g2.drawImage(Resource.getResourceImage("brownCowLives"), 250 + 50 * i, GameConstants.GAME_SCREEN_HEIGHT + 50, null);
        }

        for (int i = 0; i < tanks.get(1).getLife(); i++) {

            g2.drawImage(Resource.getResourceImage("pinkCowLives"), (GameConstants.GAME_WORLD_WIDTH / 2 + 125) + 50 * i, GameConstants.GAME_SCREEN_HEIGHT + 50, null);
        }
    }
}