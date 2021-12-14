package screen;

import asciiPanel.AsciiPanel;
import progress.State;
import thing.Player;
import thing.Thing;
import thing.World;

import java.awt.event.KeyEvent;

public class WorldScreen implements Screen{
    private World world;
    private Thing[][] things;
    private Player player1;

    public WorldScreen(World world){
        this.world = world;
        things = world.getThings();
        player1 = world.getPlayer1();

    }


    @Override
    public void displayOutput(AsciiPanel terminal) {
        // 显示游戏界面
        for (int i = 0; i < World.WIDTH; i++){
            for (int j = 0; j < World.HEIGHT; j++){
                terminal.write(things[i][j].getSign(), i, j);
            }
        }

        // 显示游戏右侧菜单界面
        terminal.write("HP: "+ world.getHP(), World.WIDTH+2, 5);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        if (!player1.isLive()){
            State.state = "end";
            return new EndScreen(false);
        }
        else if (World.numberOfMonster == 0){
            State.state = "end";
            return new EndScreen(true);
        }
        int x = player1.getX();
        int y = player1.getY();
        switch (key.getKeyCode()){
            case KeyEvent.VK_W -> world.canGoUp(x, y);
            case KeyEvent.VK_S -> world.canGoDown(x, y);
            case KeyEvent.VK_A -> world.canGoLeft(x, y);
            case KeyEvent.VK_D -> world.canGoRight(x, y);
            case KeyEvent.VK_J -> new Thread( ()->{player1.attack();} ).start();
        }
        return this;
    }
}
