package tankWar.obj;



import tankWar.page.AbstractFrame;

import java.awt.*;

public class Wall extends GameObject{

    private int length = 25;
    public Wall(String image, int x, int y, AbstractFrame gameFrame) {
        super(image, x, y, gameFrame);
    }

    @Override
    public void paintSelf(Graphics g) {

        g.drawImage(image,x,y,length,length,this.gameFrame);
    }

    @Override
    public Rectangle getRec() {

        return new Rectangle(x,y,length,length);
    }
}
