import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Mypanel extends Panel implements KeyListener {
    MT myTank;
    Mypanel() {
        myTank = new MT(120, 220);
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color((int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10)));
        g.fillRect(0, 0, 1000, 1000);
        g.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        drawTank(myTank.getX(), myTank.getY(), g, 0, myTank.getDirection());
    }

    public void drawTank(int x, int y, Graphics g, int type, int direction) {
        switch (type) {
            case 0: //Ny
                g.setColor(new Color(100+(int) (Math.random() * 155), 100+(int) (Math.random() * 155), 100+(int) (Math.random() * 155)));
                break;
            case 1:
                g.fillOval(myTank.getX() + 15, myTank.getY() + 10, 10, 10);
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
                g.fill3DRect(x ,y+ 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x + 15, y + 10, x -3, y + 10);
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            this.myTank.setDirection(0);
            this.myTank.up();
        }else if(e.getKeyCode()==KeyEvent.VK_A){
            this.myTank.setDirection(1);
            this.myTank.left();
        }else if(e.getKeyCode()==KeyEvent.VK_S){
            this.myTank.setDirection(2);
            this.myTank.lower();
        }else if(e.getKeyCode()==KeyEvent.VK_D){
            this.myTank.setDirection(3);
            this.myTank.right();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
