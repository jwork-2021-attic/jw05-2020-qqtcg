package screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EndPanel extends JPanel {
    private WordScreen screen;
    private BufferedImage endBackground;
    private boolean win;

    public EndPanel(WordScreen screen, boolean win){
        this.screen = screen;
        this.win = win;
        try {
            endBackground = ImageIO.read(new File("img/start.png"));       // 先用开始界面图片
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(endBackground, 0, 0, 1200, 800, null);
        Font font = new Font("华文行楷", Font.PLAIN, 200);
        g.setFont(font);
        if (win){
            g.drawString("Congratulation!", 100, 400);
        }
        else {
            g.drawString("You lost.", 200, 400);
        }

    }
}
