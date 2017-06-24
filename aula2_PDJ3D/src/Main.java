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

    //aula:
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

        Color cor;

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {

                //kernel:
                int kR = 0;
                int kG = 0;
                int kB = 0;
                for (int i = 0; i < kernel.length; i++) {
                    for (int j = 0; j < kernel.length; j++) {
                        if(x>0 && x < img.getWidth() - 1 && y > 0 && y < img.getHeight() - 1) {
                            cor = new Color(img.getRGB(x-1+j, y-1+i));
                            kR += (int)(kernel[j][i]*cor.getRed());
                            kG += (int)(kernel[j][i]*cor.getGreen());
                            kB += (int)(kernel[j][i]*cor.getBlue());

                        }
                    }
                }

                Color newPixel = new Color(saturate(kR), saturate(kG), saturate(kB));
                out.setRGB(x, y, newPixel.getRGB());
            }

        }
        return out;
    }
    public BufferedImage formP_S(BufferedImage imgx, BufferedImage imgy){
        BufferedImage out = new BufferedImage(imgx.getWidth(), imgx.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Color corx;
        Color cory;

        int corR;
        int corG;
        int corB;

        for (int y = 0; y < imgx.getHeight(); y++) {
            for (int x = 0; x < imgx.getWidth(); x++) {

                corx = new Color(imgx.getRGB(x, y));
                cory = new Color(imgy.getRGB(x, y));
                corR = (int)(Math.sqrt(Math.pow(corx.getRed(),2)+Math.pow(cory.getRed(),2)));
                corG = (int)(Math.sqrt(Math.pow(corx.getGreen(),2)+Math.pow(cory.getGreen(),2)));
                corB = (int)(Math.sqrt(Math.pow(corx.getBlue(),2)+Math.pow(cory.getBlue(),2)));

                Color newPixel = new Color(saturate(corR), saturate(corG), saturate(corB));
                out.setRGB(x, y, newPixel.getRGB());
            }
        }
        return out;
    }

    public float[][] filtMed(){
        return new float[][]{
            {1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f},
            {1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f},
            {1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f}
        };
    }
    public float[][] suavNit(){
        return new float[][]{
            {1.0f/16.0f, 2.0f/16.0f, 1.0f/16.0f},
            {2.0f/16.0f, 4.0f/16.0f, 2.0f/16.0f},
            {1.0f/16.0f, 2.0f/16.0f, 1.0f/16.0f}
        };
    }
    public float[][] suavCruz(){
        return new float[][]{
            {0.0f, 1.0f/5.0f, 0.0f},
            {1.0f/5.0f, 1.0f/5.0f, 1.0f/5.0f},
            {0.0f, 1.0f/5.0f, 0.0f}
        };
    }
    public float[][] sharpening(){
        return new float[][]{
            {0.0f, -1.0f, 0.0f},
            {-1.0f, 5.0f, -1.0f},
            {0.0f, -1.0f, 0.0f}
        };
    }
    public float[][] embossUL(){
        return new float[][]{
            {-2.0f, -2.0f, 0.0f},
            {-2.0f, 6.0f, 0.0f},
            {0.0f, 0.0f, 0.0f}
        };
    }
    public float[][] embossDR(){
        return new float[][]{
            {0.0f, 0.0f, 0.0f},
            {0.0f, 6.0f, -2.0f},
            {0.0f, -2.0f, -2.0f}
        };
    }
    public float[][] emboss(){
        return new float[][]{
            {-2.0f, -1.0f, 0.0f},
            {-1.0f, 1.0f, 1.0f},
            {0.0f, 1.0f, 2.0f}
        };
    }
    public float[][] laplace(){
        return new float[][]{
            {0.0f, 1.0f, 0.0f},
            {1.0f, -4.0f, 1.0f},
            {0.0f, 1.0f, 0.0f}
        };
    }
    public float[][] laplaceBordas(){
        return new float[][]{
            {0.5f, 1.0f, 0.5f},
            {1.0f, -6.0f, 1.0f},
            {0.5f, 1.0f, 0.5f}
        };
    }
    public float[][] sobelX(){
        return new float[][]{
            {-1.0f, 0.0f, 1.0f},
            {-2.0f, 0.0f, 2.0f},
            {-1.0f, 0.0f, 1.0f}
        };
    }
    public float[][] sobelY(){
        return new float[][]{
            {1.0f, 2.0f, 1.0f},
            {0.0f, 0.0f, 0.0f},
            {-1.0f, -2.0f, -1.0f}
        };
    }
    public float[][] prewittX(){
        return new float[][]{
            {-1.0f, 0.0f, 1.0f},
            {-1.0f, 0.0f, 1.0f},
            {-1.0f, 0.0f, 1.0f}
        };
    }
    public float[][] prewittY(){
        return new float[][]{
            {1.0f, 1.0f, 1.0f},
            {0.0f, 0.0f, 0.0f},
            {-1.0f, -1.0f, -1.0f}
        };
    }

    //exercício 2
    public BufferedImage playH(BufferedImage img, float change){
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Color cor;
        float[] hsb;
        int rgb;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                cor = new Color(img.getRGB(x, y));

                hsb = Color.RGBtoHSB(cor.getRed(), cor.getGreen(), cor.getBlue(), null);
                hsb[0] *= change;
                rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);

                Color newPixel = new Color(rgb);
                out.setRGB(x, y, newPixel.getRGB());
            }

        }
        return out;
    }
    public BufferedImage playS(BufferedImage img, float change){
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Color cor;
        float[] hsb;
        int rgb;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                cor = new Color(img.getRGB(x, y));

                hsb = Color.RGBtoHSB(cor.getRed(), cor.getGreen(), cor.getBlue(), null);
                hsb[1] *= change;
                rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);

                Color newPixel = new Color(rgb);
                out.setRGB(x, y, newPixel.getRGB());
            }

        }
        return out;
    }
    public BufferedImage playV(BufferedImage img, float change){
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Color cor;
        float[] hsb;
        int rgb;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                cor = new Color(img.getRGB(x, y));

                hsb = Color.RGBtoHSB(cor.getRed(), cor.getGreen(), cor.getBlue(), null);
                hsb[2] *= change;
                rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);

                Color newPixel = new Color(rgb);
                out.setRGB(x, y, newPixel.getRGB());
            }

        }
        return out;
    }

    //exercício 3


    //ATIVIDADE 2
    public BufferedImage pixelate(BufferedImage img, int pixelsize){
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        int px, py;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                px = (x/pixelsize)*pixelsize;
                py = (y/pixelsize)*pixelsize;
                out.setRGB(x, y, img.getRGB(px,py));
            }
        }
        return out;
    }

    //DESAFIO 2
    public BufferedImage bloomSize(BufferedImage img, int sizeBlur){
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Color cor;
        float[] hsb;
        int rgb;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                cor = new Color(img.getRGB(x, y));

                hsb = Color.RGBtoHSB(cor.getRed(), cor.getGreen(), cor.getBlue(), null);
                if(hsb[2] >= 0.8f) {
                    rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                    Color newPixel = new Color(rgb);
                    out.setRGB(x, y, newPixel.getRGB());
                }
            }
        }
        for (int i = 0; i < sizeBlur; i++) {
            out = convolve(out, suavCruz());
        }
        return out;
    }
    public BufferedImage bloomFinal(BufferedImage img, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4){
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Color cor, cor1, cor2, cor3, cor4;
        int r,g,b;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                cor = new Color(img.getRGB(x, y));
                cor1 = new Color(img1.getRGB(x, y));
                cor2 = new Color(img2.getRGB(x, y));
                cor3 = new Color(img3.getRGB(x, y));
                cor4 = new Color(img4.getRGB(x, y));

                r = saturate(cor.getRed() + cor1.getRed() + cor2.getRed() + cor3.getRed() + cor4.getRed());
                g = saturate(cor.getGreen() + cor1.getGreen() + cor2.getGreen() + cor3.getGreen() + cor4.getGreen());
                b = saturate(cor.getBlue() + cor1.getBlue() + cor2.getBlue() + cor3.getBlue() + cor4.getBlue());

                Color newPixel = new Color(r, g, b);
                out.setRGB(x, y, newPixel.getRGB());
            }

        }
        return out;
    }

    public void run() throws IOException {
        //MUDAR PATH DO DISCO DO PEN DRIVE:
        String PATH = "D:\\PUCPR\\3º\\PDJ3D\\img\\img";

        /* //aula  / //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img1 =ImageIO.read(new File(PATH, "cor\\metroid1.jpg"));

        BufferedImage neg = negativo(img1);
        ImageIO.write(neg, "jpg",
                new File("negImg.jpg"));
        //*/

        /* //Exercício 1  / //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img2 = ImageIO.read(new File(PATH, "cor\\turtle.jpg"));
        BufferedImage img3 = ImageIO.read(new File(PATH, "gray\\lizard.png"));

        BufferedImage conv1 = convolve(img2, filtMed());
        BufferedImage conv2 = convolve(img3, filtMed());
        ImageIO.write(conv1, "jpg",
                new File("turtlefiltMedImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardfiltMedImg.jpg"));

        conv1 = convolve(img2, suavNit());
        conv2 = convolve(img3, suavNit());
        ImageIO.write(conv1, "jpg",
                new File("turtlesuavNitImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardsuavNitImg.jpg"));

        conv1 = convolve(img2, suavCruz());
        conv2 = convolve(img3, suavCruz());
        ImageIO.write(conv1, "jpg",
                new File("turtlesuavCruzImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardsuavCruzImg.jpg"));

        conv1 = convolve(img2, sharpening());
        conv2 = convolve(img3, sharpening());
        ImageIO.write(conv1, "jpg",
                new File("turtlesharpeningImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardsharpeningImg.jpg"));

        conv1 = convolve(img2, embossUL());
        conv2 = convolve(img3, embossUL());
        ImageIO.write(conv1, "jpg",
                new File("turtleembossULImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardembossULImg.jpg"));

        conv1 = convolve(img2, embossDR());
        conv2 = convolve(img3, embossDR());
        ImageIO.write(conv1, "jpg",
                new File("turtleembossDRImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardembossDRImg.jpg"));

        conv1 = convolve(img2, emboss());
        conv2 = convolve(img3, emboss());
        ImageIO.write(conv1, "jpg",
                new File("turtleembossImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardembossImg.jpg"));

        conv1 = convolve(img2, laplace());
        conv2 = convolve(img3, laplace());
        ImageIO.write(conv1, "jpg",
                new File("turtlelaplaceImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardlaplaceImg.jpg"));

        conv1 = convolve(img2, laplaceBordas());
        conv2 = convolve(img3, laplaceBordas());
        ImageIO.write(conv1, "jpg",
                new File("turtlelaplaceBordasImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardlaplaceBordasImg.jpg"));



        conv1 = convolve(img2, sobelX());
        conv2 = convolve(img3, sobelX());
        ImageIO.write(conv1, "jpg",
                new File("turtlesobelXImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardsobelXImg.jpg"));
        BufferedImage conv3 = convolve(img2, sobelY());
        BufferedImage conv4 = convolve(img3, sobelY());
        ImageIO.write(conv1, "jpg",
                new File("turtlesobelYImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardsobelYImg.jpg"));
        conv1 = formP_S(conv1, conv3);
        conv2 = formP_S(conv2, conv4);
        ImageIO.write(conv1, "jpg",
                new File("turtlelsobelImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardsobelImg.jpg"));

        conv1 = convolve(img2, prewittX());
        conv2 = convolve(img3, prewittX());
        ImageIO.write(conv1, "jpg",
                new File("turtleprewittXImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardprewittXImg.jpg"));
        conv3 = convolve(img2, prewittY());
        conv4 = convolve(img3, prewittY());
        ImageIO.write(conv1, "jpg",
                new File("turtleprewittYImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardprewittYImg.jpg"));
        conv1 = formP_S(conv1, conv3);
        conv2 = formP_S(conv2, conv4);
        ImageIO.write(conv1, "jpg",
                new File("turtlelprewittImg.jpg"));
        ImageIO.write(conv2, "png",
                new File("lizardprewittImg.jpg"));

        //*/

        /* //Exercício 2  / //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img4 = ImageIO.read(new File(PATH, "cor\\turtle.jpg"));
        BufferedImage img5 = ImageIO.read(new File(PATH, "cor\\turtle.jpg"));
        BufferedImage img6 = ImageIO.read(new File(PATH, "cor\\turtle.jpg"));

        BufferedImage pH = playH(img4, 0.5f);
        ImageIO.write(pH, "jpg", new File("playHImg.jpg"));
        BufferedImage pS = playS(img4, 0.5f);
        ImageIO.write(pS, "jpg", new File("playSImg.jpg"));
        BufferedImage pV = playV(img4, 0.5f);
        ImageIO.write(pV, "jpg", new File("playVImg.jpg"));

        //*/

        /* //Exercício 3  / //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img7 = ImageIO.read(new File(PATH, "cor\\turtle.jpg"));
        BufferedImage img8 = ImageIO.read(new File(PATH, "cor\\turtle.jpg"));
        BufferedImage img9 = ImageIO.read(new File(PATH, "cor\\turtle.jpg"));
        BufferedImage img10 = ImageIO.read(new File(PATH, "cor\\turtle.jpg"));


        //*/

        /* //ATIVIDADE 2  */ //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img11 = ImageIO.read(new File(PATH, "cor\\turtle.jpg"));

        BufferedImage pixel= pixelate(img11, 6);
        ImageIO.write(pixel, "jpg",
                new File("pixelImg.jpg"));
        BufferedImage pixelsharp = convolve(pixel, sharpening());
        ImageIO.write(pixelsharp, "jpg",
                new File("pixelsharpImg.jpg"));
        //*/

        /* //DESAFIO 3 */ //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img12 = ImageIO.read(new File(PATH, "cor\\metroid2.jpg"));

        BufferedImage bloom1 = bloomSize(img12,5);
        ImageIO.write(bloom1, "jpg",
                new File("bloom5.jpg"));

        BufferedImage bloom2 = bloomSize(img12,11);
        ImageIO.write(bloom2, "jpg",
                new File("bloom11.jpg"));

        BufferedImage bloom3 = bloomSize(img12,21);
        ImageIO.write(bloom3, "jpg",
                new File("bloom21.jpg"));

        BufferedImage bloom4 = bloomSize(img12,41);
        ImageIO.write(bloom4, "jpg",
                new File("bloom41.jpg"));

        bloom4 = bloomFinal(img12, bloom1, bloom2, bloom3, bloom4);
        ImageIO.write(bloom4, "jpg",
                new File("bloomFinal.jpg"));

        //*/

        System.out.println("pronto");
    }
    public static void main(String[] args) throws IOException {
        new Main().run();
    }
}
