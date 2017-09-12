package com.example.nugroho.tugas3ipc;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NumberDetector {
    public List<Integer> detectNumber(int[][] arr, int x0, int y0){
        int height = arr.length;
        int width = arr[0].length;
        Log.d("sizing","w,h = "+width+","+height);
        int dir = 7;
        int x,xp,y,yp;
        x=xp=x0;
        y=yp=y0;
        List<Integer> directions = new ArrayList<>();
        Log.d("direction",""+arr[y0][x0]);

        int nextScanDir = dir;
        do {
            nextScanDir = nextScan8(dir);
            boolean first = true;
            do {
                if (first)
                    first=false;
                else
                    nextScanDir = nextScan8l(nextScanDir);
                x = nextScan8x(xp,nextScanDir);
                y = nextScan8y(yp,nextScanDir);
            } while (arr[y][x]!=1);
            dir = nextScanDir;
            directions.add(dir);
            xp=x;
            yp=y;
        } while(!((xp==x0)&&(yp==y0)));
        return directions;
    }

    private int nextScan4(int dir){
        return (dir+3)%4;
    }

    private int nextScan4x(int x, int dir){
        if(dir==0){
            return x+1;
        } else if(dir==2){
            return x-1;
        }
        return x;
    }

    private int nextScan4y(int y, int dir){
        if(dir==3){
            return y+1;
        } else if(dir==1){
            return y-1;
        }
        return y;
    }

    private int nextScan4l(int dir){
        return (dir+1)%4;
    }

    private int nextScan8(int dir){
        return (dir%2==0) ? (dir+7)%8 : (dir+6)%8;
    }

    private int nextScan8l(int dir){
        return (dir+1)%8;
    }

    private int nextScan8x(int x, int dir){
        if((dir==0)||(dir==1)||(dir==7)){
            return x+1;
        } else if ((dir==3)||(dir==4)||(dir==5)){
            return x-1;
        }
        return x;
    }

    private int nextScan8y(int y, int dir){
        if((dir==5)||(dir==6)||(dir==7)){
            return y+1;
        } else if ((dir==1)||(dir==2)||(dir==3)){
            return y-1;
        }
        return y;
    }
}
