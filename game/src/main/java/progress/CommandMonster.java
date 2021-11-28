package progress;

import element.Creature;
import element.Monster;
import screen.WordScreen;
import tool.ExamineRoomTool;

import java.util.Random;

public class CommandMonster implements Runnable{
    private static int number = 0;
    private ExamineRoomTool examineRoomTool;
    private WordScreen screen;
    private Monster monster;

    public CommandMonster(WordScreen screen){
        this.screen = screen;
        examineRoomTool = new ExamineRoomTool(screen);
        number++;
        System.out.println("Create : " + number);
    }

    public void run(){
        // 随机生成妖怪
        Random r = new Random();
        String kind = (r.nextInt(2) == 0) ? "snake" : "scorpion";
        int level = r.nextInt(2) + 1;
        while (true){
            int x = r.nextInt(1200);
            int y = r.nextInt(800);
            int speed = r.nextInt(2) + 1;
            if (examineRoomTool.notOver(monster = new Monster(x, y, speed, level, kind))){
                screen.addMonsters(monster);                        // 生成成功，加入wordScreen中

                break;
            }
        }

        // 暂时先忽略走了重叠在一起的问题，先写完主体再来考虑
        // 怪物移动逻辑需要改善
        boolean destination = r.nextInt(2) == 0;
        while (monster.getLives() > 0){
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*int destination = r.nextInt(4);
            switch (destination){
                case 0 -> monster.moveUp();
                case 1 -> monster.moveRight();
                case 2 -> monster.moveDown();
                case 3 -> monster.moveLeft();
            }*/

            int destinationUD = r.nextInt(10);
            if (destination){
                monster.moveLeft();
                if (monster.getX() == 0){
                    destination = false;
                }
            }
            else {
                monster.moveRight();
                if (monster.getX() == 1199 - monster.getWidth()){
                    destination = true;
                }
            }

            if (destinationUD == 1){
                monster.moveUp();
            }
            else if (destinationUD == 0){
                monster.moveDown();
            }

            //examineRoomTool.aiEatAi(monster);
        }
        number--;
    }

    public synchronized static boolean notFull(){
        return number < 15;
    }

    public static int get(){
        return number;
    }




}
