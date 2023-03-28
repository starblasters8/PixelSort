package PixelSort;

import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;

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

        ImageIO.write(out, fileExtension, new File(pathToExtension + "SortedHSV." + fileExtension));
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

    public static void main(String[] args) throws Exception
    {
        PixelSort ps = new PixelSort();
        ps.sortByHSB("lib/chonk.png");
    }
}