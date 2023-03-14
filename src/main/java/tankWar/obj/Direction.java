package tankWar.obj;

import java.util.Random;

public enum Direction {
    UP,DOWN,LEFT,RIGHT;
    public static Direction getRandomDir(){
        int x = new Random().nextInt(4);
        switch(x){
            case 0:return UP;
            case 1:return DOWN;
            case 2:return RIGHT;
            case 3:return LEFT;
        }
        return null;
    }
}
