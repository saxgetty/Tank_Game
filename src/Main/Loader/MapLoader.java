package Main.Loader;
import Main.GameModel.GameObject;
import Main.GameModel.Resource;
import Main.GameModel.Stationary.Floor;
import Main.GameModel.Stationary.PowerUp.DamageUp;
import Main.GameModel.Stationary.PowerUp.HealthUp;
import Main.GameModel.Stationary.PowerUp.SpeedUp;
import Main.GameModel.Stationary.Wall.BreakableWall;
import Main.GameModel.Stationary.Wall.UnbreakableWall;
import Main.tankrotationexample.TRE;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class MapLoader {

    public static void gameInitialize(ArrayList<GameObject> gameObjects, ArrayList<Floor> floors) {

        try {
            /*
             * note class loaders read files from the out folder (build folder in Netbeans) and not the
             * current working directory.
             */
            InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(TRE.class.getClassLoader().getResourceAsStream("maps/map1")));
            BufferedReader mapReader = new BufferedReader(isr);
            String row = mapReader.readLine();

            if (row == null) throw new IOException("There is no data in this file.");

            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for (int curRow = 0; curRow < numRows; curRow++) {

                row = mapReader.readLine();
                mapInfo = row.split("\t");

                for (int curCol = 0; curCol < numCols; curCol++) {

                    switch (mapInfo[curCol]) {

                        case "0":
                            floors.add(new Floor(curCol * 30, curRow * 30, Resource.getResourceImage("floor1")));
                            break;
                        case "1":
                            floors.add(new Floor(curCol * 30, curRow * 30, Resource.getResourceImage("floor2")));
                            break;
                        case "2":
                            gameObjects.add(new DamageUp(curCol * 30, curRow * 30, Resource.getResourceImage("DamageUp")));
                            break;
                        case "3":
                            gameObjects.add(new SpeedUp(curCol * 30, curRow * 30, Resource.getResourceImage("HealthUp")));
                            break;
                        case "4":
                            gameObjects.add(new HealthUp(curCol * 30, curRow * 30, Resource.getResourceImage("SpeedUp")));
                            break;
                        case "5":
                            gameObjects.add(new BreakableWall(curCol * 30, curRow * 30, Resource.getResourceImage("break2")));
                            break;
                        case "6":
                            gameObjects.add(new UnbreakableWall(curCol * 30, curRow * 30, Resource.getResourceImage("unbreak")));
                            break;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}