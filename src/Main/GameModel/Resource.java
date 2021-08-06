package Main.GameModel;
import Main.tankrotationexample.TRE;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static javax.imageio.ImageIO.read;

public class Resource {

    private static Map<String, BufferedImage> resources;
    private static Map<String, String> sounds;

    static {

        Resource.resources = new HashMap<>();
        Resource.sounds = new HashMap<>();

        try {

            Resource.resources.put("tank1", read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("brownCow" + ".png"))));
            Resource.resources.put("tank2", read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("pinkCow" + ".png"))));
            Resource.resources.put("bullet", read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("bulletShell" + ".png"))));
            Resource.resources.put("break2", read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("normalWall" + ".png"))));
            Resource.resources.put("break1", read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("breakableWall" + ".png"))));
            Resource.resources.put("unbreak", read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("unbreakableWall" + ".png"))));
            Resource.resources.put("floor1", read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("floorOne" + ".png"))));
            Resource.resources.put("floor2", read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("floorTwo" + ".png"))));
            Resource.resources.put("HPBar", read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("HPBar" + ".png"))));
            Resource.resources.put("brownCowLives", read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("brownCowLives" + ".png"))));
            Resource.resources.put("pinkCowLives", read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("pinkCowLives" + ".png"))));
            Resource.resources.put("DamageUp", read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("powerup3" + ".png"))));
            Resource.resources.put("HealthUp", read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("powerup2" + ".png"))));
            Resource.resources.put("SpeedUp", read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("powerup1" + ".png"))));
        } catch (IOException e) {

            e.printStackTrace();
            System.exit(-5);
        }
    }

    public static BufferedImage getResourceImage(String key) {

        return Resource.resources.get(key);
    }

    public static String getSoundTrack(String key) {

        return Resource.sounds.get(key);
    }
}
