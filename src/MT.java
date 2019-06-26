import java.util.Vector;

public class MT extends Tank {

    Vector<Bullet> bulletVector = new Vector<Bullet>();
    Bullet bullet = null;
    int speed=10;
    MT(int x, int y) {
        super(x, y);
    }

    public void up() {
        y -= speed;
    }

    public void left() {
        x -= speed;
    }

    public void lower() {
        y += speed;
    }

    public void right() {
        x += speed;
    }

    public void fire(int t) {

        if(t==1){
            bullet = new Bullet(x + 5, y + 1, 0);
            bulletVector.add(bullet);
            Thread threadFireUp = new Thread(bullet);
            threadFireUp.start();
            bullet = new Bullet(x, y + 5, 1);
            bulletVector.add(bullet);
            Thread threadFired = new Thread(bullet);
            threadFired.start();
            bullet = new Bullet(x + 10, y + 30, 2);
            bulletVector.add(bullet);
            Thread threadFirer = new Thread(bullet);
            threadFirer.start();
            bullet = new Bullet(x + 30, y + 10, 3);
            bulletVector.add(bullet);
            Thread threadFirel = new Thread(bullet);
            threadFirel.start();

        }else if (this.life) {
            switch (this.direction) {
                case 0:
                    bullet = new Bullet(x + 5, y + 1, 0);
                    bulletVector.add(bullet);
                    break;
                case 1:
                    bullet = new Bullet(x, y + 5, 1);
                    bulletVector.add(bullet);
                    break;
                case 2:
                    bullet = new Bullet(x + 10, y + 30, 2);
                    bulletVector.add(bullet);
                    break;
                case 3:
                    bullet = new Bullet(x + 30, y + 10, 3);
                    bulletVector.add(bullet);
                    break;
            }

            Thread threadFire = new Thread(bullet);
            threadFire.start();
        }
    }
}
