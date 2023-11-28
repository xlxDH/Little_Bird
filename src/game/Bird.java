package game;
import java.awt.*;
import java.util.Map;
import javax.swing.*;
import javax.imageio.ImageIO;

public class Bird {
    Image img;
    int x , y;
    int width,height;
    int size;
    double g;
    double vy;
    double t;
    double speed;
    double s;
    double alpha;

    Image[] images;
    int idx;

    public  Bird() throws Exception{
        img = new ImageIcon("img/0.png").getImage();
        width = img.getWidth(null);
        height = img.getHeight(null);
        x = 132;
        y = 280;
        size = 40;
        g = 4;
        vy = 20;
        t = 0.25;
        speed = vy;
        s = 0;
        alpha = 0;
        images = new Image[8];

        for(int i=0;i<8;i++){
            images[i] = new ImageIcon("img/"+i+".png").getImage();
        }
        idx = 0;
    }
    public void fly(){
        idx ++;
        img = images[(idx/12)%8];
    }

    public void step(){
        double vy = speed;
        s = vy*t+g*t*t/2;
        y = y-(int)s;
        double v = vy-g*t;
        speed = v;
        alpha = Math.atan(s/8);
    }

    public void  flappy(){
        speed=vy;
    }

    public boolean is_collision(Ground ground){
        boolean fla = y+size/2>ground.y;
        if(fla){
            y = ground.y-size/2;
            alpha=Math.PI/2;
        }
        return fla;
    }

    public boolean is_collision(Column column){
       if(x>column.x-column.width/2-size/2&&x<column.x+column.width/2+size/2){
           if(y>column.y-column.gap/2+size/2&&y<column.y+column.gap/2-size/2) return false;
           return true;
       }
       return false;
    }
}
