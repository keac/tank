public class Bullet implements  Runnable {
    int x,y;
    int speed=5;
    int direction = 0;
    boolean life=true;
    Bullet(int x,int y,int direction){
        this.x=x;
        this.y=y;
        this.direction=direction;
    }
    @Override
    public void run() {
        while (true){

            try{
                Thread.sleep(50);
            }catch (Exception e){
                System.out.println("Fu***");
            }
            switch (direction){
                case 0:
                    y-=speed;
                    break;
                case 1:
                    x-=speed;
                    break;
                case 2:
                    y+=speed;
                    break;
                case 3:
                    x+=speed;
                    break;
            }
            if(x<0||x>800||y<0||y>800){
                this.life=false;
                break;
            }
        }
    }
}
