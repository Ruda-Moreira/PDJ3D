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
                //kernel:
                int cor = 0;
                for (int i = 0; i < kernel.length i++) {
                    for (int j = 0; j < kernel.length; j++) {
                        //decaimento:
                        if(x>0 && x < img.getWidth() - 1 && y > 0 && y < img.getHeight() - 1)
                            cor += (kernel[j][i]*img.getRGB(x-1+j, y-1+i));
                    }
                }

                Color newPixel = new Color(cor);

                /*int r = (int)saturate(pixel.getRed());
                int g = (int)saturate(pixel.getGreen());
                int b = (int)saturate(pixel.getBlue());

                Color newPixel = new Color(r, g, b);*/

                out.setRGB(x, y, newPixel.getRGB());
            }

        }
        return out;
    }

    public float[][] filtMed(){
        return float = {
            {1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f},
            {1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f},
            {1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f}
        };
    }
    public float[][] suavNit(){
        return float = {
            {1.0f/16.0f, 2.0f/16.0f, 1.0f/16.0f},
            {2.0f/16.0f, 4.0f/16.0f, 2.0f/16.0f},
            {1.0f/16.0f, 2.0f/16.0f, 1.0f/16.0f}
        };
    }
    public float[][] suavCruz(){
        return float = {
            {0.0f, 1.0f/5.0f, 0.0f},
            {1.0f/5.0f, 1.0f/5.0f, 1.0f/5.0f},
            {0.0f, 1.0f/5.0f, 0.0f}
        };
    }
    public float[][] laplace(){
        return float = {
            {0.0f, 1.0f, 0.0f},
            {1.0f, -4.0f, 1.0f},
            {0.0f, 1.0f, 0.0f}
        };
    }
    public float[][] laplaceBordas(){
        return float = {
            {0.5f, 1.0f, 0.5f},
            {1.0f, -6.0f, 1.0f},
            {0.5f, 1.0f, 0.5f}
        };
    }
    public float[][] sharpening(){
        return float = {
            {0.0f, -1.0f, 0.0f},
            {-1.0f, 5.0f, -1.0f},
            {0.0f, -1.0f, 0.0f}
        };
    }
    public float[][] em
    
    public void run() throws IOException {
        //MUDAR PATH DO DISCO DO PEN DRIVE:
        String PATH = "D:\\PUCPR\\3º\\PDJ3D\\img\\img";

        /* //aula  / //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img1 =ImageIO.read(new File(PATH, "cor\\metroid1.jpg"));

        BufferedImage neg = negativo(img1);
        ImageIO.write(neg, "jpg",
                new File("negImg.jpg"));
        //*/


        /* //Exerccício 1  */ //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img2 = ImageIO.read(new File(PATH, "cor\\turtle.jpg"));
        BufferedImage img3 = ImageIO.read(new File(PATH, "gray\\lizard.png"));

        BufferedImage conv1 = convolve(img2, );
        BufferedImage conv2 = convolve(img3, );
        ImageIO.write(conv1, "jpg",
                new File("turtleConImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardConImg.jpg"));
        //*/

        /* //Exerccício 2  / //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img9 = ImageIO.read(new File(PATH, "cor\\puppy.png"));

        BufferedImage linha2 = linha(img9, 100, 1, 1, 1, Color.GREEN);
        BufferedImage linha1 = linha(img9, 1, 1, 1, 100, Color.GREEN);
        ImageIO.write(linha1, "png",
                new File("linha1Img.jpg"));
        ImageIO.write(linha2, "png",
                new File("linha2Img.jpg"));
        //*/

        /* //Exerccício 3  / //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img9 = ImageIO.read(new File(PATH, "cor\\puppy.png"));

        BufferedImage linha2 = linha(img9, 100, 1, 1, 1, Color.GREEN);
        BufferedImage linha1 = linha(img9, 1, 1, 1, 100, Color.GREEN);
        ImageIO.write(linha1, "png",
                new File("linha1Img.jpg"));
        ImageIO.write(linha2, "png",
                new File("linha2Img.jpg"));
        //*/

        /* //ATIVIDADE 2  / //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img9 = ImageIO.read(new File(PATH, "cor\\puppy.png"));

        BufferedImage linha2 = linha(img9, 100, 1, 1, 1, Color.GREEN);
        BufferedImage linha1 = linha(img9, 1, 1, 1, 100, Color.GREEN);
        ImageIO.write(linha1, "png",
                new File("linha1Img.jpg"));
        ImageIO.write(linha2, "png",
                new File("linha2Img.jpg"));
        //*/

        /* //DESAFIO 2  / //<TIRE OU COLOQUE O ASTERISK
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
    public static void main(String[] args) throws IOException {
        new Main().run();
    }
}
