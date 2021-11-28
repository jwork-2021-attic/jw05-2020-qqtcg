package tool;

import element.Creature;
import element.Monster;
import screen.Screen;
import screen.WordScreen;

public class ExamineRoomTool {
    WordScreen screen;

    public ExamineRoomTool(Screen screen){
        this.screen = (WordScreen) screen;
    }

    /**
     * 用于检测生成时该随机位置能否成功生成
     * @param creature 用于比较的生物
     * @return result
     */
    public boolean notOver(Creature creature){
        boolean result = true;
        for(Monster monster: screen.getMonsters()){          // 为了防止移动的妖怪和生成的妖怪出现在同一块上，生成在一个位移距离之外
            if (monster != null){
                int mX = monster.getX() - monster.getSpeed();
                int mY = monster.getY() - monster.getSpeed();
                int mmX = monster.getX() + monster.getWidth() + monster.getSpeed();
                int mmY = monster.getY() + monster.getHeight() + monster.getSpeed();
                result = !over(new int[][]{{creature.getX(), creature.getY()}, {creature.getX()+creature.getWidth(), creature.getY()},
                                {creature.getX(), creature.getY()+creature.getHeight()}, {creature.getX()+creature.getWidth(), creature.getY()+creature.getHeight()}},
                        new int[][]{{mX, mY}, {mmX, mY}, {mX, mmY}, {mmX, mmY}});                             // 有覆盖则不能生成
            }
        }
        return result;
    }


    public synchronized Monster whichOver(Creature creature){
        boolean result = true;
        for(Monster monster: screen.getMonsters()){          // 为了防止移动的妖怪和生成的妖怪出现在同一块上，生成在一个位移距离之外
            if (monster != null){
                int mX = monster.getX() - monster.getSpeed();
                int mY = monster.getY() - monster.getSpeed();
                int mmX = monster.getX() + monster.getWidth() + monster.getSpeed();
                int mmY = monster.getY() + monster.getHeight() + monster.getSpeed();
                result = !over(new int[][]{{creature.getX(), creature.getY()}, {creature.getX()+creature.getWidth(), creature.getY()},
                                {creature.getX(), creature.getY()+creature.getHeight()}, {creature.getX()+creature.getWidth(), creature.getY()+creature.getHeight()}},
                        new int[][]{{mX, mY}, {mmX, mY}, {mX, mmY}, {mmX, mmY}});                             // 有覆盖则不能生成
                if (!result){
                    return monster;
                }
            }
        }
        return null;
    }

    public synchronized void aiEatAi(Creature creature) {
        boolean result;
        for (Monster monster : screen.getMonsters()) {
            if (monster != null && creature != monster) {
                int mX = monster.getX() - monster.getSpeed();
                int mY = monster.getY() - monster.getSpeed();
                int mmX = monster.getX() + monster.getWidth() + monster.getSpeed();
                int mmY = monster.getY() + monster.getHeight() + monster.getSpeed();
                result = !over(new int[][]{{creature.getX(), creature.getY()}, {creature.getX() + creature.getWidth(), creature.getY()},
                                {creature.getX(), creature.getY() + creature.getHeight()}, {creature.getX() + creature.getWidth(), creature.getY() + creature.getHeight()}},
                        new int[][]{{mX, mY}, {mmX, mY}, {mX, mmY}, {mmX, mmY}});                             // 有覆盖则不能生成
                if (!result){

                    if (creature.eat(monster) != -1){
                        screen.getMonsters().remove(monster);
                        monster.die();
                        creature.evolve();
                    }
                    else {
                        screen.getMonsters().remove((Monster) creature);
                        creature.die();
                        monster.evolve();
                    }
                    break;
                }

            }
        }

    }

    // 看是两个矩形间否有覆盖
    // src， tar 存着矩形的四个顶点坐标
    // 顺序为上右下左
    // （横坐标，纵坐标）
    public boolean over(int[][] src, int[][] tar){
        boolean result = false;
        for (int[] point: tar){
            if ((src[0][0] < point[0] && src[0][1] < point[1]) &&
                    (src[1][0] > point[0] && src[1][1] < point[1]) &&
                    (src[2][0] < point[0] && src[2][1] > point[1]) &&
                    (src[3][0] > point[0] && src[3][1] > point[1])){
                result = true;
                break;
            }
        }
        return result;

    }
}
