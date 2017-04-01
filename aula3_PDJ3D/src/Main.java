import com.sun.javafx.geom.Vec3f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.ArithmeticException;
/**
 * Created by Bruno on 20/03/2017.
 */
public class Main {

    public static int saturate(int value){
        return value = value > 255 ? 255 :
                (value < 0 ? 0 : value);

    }

    int[] histogram(BufferedImage img){
        int[] total = new int[256];
        Color cor;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
             cor = new Color(img.getRGB(x, y));
             total[cor.getRed()] = total[cor.getRed()] == 0 ? 1 : total[cor.getRed()]+1;
            }
        }
        return total;
    }

    int[] acumHistogram(int[] histogram){
        int[] acum = new int[256];
        for (int i = 0; i < histogram.length; i++) {
            acum[i] = i == 0 ? histogram[i] : acum[i-1]+histogram[i];
        }
        return acum;
    }

    //exercício
    //public BufferedImage drawHistogram(int[]	histogram){}

    //ATIVIDADE
    public BufferedImage equalize(BufferedImage img){
        BufferedImage out = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        int[] total = histogram(img);
        int[] histacum = acumHistogram(total);
        Color cor;

        int hmin = 0;
        for (int i = 255; i >= 0; i--) {
            if(i==0 || histacum[i] == 1) {
                hmin = histacum[i];
                break;
            }
        }

        int pix = histacum[255];
        int tons = 256;

//        int[] newhue = new int[256];
//        for (int i = 0; i <= 255; i++) {
//            newhue[i] = Math.round((histacum[i]-hmin)/(pix-hmin)) * (tons-1);
//        }

        //int corR;
        int newhue;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                cor = new Color(img.getRGB(x, y));
                //corR = newhue[cor.getRed()];
                //cor = new Color(corR, corR, corR);

                newhue = Math.round((histacum[cor.getRed()]-hmin)/(pix-hmin)) * (tons-1);
                cor = new Color(newhue, newhue, newhue);

                out.setRGB(x, y, cor.getRGB());
            }
        }
        return out;
    }


    public void run() throws IOException, ArithmeticException {

        //MUDAR PATH DO DISCO DO PEN DRIVE:
        String PATH = "D:\\PUCPR\\3º\\PDJ3D\\img\\img";

        /* //exercício / //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img1 = ImageIO.read(new File(PATH, "cor\\puppy.png"));

        BufferedImage linha2 = linha(img9, 100, 1, 1, 1, Color.GREEN);
        BufferedImage linha1 = linha(img9, 1, 1, 1, 100, Color.GREEN);
        ImageIO.write(linha1, "png",
                new File("linha1Img.jpg"));
        ImageIO.write(linha2, "png",
                new File("linha2Img.jpg"));

        //*/

        /* //ATIVIDADE 3 */ //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img2 = ImageIO.read(new File(PATH, "gray\\car.png"));
        BufferedImage img3 = ImageIO.read(new File(PATH, "gray\\cars.jpg"));
        BufferedImage img4 = ImageIO.read(new File(PATH, "gray\\crowd.png"));
        BufferedImage img5 = ImageIO.read(new File(PATH, "gray\\montanha.jpg"));
        BufferedImage img6 = ImageIO.read(new File(PATH, "gray\\university.png"));

        BufferedImage equa1 = equalize(img2);
        BufferedImage equa2 = equalize(img3);
        BufferedImage equa3 = equalize(img4);
        BufferedImage equa4 = equalize(img5);
        BufferedImage equa5 = equalize(img6);

        ImageIO.write(equa1, "png",
                new File("equa1Img.png"));
        ImageIO.write(equa2, "jpg",
                new File("equa2Img.jpg"));
        ImageIO.write(equa3, "png",
                new File("equa3Img.png"));
        ImageIO.write(equa4, "jpg",
                new File("equa4Img.jpg"));
        ImageIO.write(equa5, "png",
                new File("equa5Img.png"));

        //*/

        /* //DESAFIO 3 / //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img9 = ImageIO.read(new File(PATH, "cor\\puppy.png"));

        BufferedImage linha2 = linha(img9, 100, 1, 1, 1, Color.GREEN);
        BufferedImage linha1 = linha(img9, 1, 1, 1, 100, Color.GREEN);
        ImageIO.write(linha1, "png",
                new File("linha1Img.jpg"));
        ImageIO.write(linha2, "png",
                new File("linha2Img.jpg"));

        //*/

        System.out.println("pronto");
    }

    public static void main (String [] args) throws IOException, ArithmeticException {
        new Main().run();
    }
}
