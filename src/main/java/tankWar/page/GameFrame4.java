package tankWar.page;



import tankWar.imageFactory.MyFactory;
import tankWar.obj.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * @author gengxuelong
 */
public class GameFrame4 extends AbstractFrame {

    private final int WIDTH = 1200;
    private final int HEIGHT = 660;
    private int timeCount = 0;
    private int enemyCount = 0;
    public void init() {
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addKeyListener(new KeyAction());

        playerList.add(playerOne);//装载玩家一
        loadingWall();//装载围墙
        homeList.add(home);//装载老母
        Burst.loadImage();
        burstList.add(new Burst(null, 0, 0, this));//爆炸元素装载图片
        //第一个爆炸总是无法绘制，那就事先添加一个“哑弹”，这样就能实现第一发碰撞事件的爆炸能正常，至于第一颗哑弹的原因我也不知道

        new Timer(delay, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (botList.size() == 0 & (enemyCount == 30)) {
                    state = 5;//胜利
                }
                if (playerList.size() == 0 || homeList.size() == 0) {
                    state = 4;//失败
                }

                //依次装载敌方坦克
                if (timeCount % 80 == 0 && enemyCount <= 30 && (state == 1 || state == 2)) {//一开始的时候state=0；不装载敌人坦克
                    int x_ran = 0;
                    int y_ran = 0;
                    x_ran = new Random().nextInt(1200);
                    y_ran = new Random().nextInt(400);
                    if (touchWithWall(x_ran, y_ran)) {
                        Bot bot = new Bot("resource/imageForTank/enemyDown.jpG", x_ran, y_ran,
                                th, Direction.getRandomDir(), "resource/imageForTank/enemyRight.jpG",
                                "resource/imageForTank/enemyLeft.jpG", "resource/imageForTank/enemyUp.jpG",
                                "resource/imageForTank/enemyDown.jpG");
                        botList.add(bot);
                        enemyCount++;
                    }
                }
                //重新绘制函数
                repaint();
            }
        }).start();
        System.out.println("成功启动定时器，启动后输出");
    }

    public GameFrame4() {
        init();
    }


    /**
     * 元素的对象
     */
    private PlayerOne playerOne = new PlayerOne("resource/imageForTank/myUp.jpg", 150, 470,
            this, null, "resource/imageForTank/myRight.jpg",
            "resource/imageForTank/myLeft.jpg", "resource/imageForTank/myUp.jpg",
            "resource/imageForTank/myDown.jpg");
    private PlayerTwo playerTwo = new PlayerTwo("resource/imageForTank/7.jpg", 600, 500,
            this, null, "resource/imageForTank/10.jpg",
            "resource/imageForTank/9.jpg", "resource/imageForTank/7.jpg",
            "resource/imageForTank/8.jpg");
    private Home home = new Home("resource/imageForTank/home.jpg", 500, 600, this);
    private int y = 222;//坦克指针的位置
    private int state = 0;//监控选择的模式

    public void paint(Graphics g) {

        Image image = this.createImage(WIDTH, HEIGHT);
        Graphics gImage = image.getGraphics();
        gImage.setColor(new Color(114, 113, 113, 255));
        gImage.fillRect(0, 0, this.WIDTH, this.HEIGHT);
        gImage.setFont(new Font("仿宋", Font.BOLD, 40));
        gImage.setColor(Color.BLUE);
        Image image1 = Toolkit.getDefaultToolkit().getImage(//坦克指针的图片
                MyFactory.getTempFilePath("resource/imageForTank/myRight.jpg"));

        if (state == 0) {
            gImage.drawString("请选择模式，按1是单人模式，按2是双人模式", 200, 100);
            gImage.drawString("单人模式", 300, 250);
            gImage.drawString("双人模式", 300, 400);
            gImage.drawImage(image1, 240, y, this);
        } else if (state == 1 || state == 2) {
            gImage.setFont(new Font("仿宋", Font.LAYOUT_NO_LIMIT_CONTEXT, 20));
            gImage.setColor(Color.RED);
            if (state == 1) {
                gImage.drawString("单人模式", 30, 82);
            } else {
                gImage.drawString("双人模式", 30, 82);
            }
            gImage.drawString("剩余敌人：" + botList.size(), 140, 82);

       /* gImage.drawString("玩家 1 生命：" + playerOne.getLive(), 450, 52);
        gImage.drawString("玩家 2 生命：" + playerTwo.getLive(), 740, 52);*/

            //绘制玩家子弹
            for (Bullet b : myBulletList) {
                b.paintSelf(gImage);
            }
            //绘制玩家坦克
            for (Tank t : playerList) {
                t.paintSelf(gImage);
            }
            //绘制敌方坦克
            for (Bot b : botList) {
                b.paintSelf(gImage);
            }
            //绘制敌方子弹
            for (EnemyBullet b : enemyBulletList) {
                b.paintSelf(gImage);
            }
            //绘制墙
            for (Wall w : wallList) {
                w.paintSelf(gImage);
            }
            //绘制老母
            for (Home h : homeList) {
                h.paintSelf(gImage);
            }
            //绘制爆炸元素
            for (Burst b : burstList) {
                b.paintSelf(gImage);
            }

            //清空要删除的子弹
            myBulletList.removeAll(removeBulletList);
            enemyBulletList.removeAll(removeBulletList);
        } else if (state == 5) {
            gImage.drawString("游戏胜利", 200, 200);
        } else if (state == 4) {
            gImage.drawString("游戏失败", 200, 200);
        } else if (state == 3) {
            gImage.drawString("游戏暂停", 200, 200);
        }

        g.drawImage(image, 0, 0, null);

    }



    public void loadingWall() {


        for (int i = 0; i < 12; i++) {
            Wall w = new Wall("resource/imageForTank/wall.jpg", 25 * i, 325, this);
            wallList.add(w);
        }

        for (int i = 0; i < 14; i++) {
            Wall w = new Wall("resource/imageForTank/wall.jpg", 25*(12),25*i, this);
            wallList.add(w);
        }

        for(int i = 0; i<12;i++){
            Water w = new Water("resource/imageForTank/water.jpg",25*i+450,275,this);
            wallList.add(w);
        }
        for(int i =0;i<8;i++){
            Wall w = new Wall("resource/imageForTank/wall.jpg", 25*(i+20), 175, this);
            wallList.add(w);
        }
        for(int i =0;i<8;i++){
            Wall w = new Wall("resource/imageForTank/wall.jpg", 25*(i+20), 350, this);
            wallList.add(w);
        }
        for (int i = 0; i < 14; i++) {
            Wall w = new Wall("resource/imageForTank/wall.jpg", 25*(48-12), 25*i, this);
            wallList.add(w);
        }
        for(int i = 0; i<12;i++){
            Wall w = new Wall("resource/imageForTank/wall.jpg",25*(i+36),325,this);
            wallList.add(w);
        }

        for (int i = 0 ;i<44;i++){
            HardWall h = new HardWall("resource/imageForTank/54.jpg",25*i+50,425,this);
            wallList.add(h);
        }
        for (int i = 0 ;i<4;i++){
            HardWall h = new HardWall("resource/imageForTank/54.jpg",25*(16),25*(2+i),this);
            wallList.add(h);
        }
        for (int i = 0 ;i<4;i++){
            HardWall h = new HardWall("resource/imageForTank/54.jpg",32*25,25*(2+i),this);
            wallList.add(h);
        }
        for(int i = 0; i<6;i++){
            Water w = new Water("resource/imageForTank/water.jpg",25*14,25*(6+i),this);
            wallList.add(w);
        }
        for(int i = 0; i<6;i++){
            Water w = new Water("resource/imageForTank/water.jpg",25*34,25*(6+i),this);
            wallList.add(w);
        }
        for (int i = 0; i < 48; i++) {
            Wall w = new Wall("resource/imageForTank/wall.jpg", 25 * i, 520, this);
            wallList.add(w);
        }

        for (int i = 0; i < 8; i++) {
            Wall w = new Wall("resource/imageForTank/wall.jpg", 100, 25 * i + 50, this);
            wallList.add(w);
        }
        for (int i = 0; i < 8; i++) {
            Wall w = new Wall("resource/imageForTank/wall.jpg", 700, 25 * i + 50, this);
            wallList.add(w);
        }

        for (int i = 0; i < 8; i++) {
            Wall w = new Wall("resource/imageForTank/wall.jpg", 400, 25 * i + 250, this);
            wallList.add(w);
        }

        for (int i = 0; i < 5; i++) {
            Wall w = new Wall("resource/imageForTank/wall.jpg", 200, 25 * i + 20, this);
            wallList.add(w);
        }

        for (int i = 0; i < 5; i++) {
            Wall w = new Wall("resource/imageForTank/wall.jpg", 900, 25 * i + 320, this);
            wallList.add(w);
        }
        for (int i = 0; i < 5; i++) {
            Wall w = new Wall("resource/imageForTank/wall.jpg", 100, 25 * i + 420, this);
            wallList.add(w);
        }

        Wall w1 = new Wall("resource/imageForTank/wall.jpg", 450, 550, this);
        Wall w2 = new Wall("resource/imageForTank/wall.jpg", 475, 550, this);
        Wall w3 = new Wall("resource/imageForTank/wall.jpg", 550, 550, this);
        Wall w11 = new Wall("resource/imageForTank/wall.jpg", 500, 550, this);
        Wall w12 = new Wall("resource/imageForTank/wall.jpg", 525, 550, this);
        Wall w5 = new Wall("resource/imageForTank/wall.jpg", 450, 575, this);
        Wall w6 = new Wall("resource/imageForTank/wall.jpg", 450, 600, this);
        Wall w7 = new Wall("resource/imageForTank/wall.jpg", 450, 625, this);
        Wall w8 = new Wall("resource/imageForTank/wall.jpg", 550, 575, this);
        Wall w9 = new Wall("resource/imageForTank/wall.jpg", 550, 600, this);
        Wall w10 = new Wall("resource/imageForTank/wall.jpg", 550, 625, this);

        wallList.add(w1);
        wallList.add(w2);
        wallList.add(w3);
        wallList.add(w5);
        wallList.add(w6);
        wallList.add(w7);
        wallList.add(w8);
        wallList.add(w9);
        wallList.add(w10);
        wallList.add(w11);
        wallList.add(w12);
    }

    public boolean touchWithWall(int x_ran, int y_ran) {
        for (Wall wall : wallList) {
            Rectangle rectangle = new Rectangle(x_ran, y_ran, 25, 25);
            if (rectangle.intersects(wall.getRec())) {
                return false;
            }
        }
        return true;
    }



    private int a = 1;
    private class KeyAction extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_1:
                    a = 1;
                    y = 222;
                    break;

                case KeyEvent.VK_2:
                    a = 2;
                    y = 222 + 150;
                    break;

                case KeyEvent.VK_ENTER:
                    state = a;
                    if (state == 2) {
                        playerList.add(playerTwo);
                    }
                    break;

                case KeyEvent.VK_P:
                    if (state != 3) {
                        a = state;
                        state = 3;
                    } else {
                        state = a;
                        if (a == 0) {
                            a = 1;
                        }
                    }


                default:
                    playerOne.keyPressed(e);
                    playerTwo.keyPressed(e);

            }
        }

        public void keyReleased(KeyEvent e) {

            playerOne.keyRelease(e);
            playerTwo.keyRelease(e);

        }
    }




    private GameFrame4 th = this;
    public class MyThread_2  {//如果是单独的程序，则可以把主线程设置为死循环线程

        //可是如果只是一个大程序的一部分，则不能把主线程设置为死循环，若这样做，则直接无法显示和点×关闭；
        public void run() {
            while (true) {

                if (botList.size() == 0 & (enemyCount == 30)) {
                    state = 5;//胜利
                }
                if (playerList.size() == 0 || homeList.size() == 0) {
                    state = 4;//失败
                }

                //依次装载敌方坦克
                if (timeCount % 80 == 0 && enemyCount <= 30 && (state == 1 || state == 2)) {//一开始的时候state=0；不装载敌人坦克
                    int x_ran = 0;
                    int y_ran = 0;
                    x_ran = new Random().nextInt(1200);
                    y_ran = new Random().nextInt(400);
                    if (touchWithWall(x_ran, y_ran)) {
                        Bot bot = new Bot("resource/imageForTank/enemyDown.jpG", x_ran, y_ran,
                                th, Direction.getRandomDir(), "resource/imageForTank/enemyRight.jpG",
                                "resource/imageForTank/enemyLeft.jpG", "resource/imageForTank/enemyUp.jpG",
                                "resource/imageForTank/enemyDown.jpG");
                        botList.add(bot);
                        enemyCount++;
                    }
                }

                //重新绘制函数
                repaint();
                try {
                    Thread.sleep(25);
                    timeCount++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}


