package screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartPanel extends JPanel {
    private WordScreen screen;
    private BufferedImage startBackground;

    public StartPanel(WordScreen screen){
        this.screen = screen;
        try {
            startBackground = ImageIO.read(new File("img/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(startBackground, 0, 0, 1200, 800, null);
        g.drawString("按空格以开始游戏", 400, 200);
        Font font = new Font("华文行楷", Font.BOLD, 140);
        g.setColor(Color.yellow);
        g.setFont(font);
        g.drawString("葫芦娃吃妖怪",  200, 200);


    }
}
