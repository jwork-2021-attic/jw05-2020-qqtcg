package progress;

import element.Gourd;
import screen.WordScreen;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GourdEatMonster extends Thread implements Runnable{
    // 没抽象好，有时间再来
    private WordScreen screen;
    private Gourd gourd;
    private boolean gameRun = true;
    private boolean win;

    public GourdEatMonster(){

        gourd = new Gourd(600, 400, 10, 1);
        this.screen = new WordScreen("葫芦娃吃妖怪", gourd, this);
    }

    // 用一个线程来刷新屏幕
    // 为每个妖怪创建一个线程
    public void gameStart(){
        ExecutorService exec = Executors.newCachedThreadPool();
        while (gameRun){
            // int temp = CommandMonster.get(); // 用于观测number变化

            if (CommandMonster.notFull()){
                exec.submit(new CommandMonster(screen));
            }
        }

        screen.end(win);


    }


    // 用于刷新屏幕
    //
    // 屏幕刷新抖动问题不知道如何解决
    //
    public void run() {
        while (gameRun){
            try {
                Thread.sleep(50);
                screen.repaint();
                screen.eventMonitor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void endGame(boolean win){
        gameRun = false;
        this.win = win;
    }



}
