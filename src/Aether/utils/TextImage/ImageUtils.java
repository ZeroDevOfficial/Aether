package Aether.utils.TextImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {

    public static BufferedImage toBufferedImage(Image image) {
        BufferedImage buffer = new BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g = buffer.createGraphics();

        g.drawImage(image, null, null);
        return buffer;
    }

    public static char getChar() {
        String character = "block";
        if (character.equalsIgnoreCase("block")) {
            return ImageChar.BLOCK.getChar();
        }
        if (character.equalsIgnoreCase("dark_shade")) {
            return ImageChar.DARK_SHADE.getChar();
        }
        if (character.equalsIgnoreCase("medium_shade")) {
            return ImageChar.MEDIUM_SHADE.getChar();
        }
        if (character.equalsIgnoreCase("light_shade")) {
            return ImageChar.LIGHT_SHADE.getChar();
        }
        return ImageChar.BLOCK.getChar();
    }
}
