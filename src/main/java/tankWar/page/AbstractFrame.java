package tankWar.page;


import tankWar.obj.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author: gengxuelong
 */
public abstract class AbstractFrame extends JFrame{
    /**
     * 元素列表
     */
    protected ArrayList<MyBullet> myBulletList = new ArrayList<>();//我方子弹列表
    protected ArrayList<EnemyBullet> enemyBulletList = new ArrayList<>();//敌方子弹列表
    protected ArrayList<Bullet> removeBulletList = new ArrayList<>();//删除子弹暂存列表
    protected ArrayList<Bot> botList = new ArrayList<>();//敌人坦克列表
    protected ArrayList<Tank> playerList = new ArrayList<>();//玩家坦克列表
    protected ArrayList<Wall> wallList = new ArrayList<>();//围墙列表
    protected ArrayList<Home> homeList = new ArrayList<>();//老母列表
    protected ArrayList<Burst> burstList = new ArrayList<>();//爆炸元素列表
    protected int delay = 40;



    public abstract void init();
    public abstract void paint(Graphics g);
    public abstract void loadingWall();
    public abstract boolean touchWithWall(int x_ran, int y_ran);

    /*
    getter and setter
     */
    public ArrayList<MyBullet> getMyBulletList() {
        return myBulletList;
    }

    public void setMyBulletList(ArrayList<MyBullet> myBulletList) {
        this.myBulletList = myBulletList;
    }

    public ArrayList<EnemyBullet> getEnemyBulletList() {
        return enemyBulletList;
    }

    public void setEnemyBulletList(ArrayList<EnemyBullet> enemyBulletList) {
        this.enemyBulletList = enemyBulletList;
    }

    public ArrayList<Bullet> getRemoveBulletList() {
        return removeBulletList;
    }

    public void setRemoveBulletList(ArrayList<Bullet> removeBulletList) {
        this.removeBulletList = removeBulletList;
    }

    public ArrayList<Bot> getBotList() {
        return botList;
    }

    public void setBotList(ArrayList<Bot> botList) {
        this.botList = botList;
    }

    public ArrayList<Tank> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Tank> playerList) {
        this.playerList = playerList;
    }

    public ArrayList<Wall> getWallList() {
        return wallList;
    }

    public void setWallList(ArrayList<Wall> wallList) {
        this.wallList = wallList;
    }

    public ArrayList<Home> getHomeList() {
        return homeList;
    }

    public void setHomeList(ArrayList<Home> homeList) {
        this.homeList = homeList;
    }

    public ArrayList<Burst> getBurstList() {
        return burstList;
    }

    public void setBurstList(ArrayList<Burst> burstList) {
        this.burstList = burstList;
    }

}
