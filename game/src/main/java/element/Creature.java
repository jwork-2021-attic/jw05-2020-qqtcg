package element;

import screen.WordScreen;

public class Creature implements Comparable<Creature>{
    protected int x;
    protected int y;
    protected int speed;
    protected int width;
    protected int height;      //宽高用于碰撞检测
    protected int level;       //用于检测谁吃谁
    protected int lives = 1;

    public Creature(int x, int y, int speed, int level){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.level = level;
        width = level * 25;
        height = level * 25;
    }

    public void evolve(){
        level++;
        width = level * 25;
        height = level * 25;
    }

    public int getLives(){
        return lives;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getSpeed(){
        return speed;
    }

    public int getLevel(){
        return level;
    }

    public void die(){
        lives = 0;
    }

    public synchronized void moveRight(){
        for (int i = 0; i < speed; i++){
            if (x + width< WordScreen.WIDTH - 1){
                x ++;
            }
        }

    }

    public synchronized void moveLeft(){
        for (int i = 0; i < speed; i++){
            if (x > 0){
                x --;
            }
        }

    }

    public synchronized void moveUp(){
        for (int i = 0; i < speed; i++){
            if (y > 0){
                y --;
            }
        }

    }

    public synchronized void moveDown(){
        for (int i = 0; i < speed; i++){
            if (y + height < WordScreen.HEIGHT - 1){
                y ++;
            }
        }

    }

    /**
     * 判断能不能吃怪兽，能吃返回吃掉的分数，不能则返回-1
     * @param monster 尝试吃的妖怪
     * @return 返回分数
     */
    public synchronized int eat(Monster monster){         // 吃掉生物
        if (this.level >= monster.level){
            return monster.getWorth();
        }
        else {
            return -1;
        }
    }


    @Override
    public int compareTo(Creature o) {
        if (this.level >= o.level){
            return 1;
        }
        else {
            return -1;
        }
    }
}
