package tankWar.obj;


import tankWar.page.AbstractFrame;

import java.awt.*;
import java.util.Random;

public class Bot extends Tank {

    public Bot(String image, int x, int y, AbstractFrame gameFrame, Direction direction,
               String right, String left, String up, String down) {
        super(image, x, y, gameFrame, direction, right, left, up, down);
    }

    @Override
    public void paintSelf(Graphics g) {

        if (direction == null) {
            g.drawImage(image, x, y, gameFrame);
        } else switch (direction) {
            case UP:
                g.drawImage(up_I, x, y, gameFrame);
                break;
            case DOWN:
                g.drawImage(down_I, x, y, gameFrame);
                break;
            case RIGHT:
                g.drawImage(right_I, x, y, gameFrame);
                break;
            case LEFT:
                g.drawImage(left_I, x, y, gameFrame);
                break;
            default:
                break;
        }
        this.move();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }

    public void attack() {
        EnemyBullet bullet = new EnemyBullet("resource/imageForTank/enemyBullet.jpg",
                getPointOfMouth().x, getPointOfMouth().y, this.gameFrame, this.direction);
        this.gameFrame.getEnemyBulletList().add(bullet);
    }//敌方坦克攻击不用冷却时间，他们的攻击时间间隔靠概率搞定



    int moveTime = 0;
    Random random = new Random();
    void move(){
        int num = random.nextInt(500);
        if(num == 0||num ==234||num == 111||num==147||num==444){

            this.attack();
        }
        if(moveTime>=20){
            direction = Direction.getRandomDir();
            moveTime = 0;
        }else{
            moveTime++;
        }
        if(direction == null)return ;
        switch(direction){
            case UP: upWard();break;
            case DOWN:downWard();break;
            case LEFT:leftWard();break;
            case RIGHT:rightWard();break;
        }
    }
}
