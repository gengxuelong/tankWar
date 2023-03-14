package tankWar.imageFactory;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import utils.MyPoint;
import utils.implemetclass.MySimpleMap;
import utils.inteface.MyMap;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: GengXuelong
 * @Date: 2022/8/17
 * @ClassName: ImageFactory
 * @Description:
 */
public class MyFactory {
    private static final String path = "resource/imageForTank/";
    private static final MyMap<String,String> mapper = new MySimpleMap<>();

    public static String getTempFilePath(String relativePath)  {
        if (relativePath == null || "".equals(relativePath)){
            return  null;
        }
        if(mapper.containsKey(relativePath)){
            return mapper.get(relativePath);
        }
        //获取指定路径下的资源文件
        ClassPathResource resources = new ClassPathResource(relativePath);
        //获取流
        InputStream in = null;
        try {
            in = resources.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建临时文件(空文件)  prefx:logo  suffix: .png，该文件会默认创建在你用户的更目录下，具体哪个自己打印出来看看就知道
        File imgFile = null;
        try {
            imgFile = File.createTempFile("logo"+relativePath, ".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将获取的流转为文件，在转换过后我们的资源文件就被copy到前面创建的临时文件中了
        try {
            assert imgFile != null;
            assert in != null;
            FileUtils.copyInputStreamToFile(in, imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("临时文件路径:"+imgFile.getAbsolutePath());
        imgFile.deleteOnExit();
        mapper.put(relativePath,imgFile.getAbsolutePath());
        return imgFile.getAbsolutePath();
    }

    public static final MyPoint size = new MyPoint(1200,660);


    public static Image beijing_entry = Toolkit.getDefaultToolkit().createImage(getTempFilePath(path+"beijing.jpeg"));
    public static Image beijing_guanQia = Toolkit.getDefaultToolkit().createImage(getTempFilePath(path+"beijing2.jpeg"));





}
