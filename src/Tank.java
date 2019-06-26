public class Tank {
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getSpeed() {
        return speed;
    }
    boolean life=true;
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    int x, y;
    int speed = 3;
   int direction = 0;
   int life_i=2;
   int life_y=life_i;
    public  void attack(){
        if(life_i<=1)
            this.life=false;
        else
            life_i--;
    }
    Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
