import progress.GourdEatMonster;

public class Main {

    // 不使用swing提供的方法线程对组件进行操作可能会产生问题
    // 暂时没有考虑处理
    public static void main(String[] args) {
        GourdEatMonster run = new GourdEatMonster();
        new Thread(run).start();
        try {
            run.gameStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
