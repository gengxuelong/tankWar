package utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * @author KangZhaoXin
 * @Date 2021/12/22 1:41
 * 播放WAV音乐
 */
public class MyPlayer {
    public static void playMusic(String music){
        Clip bgm = null;
        try {
            bgm = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        AudioInputStream ais = null;
        try {
            ais = AudioSystem.getAudioInputStream(new File(music));
            //ais 的参数可以为inputStream，可以是file，也可以是URL
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        try {
            assert bgm != null;
            bgm.open(ais);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        bgm.start();
    }
}
