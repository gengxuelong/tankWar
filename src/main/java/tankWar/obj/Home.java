package tankWar.obj;




import tankWar.page.AbstractFrame;

import java.awt.*;

public class Home extends GameObject{

    private int size = 25;
    public Home(String image, int x, int y, AbstractFrame gameFrame) {
        super(image, x, y, gameFrame);
    }

    public void paintSelf(Graphics g) {

        g.drawImage(image,x,y,size,size,this.gameFrame);
    }

    public Rectangle getRec(){

        return new Rectangle(x,y,size,size);
    }
}
