package tankWar.obj;



import tankWar.page.AbstractFrame;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerOne extends Tank {


    public PlayerOne(String image, int x, int y, AbstractFrame gameFrame, Direction direction,
                     String right_I, String left_I, String up_I, String down_I) {
        super(image, x, y, gameFrame, direction, right_I, left_I, up_I, down_I);
    }


    public void paintSelf(Graphics g) {

        this.move();

        if (direction == null) {
            g.drawImage(image, x, y, gameFrame);
        }
        if (direction == Direction.DOWN) {
            g.drawImage(down_I, x, y, gameFrame);
        }
        if (direction == Direction.UP) {
            g.drawImage(up_I, x, y, gameFrame);
        }
        if (direction == Direction.RIGHT) {
            g.drawImage(right_I, x, y, gameFrame);
        }
        if (direction == Direction.LEFT) {
            g.drawImage(left_I, x, y, gameFrame);
        }
    }
    public void attack(){
        if(isCoolDown&&alive){
            MyBullet bullet = new MyBullet("resource/imageForTank/myBullet.jpg",
                    getPointOfMouth().x, getPointOfMouth().y, this.gameFrame,this.direction);
            this.gameFrame.getMyBulletList().add(bullet);
            new Thread(myThread).start();//新线程必须写在if里面，否则如何如果连续按发射键，则会开无数个线程，
            //数秒后，子弹的发射频率就会变成你按按键的频率，就没有了控制速度的效果
        }

        //当用start时，代表新开一个线程，当用run时，代表的只是一个普通函数，在主线程中运行
        /*
        通过上网查询发现同一个Thread不能重复调用start方法，做如下修改就好了：
        将自己定义的HubRobot extends Thread 线程类改成HubRobot implements Runnable，
        后用new Thread(robot).start 就可以启动多次了
         */
        /*
        利用报错“路径追踪”可以很方便的判断出错误来源
         */
    }

    @Override
    public Rectangle getRec() {

        return new Rectangle(x,y,width, height);
    }


    boolean left;
    boolean right;
    boolean up;
    boolean down;
    public void move() {
        if (left) {
            leftWard();
        }
        if (right) {
            rightWard();
        }
        if (up) {
            upWard();
        }
        if (down) {
            downWard();
        }
    }





    /**
     * 键盘事件
     */
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_S:
                down = true;
                break;
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_SPACE:
                attack();

            default:
                break;
        }
    }

    public void keyRelease(KeyEvent e) {

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            default:
                break;
        }
    }




}
