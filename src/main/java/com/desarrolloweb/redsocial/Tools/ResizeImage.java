package com.desarrolloweb.redsocial.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResizeImage {

    public boolean resize(){
        try{
            //carga la imagen
            File fileOriginal = new File("ppp.jpg");
            System.out.println(fileOriginal);
            BufferedImage imgOriginal = ImageIO.read(fileOriginal);

            //tamaño
            int ancho = 500;
            int alto = 500;

            BufferedImage imagenResize = new BufferedImage(ancho, alto, imgOriginal.getType());
            Graphics2D g = imagenResize.createGraphics();
            g.drawImage(imgOriginal, 0, 0, ancho, alto, null);
            g.dispose();

            //guardar la imagen
            File fileResize = new File("img100x100.jpg");
            ImageIO.write(imagenResize, "jpg", fileResize);
            System.out.println("finalizo");
        }catch (IOException e){
            e.printStackTrace();
        }

        return  false;
    }
}
