package utils;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel{

        private Image image ;
        public void setImage(Image image1) {
            this.image = image1;
        }

        public void paintComponent(Graphics g){//观察者null时无法划出图片，但观察者为this时便可以画出图片,原因未知
             if(image !=null){
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
            }
            super.paintComponent(g);//如果还要保留容器中的原本组件就别忘了调用super.paintComponent(g)。(CSDN)
        }
        public BackgroundPanel() {
            setLayout(null);
            setOpaque(false);
        }
    }
