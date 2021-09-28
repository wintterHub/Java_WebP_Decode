package test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class test {

    /**
     * 纯Java实现WebP转JPG示例
     */
    public static void main(String[] args) {
        try {
            File f = new File("testdata/test.webp"); // 待转换的webp原图片
            BufferedImage im = ImageIO.read(f); // 需要手动导入依赖包 lib/WebPImageDecoderPlugin-0_2.jar，否则会报错image == null!
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            ImageIO.write(im, "jpg", outStream); // 设置转换格式未jpg
            byte[] data = outStream.toByteArray();
            generateImage(data, "testdata/test.webp.jpg"); // 生成转换后的图片
            System.out.println("转换完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean generateImage(byte[] b, String path) {
        try {
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
