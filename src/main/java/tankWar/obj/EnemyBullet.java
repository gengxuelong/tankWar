package tankWar.obj;



import tankWar.page.AbstractFrame;

import java.awt.*;
import java.util.ArrayList;

public class EnemyBullet extends Bullet{
    public EnemyBullet(String image, int x, int y, AbstractFrame gameFrame, Direction direction) {
        super(image, x, y, gameFrame, direction);
    }

    public void paintSelf(Graphics g) {

        g.drawImage(image,x,y,size,size,this.gameFrame);
        move();
        hitPlayer();
    }

    public Rectangle getRec(){

        return new Rectangle(x,y,size,size);
    }

    /**
     * 检测我方和坦克是否和敌方子弹相撞，相撞后子弹和坦克均消失
     */
    public void hitPlayer(){
        ArrayList<Tank> plays = this.gameFrame.getPlayerList();
        for(Tank b : plays){
            if(this.getRec().intersects(b.getRec()))
            {
                b.alive = false;
                this.gameFrame.getPlayerList().remove(b);
                this.gameFrame.getRemoveBulletList().add(this);
                Burst burst = new Burst(null,b.x-10,b.y-10,this.gameFrame);
                this.gameFrame.getBurstList().add(burst);
                break;
            }
        }
    }
}
