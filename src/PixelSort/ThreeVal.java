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

    public void setColor(Color color){this.color = color;}
    public void setNum(int num){this.num = num;}
    public Color getColor(){return color;}
    public int getNum(){return num;}
    public int getSum(){return sum;}
}
