package tankWar.obj;



import tankWar.page.AbstractFrame;

import java.awt.*;
import java.util.ArrayList;

public abstract class  Bullet extends GameObject{

    protected int size = 5;//尺寸
    protected int speed = 9;//速度
    protected Direction direction;//方向

    public Bullet(String image, int x, int y, AbstractFrame gameFrame, Direction direction) {
        super(image, x, y, gameFrame);
        this.direction = direction;
    }


    /**
     *子弹移动的实现
     */
    public void leftWard() {
        x -= speed;
    }

    public void rightWard() {
        x += speed;
    }

    public void upWard() {
        y -= speed;
    }

    public void downWard() {
        y += speed;
    }

    /**
     * 子弹移动方法
     */
    void move(){
        hitWall();
        touchBorder();
        hitHome();
        if(direction == null){upWard();return;}
        switch (direction){
            case UP:        upWard(); break;
            case DOWN:      downWard(); break;
            case RIGHT:     rightWard(); break;
            case LEFT :     leftWard(); break;
            default:break;
        }
    }

    public void hitWall(){
        ArrayList<Wall> walls = this.gameFrame.getWallList();
        for(Wall w :walls){
            if(this.getRec().intersects(w.getRec())){
            	if(w instanceof Water);
            	else if(w instanceof HardWall) {
            		this.gameFrame.getRemoveBulletList().add(this);
            	}else {
            		this.gameFrame.getWallList().remove(w);
            		this.gameFrame.getRemoveBulletList().add(this);
            	}
                break;//果然，这个break必不可少，如果没有，就会出现在遍历列表时修改该列表内容的错误
            }
        }
    }

    public void touchBorder(){
        if(x<6||x+size>this.gameFrame.getWidth()-6||y<30||y+size>this.gameFrame.getHeight()-6){
            this.gameFrame.getRemoveBulletList().add(this);
        }
    }

    public void hitHome(){
        ArrayList<Home> homes = this.gameFrame.getHomeList();
        for(Home h: homes){
            if(this.getRec().intersects(h.getRec())){
                this.gameFrame.getHomeList().remove(h);
                this.gameFrame.getRemoveBulletList().add(this);
                break;
            }
        }
    }

    @Override
    public abstract void paintSelf(Graphics g);
    @Override
    abstract public Rectangle getRec();
}
