package screen;

import element.Gourd;
import element.Monster;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class GamePanel extends JPanel {
    private WordScreen screen;
    private Gourd gourdRole;
    private ArrayList<Monster> monsters;
    private int level = 0;
    private Timer timer;

    private BufferedImage background;
    private BufferedImage gourd;
    private BufferedImage snake;
    private BufferedImage scorpion;

    public GamePanel(WordScreen screen){
        this.screen = screen;
        this.gourdRole = screen.getGourd();
        this.monsters = screen.getMonsters();
        try {
            background = ImageIO.read(new File("img/start.png"));
            gourd = ImageIO.read(new File("img/gourd_only.png"));
            snake = ImageIO.read(new File("img/snake.png"));
            scorpion = ImageIO.read(new File("img/scorpion.png"));
            //下面这个会报错，有时间再看是什么原因
            //background = ImageIO.read(Objects.requireNonNull(GamePanel.class.getResource("background.png")));
            //gourd = ImageIO.read(Objects.requireNonNull(GamePanel.class.getResource("gourd.png")));
            //snake = ImageIO.read(Objects.requireNonNull(GamePanel.class.getResource("snake.png")));
            //scorpion = ImageIO.read(Objects.requireNonNull(GamePanel.class.getResource("snake.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(background, 0, 0, 1200, 800, null);
        paintGourd(g);
        if (monsters != null){
            paintMonster(g);
        }
        paintScore(g);

    }

    public void paintMonster(Graphics g){
        for(Monster monster : monsters){
            if (monster != null){
                if (monster.getKind().equals("snake")){
                    paintSnake(g, monster);
                }
                else if (monster.getKind().equals("scorpion")){
                    paintScorpion(g, monster);
                }
            }



        }
    }



    public void paintGourd(Graphics g){
        g.drawImage(gourd, gourdRole.getX(), gourdRole.getY(), gourdRole.getWidth(), gourdRole.getHeight(), null);
    }

    public void paintSnake(Graphics g, Monster monster){
        g.drawImage(snake, monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight(), null);
    }

    public void paintScorpion(Graphics g, Monster monster){
        g.drawImage(scorpion, monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight(), null);
    }

    public void paintScore(Graphics g){
        Font font = new Font("华文琥珀", Font.BOLD, 35);
        g.setColor(Color.magenta);
        g.setFont(font);
        g.drawString("Score: " + screen.getScore(), 10, 35);
        g.drawString("Level: " + gourdRole.getLevel(), 10, 75);
    }
}
