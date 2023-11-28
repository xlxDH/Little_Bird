package game;

import javax.swing.*;
import java.awt.*;
public class Ground {
    Image img;
    int x , y;
    int width , height;

    public Ground() throws Exception{
        img = new  ImageIcon("img/ground.png").getImage();
        width = img.getWidth(null);
        height = img.getHeight(null);
        x = 0;
        y = 500;
    }

    public void step() {
        x -= 4;
        if(x<= -109){
            x = 0;
        }
    }
}
