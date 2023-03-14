package tankWar.obj;



import tankWar.imageFactory.MyFactory;
import tankWar.page.AbstractFrame;

import java.awt.*;
import java.util.ArrayList;

public abstract class Tank extends GameObject {

    protected Direction direction;
    protected Image right_I;
    protected Image left_I;
    protected Image up_I;
    protected Image down_I;

    protected int width = 25;
    protected int height = 25;
    protected int speed = 4;
    protected boolean alive = true;

    protected MyThread myThread = new MyThread();

    public Tank(String image, int x, int y, AbstractFrame gameFrame,
                Direction direction,  String right, String left, String up, String down) {
        super(image, x, y, gameFrame);
        this.direction = direction;
        this.right_I = Toolkit.getDefaultToolkit().createImage(MyFactory.getTempFilePath(right));
        this.left_I = Toolkit.getDefaultToolkit().createImage(MyFactory.getTempFilePath(left));
        this.up_I = Toolkit.getDefaultToolkit().createImage(MyFactory.getTempFilePath(up));
        this.down_I = Toolkit.getDefaultToolkit().createImage(MyFactory.getTempFilePath(down));
    }


    /**
     * 发射子弹的方法
     */
    public abstract void attack();

    /**
     * 获取炮口的位置坐标
     */
    Point getPointOfMouth(){
        if(direction == null) return new Point(x+width/2-2,y-2);
        switch(direction){
            case UP:
                return new Point(x+width/2-2,y-2);
            case DOWN:
                return new Point(x+width/2-2,y+height+2);
            case RIGHT:
                return new Point(x+width,y+height/2-2);
            case LEFT:
                return new Point(x-2,y+height/2-2);
            default:return null;
        }
    }

    /**
     * 各方向移动的具体实现
     */
    public void leftWard() {
        if(!touchWall(x-speed,y)&&!touchBorder(x-speed,y))
        x -= speed;
        direction = Direction.LEFT;
    }
    public void rightWard() {
        if(!touchWall(x+speed,y)&&!touchBorder(x+speed,y))
        x += speed;
        direction = Direction.RIGHT;
    }
    public void upWard() {
        if(!touchWall(x,y-speed)&&!touchBorder(x,y-speed))
        y -= speed;
        direction = Direction.UP;
    }
    public void downWard() {
        if(!touchWall(x,y+speed)&&!touchBorder(x,y+speed))
        y += speed;
        direction = Direction.DOWN;
    }

    /**
     * 元素类都有的抽象方法
     */
    public abstract void paintSelf(Graphics g);
    public abstract Rectangle getRec();

    /**
     * 围墙判断
     */
    public boolean touchWall(int x,int y){
        ArrayList<Wall> walls = this.gameFrame.getWallList();
        Rectangle next = new Rectangle(x,y,width,height);
        for(Wall w :walls){
            if(next.intersects(w.getRec())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 边界判断
     */
    public boolean touchBorder(int x,int y){
        if(x<6||x+width>this.gameFrame.getWidth()-6){
            return true;
        }
        if(y<30||y+height>this.gameFrame.getHeight()-6){
            return true;
        }
        return false;
    }







    boolean isCoolDown = true;//判断是否已经冷却，可以再次发射
    int coolDownTime = 600;//冷却的时间
    /**
     * 内部线程，控制子弹发射的时间间隔，通过控制开关来实现控制子弹是否发射
     */
    protected class MyThread implements Runnable{

        public void run(){
            isCoolDown = false;
            try {
                Thread.sleep(coolDownTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isCoolDown = true;
        }
    }

}