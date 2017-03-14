import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Bruno on 13/03/2017.
 */
public class Main {

    public static int saturate(int value){
        return value = value > 255 ? 255 :
                (value < 0 ? 0 : value);
        
    }


    public BufferedImage negativo(BufferedImage img){
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), 
                BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int cor = img.getRGB(x, y);
                Color pixel = new Color(cor);

                int r = saturate(255 - pixel.getRed());
                int g = saturate(255 - pixel.getGreen());
                int b = saturate(255 - pixel.getBlue());

                Color newPixel = new Color(r, g, b);
                out.setRGB(x, y, newPixel.getRGB());
            }
            
        }
        return out;
    }

    //exercicio 1
    public BufferedImage convolve(BufferedImage img, float[][] kernel){
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int[][] conv = {{img.getRGB(x-1, y-1),img.getRGB(x, y-1), img.getRGB(x+1, y-1)}


                Color pixelx0y0 = new Color(cor);
                int cor = img.getRGB(x-1, y-1);
                Color pixelx0y0 = new Color(cor);

                int r = (int)saturate(pixel.getRed());
                int g = (int)saturate(pixel.getGreen());
                int b = (int)saturate(pixel.getBlue());

                Color newPixel = new Color(r, g, b);
                out.setRGB(x, y, newPixel.getRGB());
            }

        }
        return out;
    }
    
    public void run() throws IOException {
        //MUDAR PATH DO DISCO DO PEN DRIVE:
        String PATH = "D:\\PUCPR\\3º\\PDJ3D\\img\\img";

        BufferedImage img1 =ImageIO.read(new File(PATH, "cor\\metroid1.jpg"));

        BufferedImage neg = negativo(img1);
        ImageIO.write(neg, "jpg",
                new File("negImg.jpg"));


        System.out.println("pronto");
    }
    public static void main(String[] args) throws IOException {
        new Main().run();
    }
}
