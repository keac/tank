public class MT extends Tank {

    MT(int x, int y) {
        super(x, y);
    }
    public void up(){
        y-=speed;
    }
    public void left(){
        x-=speed;
    }
    public void lower(){
        y+=speed;
    }
    public void right(){
        x+=speed;
    }
}
