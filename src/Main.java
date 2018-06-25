import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    Mypanel myPanel=null;
    public static void main(String args[]){
        new Main();
    }
    Main(){
        myPanel=new Mypanel();
        this.add(myPanel);
        this.addKeyListener(myPanel);
        this.setSize(1000,1000);
        this.setLocation(0,0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗体并且关闭进程
        this.setVisible(true);
    }
}
