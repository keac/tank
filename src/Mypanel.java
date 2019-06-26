import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class Mypanel extends Panel implements KeyListener, Runnable {

    Image Ipanel;
    Graphics gPanel;

    MT myTank;
    int enemyNum =10;
    Vector<EnemyTank> enemyTanks = new Vector<EnemyTank>();

    Mypanel() {
        myTank = new MT(120, 220);
        //新建地方谈，使用构造函数来出事话坦克坐标
        for (int i = 0; i < enemyNum; i++) {
            //新建敌方坦克对象
            EnemyTank enemyTank = new EnemyTank((i) * 181 + 5, 0);
            Thread enTank=new Thread(enemyTank);
            enTank.start();
            //往集合类里添加坦克
            enemyTanks.add(enemyTank);
        }

    }


    public void paint(Graphics g) {
        super.paint(g);

//        g.setColor(new Color(0, 0, 0));
//        g.fillRect(0, 0, 800, 800);
        Toolkit tool = this.getToolkit();
        Image image=tool.getImage("b.jpg");
        g.drawImage(image, 0, 0, 800,800, this);//设定位置
        if (myTank.life) {
            g.setColor(new Color(208,0, 20));
            g.drawRect(myTank.getX(), myTank.getY()+40,20,2);
            g.fillRect(myTank.getX(), myTank.getY()+40,myTank.life_i*10,2);
            this.drawTank(myTank.getX(), myTank.getY(), g, 0, myTank.getDirection());
        }

        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.life) {
                g.drawRect(enemyTank.getX(), enemyTank.getY()+40,20,2);
                g.fillRect(enemyTank.getX(), enemyTank.getY()+40,enemyTank.life_i*10,2);
                this.drawTank(enemyTank.getX(), enemyTank.getY(), g, 1, enemyTank.getDirection());
            }
            if (!enemyTank.life)
                enemyTanks.remove(enemyTank);
        }
        for (int i = 0; i < myTank.bulletVector.size(); i++) {
            Bullet bullet = myTank.bulletVector.get(i);
            if (bullet != null && bullet.life) {
                g.setColor(new Color(0, 100 + (int) (Math.random() * 155), 100 + (int) (Math.random() * 155)));
                g.fillOval(bullet.x, bullet.y, 10, 10);
            }
            if (!bullet.life)
                myTank.bulletVector.remove(bullet);
        }
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.life) {
                for (int j = 0; j < enemyTank.bulletVector.size(); j++) {
                    Bullet bullet = enemyTank.bulletVector.get(j);
                    if (bullet != null && bullet.life) {
                        g.setColor(new Color(0, 100 + (int) (Math.random() * 155), 100 + (int) (Math.random() * 155)));
                        g.fillOval(bullet.x, bullet.y, 10, 10);
                    }
                    if (!bullet.life)
                        enemyTank.bulletVector.remove(bullet);
                }
            }
        }



    }

    public void drawTank(int x, int y, Graphics g, int type, int direction) {
        switch (type) {
            case 0: //Ny
                g.setColor(new Color(100 + (int) (Math.random() * 155), 100 + (int) (Math.random() * 155), 100 + (int) (Math.random() * 155)));
                break;
            case 1:
                g.setColor(new Color((int) (Math.random() * 200), 0, 0));
                break;
        }
        switch (direction) {
            case 0:
                super.paint(g);
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 15, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 15, x + 10, y - 3);
                break;
            case 1:
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x + 15, y + 10, x - 3, y + 10);
                break;
            case 2:
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 15, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 15, x + 10, y + 33);
                break;
            case 3:
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x + 15, y + 10, x + 33, y + 10);
                break;
        }
    }

    public void judge(Bullet bullet, EnemyTank enemyTank) {
        switch (enemyTank.direction) {
            case 0:
            case 2:
                if (bullet.x > enemyTank.x && bullet.x < enemyTank.x + 20 && bullet.y > enemyTank.y && bullet.y < enemyTank.y + 30) {
                    bullet.life = false;
                    enemyTank.attack();
                }
                break;
            case 1:
            case 3:
                if (bullet.x > enemyTank.x && bullet.x < enemyTank.x + 30 && bullet.y > enemyTank.y && bullet.y < enemyTank.y + 20) {
                    bullet.life = false;
                    enemyTank.attack();
                }
        }
    }

    public void EJudge(Bullet bullet, MT myTank) {
        switch (myTank.direction) {
            case 0:
            case 2:
                if (bullet.x > myTank.x && bullet.x < myTank.x + 20 && bullet.y > myTank.y && bullet.y < myTank.y + 30) {
                    bullet.life = false;
                    myTank.attack();
                }
                break;
            case 1:
            case 3:
                if (bullet.x > myTank.x && bullet.x < myTank.x + 30 && bullet.y > myTank.y && bullet.y < myTank.y + 20) {
                    bullet.life = false;
                    myTank.attack();
                }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    boolean U=false,L=false,R=false,D=false;
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                L=true;
                break;
            case KeyEvent.VK_D:
                R=true;
                break;
            case KeyEvent.VK_W:
                U=true;
                break;
            case KeyEvent.VK_S:
                D=true;
                break;
        }
        if(this.myTank.x<0||this.myTank.x>800||this.myTank.y<0||this.myTank.y>800) {
            this.myTank.x=(int) (Math.random() * 800);
            this.myTank.y=(int) (Math.random() * 800);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (this.myTank.bulletVector.size() < 20&&this.myTank.life)
                myTank.fire(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            if (this.myTank.bulletVector.size() < 100&&this.myTank.life)
                myTank.fire(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_G) {
            for (int i = 0; i < (int)(Math.random()*5); i++) {
                //新建敌方坦克对象
                EnemyTank enemyTank = new EnemyTank((i) * 181 + 5, 0);
                Thread enTank=new Thread(enemyTank);
                enTank.start();
                //往集合类里添加坦克
                enemyTanks.add(enemyTank);
            }
        }

//        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                L=false;
                break;
            case KeyEvent.VK_D:
                R=false;
                break;
            case KeyEvent.VK_W:
                U=false;
                break;
            case KeyEvent.VK_S:
                D=false;
                break;
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println("Fu***");
            }
            if(!myTank.life){
                int res=JOptionPane.showConfirmDialog(null, "再来一次", "再来一次", JOptionPane.YES_NO_OPTION);
               if(res==1){
                   JOptionPane.showMessageDialog(null, "很遗憾，钱没有冲够，游戏结束", "很遗憾，游戏结束", JOptionPane.ERROR_MESSAGE);
                   break;
               }

               else{
                   myTank.life=true;
                   myTank.life_i=2;
               }

            }
            if(!U&&!D&&L&&!R){
                this.myTank.setDirection(1);
                this.myTank.left();
                System.out.println("L");
            }else if(!U&&!D&&!L&&R){
                this.myTank.setDirection(3);
                this.myTank.right();
            }else if(U&&!D&&!L&&!R){
                this.myTank.setDirection(0);
                this.myTank.up();
            }else if(!U&&D&&!L&&!R){
                this.myTank.setDirection(2);
                this.myTank.lower();
            }else if(U&&!D&&L&&!R){
                this.myTank.setDirection(1);
                this.myTank.up();
                this.myTank.left();
            } else if (!U && D && L && !R) {
                this.myTank.setDirection(3);
                this.myTank.lower();
                this.myTank.left();
            } else if (U && !D && !L && R) {
                this.myTank.setDirection(0);
                this.myTank.up();
                this.myTank.right();
            } else if (!U && D && !L && R) {
                this.myTank.setDirection(2);
                this.myTank.right();
                this.myTank.lower();
            }



            for(int i=0;i<myTank.bulletVector.size();i++){
                Bullet bullet=myTank.bulletVector.get(i);
                if(bullet.life&&myTank.life){
                    for(int j=0;j<enemyTanks.size();j++){
                        EnemyTank enemyTank =enemyTanks.get(j);
                        if(enemyTank.life)
                            this.judge(bullet,enemyTank);
                    }
                }
            }
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.life) {
                    for (int j = 0; j < enemyTank.bulletVector.size(); j++) {
                        Bullet bullet=enemyTank.bulletVector.get(j);
                        if (bullet.life) {
//                            for(int p=0;p<enemyTanks.size();p++){
//                                EnemyTank enemyTank1 =enemyTanks.get(p);
//                                if(enemyTank.life)
//                                    this.judge(bullet,enemyTank1);
//                            }

                                if (myTank.life)
                                    this.EJudge(bullet, myTank);
                        }
                    }
                }
            }

            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.life) {
                    switch (enemyTank.direction) {
                        case 0:
                            enemyTank.up();
                            break;
                        case 1:
                            enemyTank.left();
                            break;
                        case 2:
                            enemyTank.lower();
                            break;
                        case 3:
                            enemyTank.right();
                            break;
                    }
                }
            }

            this.repaint();
        }
    }
}
