package tankWar.obj;



import tankWar.imageFactory.MyFactory;
import tankWar.page.AbstractFrame;

import java.awt.*;

public class Burst extends GameObject{

    private static  Image[] images = new Image[15];
    private int burstCount = 0;
    private int size = 40;

    public static void loadImage(){
        for(int i=0;i<15;i++){
            images[i] = Toolkit.getDefaultToolkit().createImage(MyFactory.getTempFilePath("resource/imageForTank/largeBurst" +i+".gif"));
        }
    }

    public Burst(String image, int x, int y, AbstractFrame gameFrame) {
        super(image, x, y, gameFrame);
    }

    @Override
    public void paintSelf(Graphics g) {

        if(burstCount<15){
            g.drawImage(images[burstCount],x,y,size,size, gameFrame);
            burstCount++;
        }
    }

    @Override
    public Rectangle getRec() {
        return null;
    }


}
