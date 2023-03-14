package tankWar.obj;




import tankWar.page.AbstractFrame;

import java.awt.*;
import java.util.ArrayList;

public class MyBullet extends Bullet{
    public MyBullet(String image, int x, int y, AbstractFrame gameFrame, Direction direction) {
        super(image, x, y, gameFrame, direction);
    }

    @Override
    public void paintSelf(Graphics g) {

        g.drawImage(image,x,y,size,size,this.gameFrame);
        move();
        hitBot();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,size,size);
    }

    /**
     * 检测和敌方坦克是否相撞，相撞后子弹和坦克均消失
     */
    public void hitBot(){
        ArrayList<Bot> bots = this.gameFrame.getBotList();
        for(Bot b : bots){
            if(this.getRec().intersects(b.getRec()))
            {
                this.gameFrame.getBotList().remove(b);
                this.gameFrame.getRemoveBulletList().add(this);
                Burst burst = new Burst(null,b.x-10,b.y-10,this.gameFrame);
                this.gameFrame.getBurstList().add(burst);
                break;
            }
        }
    }
}
