package screen;

import element.Gourd;
import element.Monster;
import progress.CommandMonster;
import progress.GourdEatMonster;
import progress.PlayerOperation;
import tool.ExamineRoomTool;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class WordScreen extends JFrame implements Screen {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private int score = 0;
    private int level;
    private Gourd gourd;
    private GamePanel game;
    private EndPanel end;
    private ArrayList<Monster> monsters = new ArrayList<>();
    private PlayerOperation operation;                  // 处理键盘（或者鼠标）事件
    private ExamineRoomTool examineRoomTool;
    private GourdEatMonster gourdEatMonster;



    public WordScreen(String name, Gourd gourd, GourdEatMonster gourdEatMonster){
        super(name);
        this.gourd = gourd;
        level = gourd.getLevel();
        game = new GamePanel(this);
        operation = new PlayerOperation(this);
        examineRoomTool = new ExamineRoomTool(this);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(game);
        this.setVisible(true);
        this.addKeyListener(operation);
        this.gourdEatMonster = gourdEatMonster;
        repaint();
    }

    public Screen respondToUserInput(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> gourd.moveLeft();
            case KeyEvent.VK_RIGHT -> gourd.moveRight();
            case KeyEvent.VK_UP -> gourd.moveUp();
            case KeyEvent.VK_DOWN -> gourd.moveDown();
        }


        return this;
    }

    @Override
    public void repaint(){
        super.repaint();
        game.paint(this.getGraphics());
    }

    public Gourd getGourd(){
        return gourd;
    }

    public synchronized void addMonsters(Monster monster){
        monsters.add(monster);

    }

    public ArrayList<Monster> getMonsters(){
        return monsters;
    }

    public int getScore(){
        return score;
    }



    // 循环判断是否有妖怪和葫芦娃重合，有则吃掉(能吃就吃，不能吃就去世)
    public void eventMonitor(){
        Monster thisMonster = examineRoomTool.whichOver(gourd);
        if (thisMonster != null){
            if (gourd.eat(thisMonster) != -1){
                score += gourd.eat(thisMonster);

                if (score >= 3*gourd.getLevel()){             // 进化
                    gourd.evolve();
                }
                monsters.remove(thisMonster);
                thisMonster.die();

            }
            else {
                gourdEatMonster.endGame(false);
            }
        }

        if (score >= 20){
            gourdEatMonster.endGame(true);
        }

    }

    public synchronized void end(boolean win){
        this.remove(game);
        this.add(end = new EndPanel(this, win));
        this.paintEnd();
    }

    public void paintEnd(){
        super.repaint();
        end.paint(this.getGraphics());
    }

}
