import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    Vector<Bullet> bulletVector = new Vector<Bullet>();
    Bullet bullet = null;
    int speed = 3;

    EnemyTank(int x, int y) {
        super(x, y);
        this.direction = 2;
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

    public void fire() {
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
        if (this.life) {
            Thread threadFire = new Thread(bullet);
            threadFire.start();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Fu***");
            }
            if (((int) (Math.random() * 4)) == 1) {
                this.fire();
            }
            EnemyTank enemyTank = this;
            if (enemyTank.life) {
                if (enemyTank.x < 0 || enemyTank.x > 800 || enemyTank.y < 0 || enemyTank.y > 800) {
                    enemyTank.x = (int) (Math.random() * 800);
                    enemyTank.y = (int) (Math.random() * 800);
                }
                this.direction = (int) (Math.random() * 4);
            }
        }
    }
}
