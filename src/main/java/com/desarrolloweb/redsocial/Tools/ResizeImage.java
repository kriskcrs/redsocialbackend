package com.desarrolloweb.redsocial.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResizeImage {

    public BufferedImage resize(File fileOriginal, int ancho, int alto){
        try{
            //carga la imagen
            System.out.println(fileOriginal);
            BufferedImage imgOriginal = ImageIO.read(fileOriginal);

            BufferedImage imagenResize = new BufferedImage(ancho, alto, imgOriginal.getType());
            Graphics2D g = imagenResize.createGraphics();
            g.drawImage(imgOriginal, 0, 0, ancho, alto, null);
            g.dispose();

            return imagenResize;
            //guardar la imagen
            //File fileResize = new File("img100x100.jpg");
            //ImageIO.write(imagenResize, "jpg", fileResize);

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
