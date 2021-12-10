package screen;

import asciiPanel.AsciiPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartScreen implements Screen{



    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.write((char)1, 0, 0);
        terminal.writeCenter("Welcome to my game!", 8);
        terminal.writeCenter("press B to start", 14);

    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_B) {
            return new WorldScreen();
        }
        return this;

    }
}
