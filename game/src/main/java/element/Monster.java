package element;

public class Monster extends Creature{

    private String kind;
    private int worth;

    public Monster(int x, int y, int speed, int level, String kind){
        super(x, y, speed, level);
        this.kind = kind;
        worth = level;
    }

    public String getKind(){
        return kind;
    }

    public int getWorth(){
        return worth;
    }
}
