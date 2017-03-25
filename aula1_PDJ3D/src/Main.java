import com.sun.javafx.geom.Vec3f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.ArithmeticException;

/**
 * Created by joao.gurgel on 06/03/2017.
 */
public class Main {

    public static int saturate(int value){
        return value = value > 255 ? 255 :
                (value < 0 ? 0 : value);
    }

    //exercício 1
    public BufferedImage bright(BufferedImage img, float intensity){
        BufferedImage out = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        int r =0;
        int g =0;
        int b =0;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color cor = new Color(img.getRGB(x, y));
                if(intensity >= 0) {
                    r = saturate((int) (cor.getRed() * intensity));
                    g = saturate((int) (cor.getGreen() * intensity));
                    b = saturate((int) (cor.getBlue() * intensity));
                }
                if(intensity <= 0) {
                    intensity = 1+ intensity;
                    if(intensity <=0) {
                        r = saturate((int) (cor.getRed() * 0.0f));
                        g = saturate((int) (cor.getGreen() * 0.0f));
                        b = saturate((int) (cor.getBlue() * 0.0f));
                    }
                    r = saturate((int) (cor.getRed() * intensity));
                    g = saturate((int) (cor.getGreen() * intensity));
                    b = saturate((int) (cor.getBlue() * intensity));
                }
                Color outColor = new Color(r, g, b);
                out.setRGB(x, y, outColor.getRGB());
            }
        }
        return out;
    }

    //exercício 2
    public BufferedImage grayscale(BufferedImage img){
        BufferedImage out = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        int r =0;
        int g =0;
        int b =0;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color cor = new Color(img.getRGB(x, y));
                /* //tipo1 /
                r = saturate((int) (cor.getRed()));
                g = saturate((int) (cor.getRed()));
                b = saturate((int) (cor.getRed()));
                //*/

                /* //tipo2 /
                int aux = ((int)cor.getRed() + (int)cor.getGreen() + (int)cor.getBlue()) / 3);
                r = saturate(aux);
                g = saturate(aux);
                b = saturate(aux);
                //*/

                /* //tipo3 */
                int aux = (int) (cor.getRed() * 0.3f) + (int) (cor.getGreen() * 0.59f) + (int) (cor.getBlue() * 0.11f);
                r = saturate(aux);
                g = saturate(aux);
                b = saturate(aux);
                //*/

                Color outColor = new Color(r, g, b);
                out.setRGB(x, y, outColor.getRGB());
            }
        }
        return out;
    }
    public BufferedImage threshold(BufferedImage img, int value){
        BufferedImage out = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        int r =0;
        int g =0;
        int b =0;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color cor = new Color(img.getRGB(x, y));
                r = saturate((int) (cor.getRed()));
                if(r >= value) {
                    r = 255;
                    g = 255;
                    b = 255;
                }
                else{
                    r = 0;
                    g = 0;
                    b = 0;
                }
                Color outColor = new Color(r, g, b);
                out.setRGB(x, y, outColor.getRGB());
            }
        }
        return out;
    }

    //exercício 3
    public BufferedImage subtract(BufferedImage img1, BufferedImage img2){
        BufferedImage out = new BufferedImage(
                img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);
        int r =0;
        int g =0;
        int b =0;
        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {
                Color cor1 = new Color(img1.getRGB(x, y));
                Color cor2 = new Color(img2.getRGB(x, y));
                r = (int) (cor2.getRed() - cor1.getRed());
                g = (int) (cor2.getGreen() - cor1.getGreen());
                b = (int) (cor2.getBlue() - cor1.getBlue());
                if(r < 0)
                    r = 0;
                if(g < 0)
                    g = 0;
                if(b < 0)
                    b = 0;
                Color outColor = new Color(r, g, b);
                out.setRGB(x, y, outColor.getRGB());
            }
        }
        return out;
    }
    public BufferedImage add(BufferedImage img1, BufferedImage img2){
        BufferedImage out = new BufferedImage(
                img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);
        int r =0;
        int g =0;
        int b =0;
        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {
                Color cor1 = new Color(img1.getRGB(x, y));
                Color cor2 = new Color(img2.getRGB(x, y));
                r = (int) (cor2.getRed() + cor1.getRed());
                g = (int) (cor2.getGreen() + cor1.getGreen());
                b = (int) (cor2.getBlue() + cor1.getBlue());
                if(r > 255)
                    r = 255;
                if(g > 255)
                    g = 255;
                if(b > 255)
                    b = 255;
                Color outColor = new Color(r, g, b);
                out.setRGB(x, y, outColor.getRGB());
            }
        }
        return out;
    }

    //exercício 4
    public BufferedImage lerp(BufferedImage img1, BufferedImage img2, float percent){
        BufferedImage out = new BufferedImage(
                img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);
        int r =0;
        int g =0;
        int b =0;
        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {
                Color cor1 = new Color(img1.getRGB(x, y));
                Color cor2 = new Color(img2.getRGB(x, y));

                r = (int) (cor1.getRed()*(1.0f-percent) + cor2.getRed()*percent);
                g = (int) (cor1.getGreen()*(1.0f-percent) + cor2.getGreen()*percent);
                b = (int) (cor1.getBlue()*(1.0f-percent) + cor2.getBlue()*percent);

                Color outColor = new Color(r, g, b);
                out.setRGB(x, y, outColor.getRGB());
            }
        }
        return out;
    }

    //exercício 5
    public static Color getRGBf (float[] aux){
        int r = (int) (aux[0] * 255.0f);
        int g = (int) (aux[1] * 255.0f);
        int b = (int) (aux[2] * 255.0f);
        Color out = new Color(r, g , b);
        return out;
    }
    public static Color getRGBAf (float[] aux){
        int r = (int) (aux[0] * 255.0f);
        int g = (int) (aux[1] * 255.0f);
        int b = (int) (aux[2] * 255.0f);
        int a = (int) (aux[3] * 255.0f);
        Color outa = new Color(r, g , b, a);
        return outa;
    }

    public static float[] setRGBf (Color cor){
        float[] rgbf = new float[3];
        rgbf[0] = (cor.getRed()/255.0f);
        rgbf[1] = (cor.getGreen()/255.0f);
        rgbf[2] = (cor.getBlue()/255.0f);
        return rgbf;
    }
    public static float[] setRGBAf (Color cor){
        float[] rgbaf = new float[4];
        rgbaf[0] = (cor.getRed()/255.0f);
        rgbaf[1] = (cor.getGreen()/255.0f);
        rgbaf[2] = (cor.getBlue()/255.0f);
        rgbaf[3] = (cor.getAlpha()/255.0f);
        return rgbaf;
    }

    public BufferedImage multiply(BufferedImage src, float[] color){
        BufferedImage out = new BufferedImage(
                src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < src.getHeight(); y++) {
            for (int x = 0; x < src.getWidth(); x++) {

                Color cor1 = new Color(src.getRGB(x, y));
                float[] rgb = setRGBf(cor1);

                rgb[0] = (rgb[0]*color[0]);
                rgb[1] = (rgb[1]*color[1]);
                rgb[2] = (rgb[2]*color[2]);

                Color outColor = getRGBf(rgb);
                out.setRGB(x, y, outColor.getRGB());
            }
        }
        return out;
    }

    //ATIVIDADE
    public BufferedImage turnEGA (BufferedImage src){
        BufferedImage out = new BufferedImage(
                src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        Paletas paleta = new Paletas();

        Vec3f vector3paleta = new Vec3f();
        Vec3f vector3rgb = new Vec3f();
        Vec3f vector3aux = new Vec3f();
        double distance = 0;

        for (int y = 0; y < src.getHeight(); y++) {
            for (int x = 0; x < src.getWidth(); x++) {

                Color cor1 = new Color(src.getRGB(x, y));
                Color outColor;
                vector3rgb.set((float) (cor1.getRed()), (float) (cor1.getGreen()), (float) (cor1.getBlue()));
                distance = 0;

                for (int i = 0; i < paleta.pallete64.length; i++) {
                    Color cor = new Color(paleta.pallete64[i]);
                    vector3paleta.set((float) (cor.getRed()), (float) (cor.getGreen()), (float) (cor.getBlue()));

                    vector3aux.sub(vector3paleta,vector3rgb);
                    if (distance == 0)
                        distance = vector3aux.length();
                    else {
                        if(distance >= vector3aux.length()) {
                            distance = vector3aux.length();
                            outColor = new Color((int)(vector3paleta.x), (int)(vector3paleta.y), (int)(vector3paleta.z));
                            out.setRGB(x, y, outColor.getRGB());
                        }
                    }
                }

            }
        }
        return out;
    }

    public BufferedImage fsDither (BufferedImage src){
        BufferedImage out = new BufferedImage(
                src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        Paletas paleta = new Paletas();

        Vec3f vector3paleta = new Vec3f();
        Vec3f vector3rgb = new Vec3f();
        Vec3f vector3aux = new Vec3f();
        Vec3f vector3Dith = new Vec3f();

        double distance;

        Color cor1;
        Color outColor;
        Color corD;
        Color cor;

        for (int y = 0; y < src.getHeight(); y++) {
            for (int x = 0; x < src.getWidth(); x++) {

                cor1 = new Color(src.getRGB(x, y));
                vector3rgb.set((float) (cor1.getRed()), (float) (cor1.getGreen()), (float) (cor1.getBlue()));
                distance = 0;

                //EGA:
                for (int i = 0; i < paleta.pallete64.length; i++) {

                    cor = new Color(paleta.pallete64[i]);
                    vector3paleta.set((float) (cor.getRed()), (float) (cor.getGreen()), (float) (cor.getBlue()));

                    vector3aux.sub(vector3paleta,vector3rgb);

                    if (distance == 0)
                        distance = vector3aux.length();
                    else {
                        if(distance >= vector3aux.length()) {
                            distance = vector3aux.length();
                            outColor = new Color((int)(vector3paleta.x), (int)(vector3paleta.y), (int)(vector3paleta.z));
                            out.setRGB(x, y, outColor.getRGB());
                        }
                    }
                }

                //dithering:
                if(x>0 && x<src.getWidth()-1 && y<src.getHeight()-1) {

                    corD = new Color(src.getRGB(x + 1, y));
                    vector3Dith.set((float) (corD.getRed()), (float) (corD.getGreen()), (float) (corD.getBlue()));
                    outColor = new Color(saturate((int) (vector3Dith.x + vector3aux.x * 7.0f / 16.0f)),
                                         saturate((int) (vector3Dith.y + vector3aux.y * 7.0f / 16.0f)),
                                         saturate((int) (vector3Dith.z + vector3aux.z * 7.0f / 16.0f)));
                    src.setRGB(x + 1, y, outColor.getRGB());


                    corD = new Color(src.getRGB(x - 1, y + 1));
                    vector3Dith.set((float) (corD.getRed()), (float) (corD.getGreen()), (float) (corD.getBlue()));
                    outColor = new Color(saturate((int) (vector3Dith.x + vector3aux.x * 3.0f / 16.0f)),
                                         saturate((int) (vector3Dith.y + vector3aux.y * 3.0f / 16.0f)),
                                         saturate((int) (vector3Dith.z + vector3aux.z * 3.0f / 16.0f)));
                    src.setRGB(x - 1, y + 1, outColor.getRGB());


                    corD = new Color(src.getRGB(x, y + 1));
                    vector3Dith.set((float) (corD.getRed()), (float) (corD.getGreen()), (float) (corD.getBlue()));
                    outColor = new Color(saturate((int) (vector3Dith.x + vector3aux.x * 5.0f / 16.0f)),
                                         saturate((int) (vector3Dith.y + vector3aux.y * 5.0f / 16.0f)),
                                         saturate((int) (vector3Dith.z + vector3aux.z * 5.0f / 16.0f)));
                    src.setRGB(x, y + 1, outColor.getRGB());


                    corD = new Color(src.getRGB(x + 1, y + 1));
                    vector3Dith.set((float) (corD.getRed()), (float) (corD.getGreen()), (float) (corD.getBlue()));
                    outColor = new Color(saturate((int) (vector3Dith.x + vector3aux.x * 1.0f / 16.0f)),
                                         saturate((int) (vector3Dith.y + vector3aux.y * 1.0f / 16.0f)),
                                         saturate((int) (vector3Dith.z + vector3aux.z * 1.0f / 16.0f)));
                    src.setRGB(x + 1, y + 1, outColor.getRGB());
                }

            }
        }
        return out;
    }

    //DESAFIO
    public static BufferedImage linha(BufferedImage img, int x1, int y1, int x2, int y2, Color color){
        BufferedImage out = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        int r = 0;
        int g = 0;
        int b = 0;
        float inclinacao = 0.0f;
        if(x1 > x2){
            int aux = x1;
            x1 = x2;
            x2 = aux;
        }
        if(y1 > y2){
            int aux = y1;
            y1 = y2;
            y2 = aux;
        }

        try {
             inclinacao = ((y2 - y1) / (x2 - x1));
        }catch(ArithmeticException e){
            System.out.println("divided by zero");
        }
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {

                Color cor = new Color(img.getRGB(x, y));
                r = saturate((int) (cor.getRed()));
                g = saturate((int) (cor.getGreen()));
                b = saturate((int) (cor.getBlue()));
                Color outColor = new Color(r, g, b);
                out.setRGB(x, y, outColor.getRGB());
                try {
                     if (y <= y2 && x <= x2) {
                        if (((y - y1) / (x - x1)) == inclinacao)
                                out.setRGB(x, y, color.getRGB());
                     }
                }catch(ArithmeticException e){
                    out.setRGB(x, y, color.getRGB());
                    System.out.println("divided by zero");
                }
                if (x == x1 && y == y1)
                    out.setRGB(x, y, color.getRGB());
                if (x == x2 && y == y2)
                    out.setRGB(x, y, color.getRGB());


            }
        }
        return out;
    }

    public void run() throws IOException, ArithmeticException{

        //MUDAR PATH DO DISCO DO PEN DRIVE:
        String PATH = "D:\\PUCPR\\3º\\PDJ3D\\img\\img";

        /* //exercício 1  / //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img = ImageIO.read(new File(PATH, "cor\\puppy.png"));
        BufferedImage darkImg = bright(img, -0.7f);
        BufferedImage brightImg = bright(img, 2.0f);
        ImageIO.write(darkImg, "png",
                new File("darkPuppy.png"));
        ImageIO.write(brightImg, "png",
                new File("brightPuppy.png"));
        //*/

        /* //exercício 2  / //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img2 = ImageIO.read(new File(PATH, "cor\\puppy.png"));
        BufferedImage grayImg = grayscale(img2);
        ImageIO.write(grayImg, "png",
                new File("grayImg.png"));

        BufferedImage holdImg = threshold(grayImg, 120);
        ImageIO.write(holdImg, "png",
                new File("holdGray.png"));
        //*/

        /* //exercício 3  / //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img3 = ImageIO.read(new File(PATH, "pb\\errosB1.png"));
        BufferedImage img4 = ImageIO.read(new File(PATH, "pb\\errosB2.png"));
        BufferedImage sub1 = subtract(img3, img4);
        BufferedImage sub2 = subtract(img4, img3);
        BufferedImage add = subtract(img3, img4);

        ImageIO.write(sub1, "png",
                new File("sub1Img.png"));
        ImageIO.write(sub2, "png",
                new File("sub2Img.png"));
        ImageIO.write(add, "png",
                new File("addImg.png"));
        //*/

        /* //exercício 4  / //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img5 = ImageIO.read(new File(PATH, "cor\\mario.jpg"));
        BufferedImage img6 = ImageIO.read(new File(PATH, "cor\\sonic.jpg"));
        BufferedImage lp25 = lerp(img5, img6, 0.25f);
        BufferedImage lp50 = lerp(img5, img6, 0.50f);
        BufferedImage lp75 = lerp(img5, img6, 0.75f);

        ImageIO.write(lp25, "png",
                new File("lp25Img.jpg"));
        ImageIO.write(lp50, "png",
                new File("lp50Img.jpg"));
        ImageIO.write(lp75, "png",
                new File("lp75Img.jpg"));
        //*/

        /* //exercício 5  / //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img7 = ImageIO.read(new File(PATH, "cor\\metroid2.jpg"));

        float[] cores = new float[3];
        cores[0] = 0.2f;
        cores[1] = 0.5f;
        cores[2] = 1.0f;

        BufferedImage mult = multiply(img7, cores);

        ImageIO.write(mult, "png",
                new File("multImg.jpg"));

        //*/

        /* //ATIVIDADE 1  */ //<TIRE OU COLOQUE O ASTERISK
        BufferedImage img8 = ImageIO.read(new File(PATH, "cor\\puppy.png"));

        BufferedImage ega = turnEGA(img8);
        ImageIO.write(ega, "png",
                new File("egaImg.png"));

        BufferedImage fsEGAdither = fsDither(ega);
        ImageIO.write(fsEGAdither, "png",
                new File("fsEGAditherImg.jpg"));
        BufferedImage fsdither = fsDither(img8);
        ImageIO.write(fsdither, "png",
                new File("fsditherImg.jpg"));

        //*/

        /* //DESAFIO 1 / //<TIRE OU COLOQUE O ASTERISK
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
