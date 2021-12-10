package thing;

public class Thing {
    private int x;
    private int y;
    private int live;
    private final char sign;


    public Thing(char sign, int live){
        this.sign = sign;
        this.live = live;

    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public char getSign(){
        return sign;
    }

    public void hurt(){
        live--;
    }

    public void care(){
        live++;
    }

    public boolean isLive(){
        return live > 0;
    }
}
