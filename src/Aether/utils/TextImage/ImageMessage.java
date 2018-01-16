package Aether.utils.TextImage;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ImageMessage {
    private static final char TRANSPARENT_CHAR = ' ';
    private final Color[] colors = new Color[]{new Color(0, 0, 0), new Color(0, 0, 170), new Color(0, 170, 0), new Color(0, 170, 170), new Color(170, 0, 0), new Color(170, 0, 170), new Color(255, 170, 0), new Color(170, 170, 170), new Color(85, 85, 85), new Color(85, 85, 255), new Color(85, 255, 85), new Color(85, 255, 255), new Color(255, 85, 85), new Color(255, 85, 255), new Color(255, 255, 85), new Color(255, 255, 255)};
    private String[] lines;

    public ImageMessage(BufferedImage image, int height, char imgChar) {
        TextFormat[][] chatColors = this.toChatColorArray(image, height);
        this.lines = this.toImgMessage(chatColors, imgChar);
    }

    public ImageMessage(TextFormat[][] chatColors, char imgChar) {
        this.lines = this.toImgMessage(chatColors, imgChar);
    }

    public /* varargs */ ImageMessage(String... imgLines) {
        this.lines = imgLines;
    }

    public /* varargs */ ImageMessage appendText(String... text) {
        int y = 0;
        while (y < this.lines.length) {
            if (text.length > y) {
                String[] arrstring = this.lines;
                int n = y;
                arrstring[n] = String.valueOf(arrstring[n]) + " " + text[y];
            }
            ++y;
        }
        return this;
    }

    public /* varargs */ ImageMessage appendCenteredText(String... text) {
        int y = 0;
        while (y < this.lines.length) {
            if (text.length <= y) {
                return this;
            }
            int len = 65 - this.lines[y].length();
            this.lines[y] = String.valueOf(this.lines[y]) + this.center(text[y], len);
            ++y;
        }
        return this;
    }

    private TextFormat[][] toChatColorArray(BufferedImage image, int height) {
        double ratio = (double) image.getHeight() / (double) image.getWidth();
        int width = (int) ((double) height / ratio);
        if (width > 10) {
            width = 10;
        }
        BufferedImage resized = this.resizeImage(image, (int) ((double) height / ratio), height);
        TextFormat[][] chatImg = new TextFormat[resized.getWidth()][resized.getHeight()];
        int x = 0;
        while (x < resized.getWidth()) {
            int y = 0;
            while (y < resized.getHeight()) {
                TextFormat closest;
                int rgb = resized.getRGB(x, y);
                chatImg[x][y] = closest = this.getClosestChatColor(new Color(rgb, true));
                ++y;
            }
            ++x;
        }
        return chatImg;
    }

    private String[] toImgMessage(TextFormat[][] colors, char imgchar) {
        String[] lines = new String[colors[0].length];
        int y = 0;
        while (y < colors[0].length) {
            String line = "";
            int x = 0;
            while (x < colors.length) {
                TextFormat color;
                line = String.valueOf(line) + ((color = colors[x][y]) != null ? new StringBuilder(String.valueOf(colors[x][y].toString())).append(imgchar).toString() : Character.valueOf(' '));
                ++x;
            }
            lines[y] = String.valueOf(line) + (Object) TextFormat.RESET;
            ++y;
        }
        return lines;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        AffineTransform af = new AffineTransform();
        af.scale((double) width / (double) originalImage.getWidth(), (double) height / (double) originalImage.getHeight());
        AffineTransformOp operation = new AffineTransformOp(af, 1);
        return operation.filter(originalImage, null);
    }

    private double getDistance(Color c1, Color c2) {
        double rmean = (double) (c1.getRed() + c2.getRed()) / 2.0;
        double r = c1.getRed() - c2.getRed();
        double g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();
        double weightR = 2.0 + rmean / 256.0;
        double weightG = 4.0;
        double weightB = 2.0 + (255.0 - rmean) / 256.0;
        return weightR * r * r + weightG * g * g + weightB * (double) b * (double) b;
    }

    private boolean areIdentical(Color c1, Color c2) {
        if (Math.abs(c1.getRed() - c2.getRed()) <= 5 && Math.abs(c1.getGreen() - c2.getGreen()) <= 5 && Math.abs(c1.getBlue() - c2.getBlue()) <= 5) {
            return true;
        }
        return false;
    }

    private TextFormat getClosestChatColor(Color color) {
        if (color.getAlpha() < 128) {
            return null;
        }
        int index = 0;
        double best = -1.0;
        int i = 0;
        while (i < this.colors.length) {
            if (this.areIdentical(this.colors[i], color)) {
                return TextFormat.values()[i];
            }
            ++i;
        }
        i = 0;
        while (i < this.colors.length) {
            double distance = this.getDistance(color, this.colors[i]);
            if (distance < best || best == -1.0) {
                best = distance;
                index = i;
            }
            ++i;
        }
        return TextFormat.values()[index];
    }

    private String center(String s, int length) {
        if (s.length() > length) {
            return s.substring(0, length);
        }
        if (s.length() == length) {
            return s;
        }
        int leftPadding = (length - s.length()) / 2;
        StringBuilder leftBuilder = new StringBuilder();
        int i = 0;
        while (i < leftPadding) {
            leftBuilder.append(" ");
            ++i;
        }
        return String.valueOf(leftBuilder.toString()) + s;
    }

    public String[] getLines() {
        return this.lines;
    }

    public void sendMessageToPlayer(Player player) {
        String[] arrstring = this.lines;
        int n = arrstring.length;
        int n2 = 0;
        while (n2 < n) {
            String line = arrstring[n2];
            player.sendMessage(line);
            ++n2;
        }
    }

    public void sendPopupToPlayer(Player player) {
        String[] arrstring = this.lines;
        int n = arrstring.length;
        int n2 = 0;
        while (n2 < n) {
            String line = arrstring[n2];
            player.sendPopup(line);
            ++n2;
        }
    }
}