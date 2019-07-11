


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author WingsOfWind
 */
public class ImageLoad {

    public static Image[] loadImage(String  path, int width) throws IOException{
        BufferedImage load = ImageIO.read(ImageLoad.class.getResource(path));
        Image[] images= new Image[load.getWidth()/ width];
        for(int i=0; i< images.length;i++){
            images[i]= load.getSubimage(i* width, 0, width, width);
        }
        return images;
    }
}
