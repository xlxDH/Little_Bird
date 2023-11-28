package game;

import java.util.*;
import java.awt.*;

import javax.swing.*;
import javax.imageio.ImageIO;

public class Column {
    Image img;
    int x , y;
    int width,height;
    int gap;
    int distance;
    Random random = new Random();

    public Column(int n) throws Exception{
        img = new ImageIcon("img/column.png").getImage();
        width=img.getWidth(null);
        height=img.getHeight(null);
        gap = 144;
        distance=245;
        x=550+(n-1)*distance;
        y= random.nextInt(218)+132;
    }

    public void step(){
        x-=4;
        if(x<=-width/2){
            x = distance*2-width/2;
            y= random.nextInt(218);
        }
    }
}
