package game;

import javax.imageio.ImageIO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import java.awt.*;
import javax.imageio.*;
import javax.swing.*;

public class BirdGame extends JPanel {
    Image background;
    Image startImage;
    Image overImage;
    Ground ground;
    Column column1 ,column2;
    Bird bird;
    int score;
    int state;
    public  static  final  int START = 0;
    public  static  final  int RUNNING = 1;
    public  static  final  int OVER = 2;

    public BirdGame() throws Exception{
        background = new ImageIcon("img/background.png").getImage();
        startImage = new ImageIcon("img/start.png").getImage();
        overImage = new ImageIcon("img/gameover.png").getImage();
        ground=new Ground();
        column1=new Column(1);
        column2=new Column(2);
        bird=new Bird();
        score=0;
        state=0;
    }

    public void paint(Graphics g){
        super.paint(g);
        Color c = g.getColor();
        g.setColor(Color.white);

        g.drawImage(background,0,0,null);
        g.drawImage(ground.img,ground.x,ground.y,null);
        g.drawImage(column1.img,column1.x-column1.width/2,column1.y-column1.height/2,null);
        g.drawImage(column2.img,column2.x-column2.width/2,column2.y-column2.height/2,null);
        Graphics2D g2=(Graphics2D) g;
        g2.rotate(-bird.alpha,bird.x,bird.y);
        g.drawImage(bird.img,bird.x-bird.width/2,bird.y-bird.height/2,null);
        g2.rotate(bird.alpha,bird.x,bird.y);

        Font f = new Font(Font.SANS_SERIF,Font.BOLD,40);
        g.setFont(f);
        g.drawString(""+score,40,60);
        g.setColor(Color.white);
        g.drawString(""+score,40-3,60-3);

        switch (state){
            case START:
                g.drawImage(startImage,0,0,null);
                break;
            case OVER:
                g.drawImage(overImage,0,0,null);
                break;
        }
//        repaint();
    }

    public void action() throws Exception{
        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                try{
                    switch (state){
                        case START:
                            state=RUNNING;
                            break;
                        case RUNNING:
                            bird.flappy();
                            break;
                        case OVER:
                            column1=new Column(1);
                            column2=new Column(2);
                            bird=new Bird();
                            score=0;
                            state=START;
                            break;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        addMouseListener(ml);
        while(true){
            switch (state){
                case START:
                    bird.fly();
                    ground.step();
                    break;
                case RUNNING:
                    ground.step();
                    column1.step();
                    column2.step();
                    bird.fly();
                    bird.step();
//                      if(bird.x==column1.x||bird.x==column2.x){
//                        score++;
//                    }
                    score++;
                    if(bird.is_collision(ground)||bird.is_collision(column1)||bird.is_collision(column2)){
                        state=OVER;
                    }
                    break;
            }
            Thread.sleep(1000/60);

            repaint();
        }
    }

    public static void main(String[] args) throws Exception {
        JFrame frame=new JFrame();
        BirdGame game=new BirdGame();
        frame.add(game);
        frame.setSize(440,670);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.action();
    }

}
