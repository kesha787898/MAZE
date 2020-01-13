package com.company;
import java.awt.image.BufferedImage;
public class draw {
    static void squad(BufferedImage im,int x0,int y0,int color,int rad ){
        line(im,x0-rad,y0-rad,x0-rad,y0+rad,color);
        line(im,x0+rad,y0-rad,x0+rad,y0+rad,color);
        line(im,x0-rad,y0-rad,x0+rad,y0-rad,color);
        line(im,x0-rad,y0+rad,x0+rad,y0+rad,color);
    }
    static void filled_squad(BufferedImage im,int x0,int y0,int color,int rad ){
        for (int x=x0-rad;x<x0+rad;x++){
            for (int y=y0-rad;y<y0+rad;y++){
im.setRGB(x,y,color);
            }
        }
    }
    static void squad(BufferedImage im,int x0,int y0,int color,int rad,boolean left,boolean right,boolean top,boolean bot){
        if(left){
        line(im,x0-rad,y0-rad,x0-rad,y0+rad,color);
            }
        if(right){
            line(im,x0+rad,y0-rad,x0+rad,y0+rad,color);
        }
        if(top){
            line(im,x0-rad,y0-rad,x0+rad,y0-rad,color);
        }
        if(bot){
            line(im,x0-rad,y0+rad,x0+rad,y0+rad,color);
        }
    }
    static void line(BufferedImage im,int x0,int y0,int x1,int y1,int color){
        if (x0==x1){
            if(y0>y1){
                int r=y1;
                y1=y0;
                y0=r;

            }
            for (int y=y0 ;y< y1;y++) {

                im.setRGB(x0, y, color);
            }
            return;
        }
        int deltax =Math.abs(x1 - x0);
        int deltay = Math.abs(y1 - y0);
        int y = y0;
        int diry = y1 - y0;
        float error = 0;
        float deltaerr = deltay / deltax;
        if (diry > 0) diry = 1;
        if (diry < 0) diry = -1;
        for (int i=x0 ;i< x1;i++) {
            im.setRGB(i, y, color);//}
             error=error + deltaerr;
            if (error >= 0.5){
            y=y + diry;
            error=error-1;}
        }
    }
}
