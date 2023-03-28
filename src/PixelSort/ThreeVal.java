package PixelSort;

import java.awt.Color;

public class ThreeVal 
{
    private Color color;
    private int num, sum;
    public ThreeVal(Color color, int num)
    {
        this.color = color;
        this.num = num;
        this.sum = color.getBlue() + color.getGreen() + color.getRed();
    }

    public double getHue()
    {
        double hue = (double)(Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[0]);
        return hue;
    }
    public double getSaturation()
    {
        double saturation = (double)(Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[1]);
        return saturation;
    }
    public double getBrightness()
    {
        double brightness = (double)(Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[2]);
        return brightness;
    }

    public void setColor(Color color){this.color = color;}
    public void setNum(int num){this.num = num;}
    public Color getColor(){return color;}
    public int getNum(){return num;}
    public int getSum(){return sum;}
}
