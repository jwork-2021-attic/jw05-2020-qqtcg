package thing;

public class Bloom extends Thing{
    int times;         // 爆炸时间，单位毫秒
    int range;         // 爆炸范围
    World world;

    public Bloom(int times, int range, World world) {
        super((char)8, 1);
        this.times = times;
        this.range = range;
        this.world = world;
    }

    public void explore() throws Exception{
        Thread.sleep(this.times);          // 先放在这里，不过感觉放在外面更容易控制暂停
        world.dealExplore(this.getX(), this.getY(), this.range);
        System.out.println(getName() + " explore");
    }

    public int getRange(){
        return range;
    }

    public String getName(){
        return "bloom";
    }


}
