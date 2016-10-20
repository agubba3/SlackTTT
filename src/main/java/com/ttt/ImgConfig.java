package com.ttt;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by agubba on 10/17/16.
 */

public class ImgConfig {

    private static BufferedImage board;
    private static BufferedImage X;
    private static BufferedImage O;

    private String error;
    private int XOhw = 120; //height and width of pieces

    public ImgConfig() {

        try {
            board = ImageIO.read(new URL("https://www.filterforge.com/filters/4592.jpg"));
            O = ImageIO.read(new URL("http://i.imgur.com/VDNiMCW.jpg"));
            X = ImageIO.read(new URL("http://i.imgur.com/nYMtsCd.png"));
            O = toBufferedImage(O.getScaledInstance(XOhw, XOhw, Image.SCALE_DEFAULT));
            X = toBufferedImage(X.getScaledInstance(XOhw, XOhw, Image.SCALE_DEFAULT));
        } catch (IOException e) {
            error = e.getMessage();
        }
    }

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public BufferedImage getBoard() {
        return board;
    }

    public BufferedImage getX() {
        return X;
    }

    public BufferedImage getO() {
        return O;
    }

}
