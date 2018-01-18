package sample;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kostarubtsov1990 on 18/01/18.
 */
public class ColorMap {
    Map<String, Color> colorNameToColor;

    public ColorMap() {
        colorNameToColor = new HashMap<>();
        colorNameToColor.put("Black", Color.BLACK);
        colorNameToColor.put("White", Color.WHITE);
        colorNameToColor.put("Blue", Color.BLUE);
        colorNameToColor.put("Green", Color.GREEN);
    }

    public Color GetColor (String colorName) {
        return colorNameToColor.get(colorName);
    }

}
