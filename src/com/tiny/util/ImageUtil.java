package com.tiny.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class ImageUtil {
    private static final int WIDTH  = 300;
    private static final int HEIGHT = 50;
    private static final int LENGTH = 5;
    private static final int LINES  = 10;
    private static final int FONT_SIZE = 54;

    private static final char[] alphabets = new char[] {
      'a', 'b', 'c', 'd', 'e', 'f',
      'g', 'h', 'i', 'j', 'k', 'l',
      'm', 'n', 'o', 'p', 'q', 'r',
      's', 't', 'u', 'v', 'w', 'x',
      'y', 'z', 'A', 'B', 'C', 'D',
      'E', 'F', 'G', 'H', 'I', 'J',
      'K', 'L', 'M', 'N', 'O', 'P',
      'Q', 'R', 'S', 'T', 'U', 'V',
      'W', 'X', 'Y', 'Z', '0', '1',
      '2', '3', '4', '5', '6', '7',
      '8', '9'
    };


    public static Map<String, BufferedImage> createImage() {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.createGraphics();
//        graphics.setColor(new Color(39, 49, 66));
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        StringBuilder verifyCode = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < LENGTH; i ++) {
            int index = random.nextInt(alphabets.length);
            verifyCode.append(alphabets[index]);
            graphics.setColor(getRandomColor());
            graphics.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
            graphics.drawString(String.valueOf(alphabets[index]), (WIDTH / LENGTH) * i, (FONT_SIZE) / 2 + FONT_SIZE / 3);

        }

        for (int i = 0; i < LINES; i ++) {
            graphics.drawLine(random.nextInt(WIDTH / 2), random.nextInt(HEIGHT),
                    random.nextInt(WIDTH), random.nextInt(HEIGHT));
        }

        Map<String, BufferedImage> container = new HashMap<> ();
        container.put(verifyCode.toString(), bufferedImage);
        return container;

    }

    private static Color getRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(128), random.nextInt(128), random.nextInt(128));
    }

}
