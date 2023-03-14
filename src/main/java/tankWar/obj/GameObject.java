package tankWar.obj;




import tankWar.imageFactory.MyFactory;
import tankWar.page.AbstractFrame;

import java.awt.*;

public abstract class GameObject {

    protected Image image;
    protected int x;
    protected int y;
    protected AbstractFrame gameFrame;

    public GameObject(String image, int x, int y, AbstractFrame gameFrame) {
        this.image = Toolkit.getDefaultToolkit().createImage(MyFactory.getTempFilePath(image));
        this.x = x;
        this.y = y;
        this.gameFrame = gameFrame;
    }

    public abstract void paintSelf(Graphics g);
    public abstract Rectangle getRec();


}
