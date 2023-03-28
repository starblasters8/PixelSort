package PixelSort;

import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;

public class PixelSort 
{
    public void sortByHSB(String path) throws Exception
    {
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();
        ArrayList<ThreeVal> colOcc = new ArrayList<ThreeVal>();
        String fileExtension = file.getName().substring(file.getName().lastIndexOf('.') + 1);
        String pathToExtension = path.substring(0, path.lastIndexOf('.'));

        //Get all colors
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                colOcc.add(new ThreeVal(new Color(image.getRGB(x, y)), 1));
        
        //Merge same colors
        for(int i = 0; i < colOcc.size(); i++)
        {
            for(int j = i + 1; j < colOcc.size(); j++)
            {
                if(colOcc.get(i).getColor().equals(colOcc.get(j).getColor()))
                {
                    colOcc.get(i).setNum(colOcc.get(i).getNum() + colOcc.get(j).getNum());
                    colOcc.remove(j);
                    j--;
                }
            }
        }

        //Sort pixels by hsv
        for(int i = 0; i < colOcc.size(); i++)
        {
            for(int j = i + 1; j < colOcc.size(); j++)
            {
                if(colOcc.get(i).getHue() > colOcc.get(j).getHue() || (colOcc.get(i).getHue() == colOcc.get(j).getHue() && colOcc.get(i).getSaturation() > colOcc.get(j).getSaturation()) || (colOcc.get(i).getHue() == colOcc.get(j).getHue() && colOcc.get(i).getSaturation() == colOcc.get(j).getSaturation() && colOcc.get(i).getBrightness() > colOcc.get(j).getBrightness()))
                {
                    ThreeVal temp = colOcc.get(i);
                    colOcc.set(i, colOcc.get(j));
                    colOcc.set(j, temp);
                }
            }
        }

        //Write to file
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int x = 0;
        int y = 0;
        for(int i = 0; i < colOcc.size(); i++)
        {
            for(int j = 0; j < colOcc.get(i).getNum(); j++)
            {
                out.setRGB(x, y, colOcc.get(i).getColor().getRGB());
                x++;
                if(x >= width)
                {
                    x = 0;
                    y++;
                }
            }
        }

        ImageIO.write(out, fileExtension, new File(pathToExtension + "SortedHSB." + fileExtension));
    }

    public void sortByRGB(String path) throws Exception
    {
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();
        ArrayList<ThreeVal> colOcc = new ArrayList<ThreeVal>();
        String fileExtension = file.getName().substring(file.getName().lastIndexOf('.') + 1);
        String pathToExtension = path.substring(0, path.lastIndexOf('.'));

        //Get all colors
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                colOcc.add(new ThreeVal(new Color(image.getRGB(x, y)), 1));

        //Merge same colors
        for(int i = 0; i < colOcc.size(); i++)
        {
            for(int j = i + 1; j < colOcc.size(); j++)
            {
                if(colOcc.get(i).getColor().equals(colOcc.get(j).getColor()))
                {
                    colOcc.get(i).setNum(colOcc.get(i).getNum() + colOcc.get(j).getNum());
                    colOcc.remove(j);
                    j--;
                }
            }
        }

        //Sort by sum of RGB values
        for(int i = 0; i < colOcc.size(); i++)
        {
            for(int j = i + 1; j < colOcc.size(); j++)
            {
                if(colOcc.get(i).getSum() > colOcc.get(j).getSum())
                {
                    ThreeVal temp = colOcc.get(i);
                    colOcc.set(i, colOcc.get(j));
                    colOcc.set(j, temp);
                }
            }
        }

        //Write to file
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int x = 0;
        int y = 0;
        for(int i = 0; i < colOcc.size(); i++)
        {
            for(int j = 0; j < colOcc.get(i).getNum(); j++)
            {
                out.setRGB(x, y, colOcc.get(i).getColor().getRGB());
                x++;
                if(x >= width)
                {
                    x = 0;
                    y++;
                }
            }
        }

        ImageIO.write(out, fileExtension, new File(pathToExtension + "SortedRGB." + fileExtension));
    }

    public void randomizeImage(String path) throws Exception
    {
        Random rand = new Random();
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();
        String fileExtension = file.getName().substring(file.getName().lastIndexOf('.') + 1);
        String pathToExtension = path.substring(0, path.lastIndexOf('.'));
        ArrayList<Color> pixels = new ArrayList<Color>();

        //Get all pixels
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                pixels.add(new Color(image.getRGB(x, y)));
        
        //Create new image with randomized pixels
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int index = rand.nextInt(pixels.size());
                out.setRGB(x, y, pixels.get(index).getRGB());
                pixels.remove(index);
            }
        }

        ImageIO.write(out, fileExtension, new File(pathToExtension + "Randomized." + fileExtension));
    }
    public static void main(String[] args) throws Exception
    {
        String testPath = "lib/rainbow.png";
        PixelSort ps = new PixelSort();
        ps.sortByRGB(testPath);
        ps.sortByHSB(testPath);
        ps.randomizeImage(testPath);
    }
}