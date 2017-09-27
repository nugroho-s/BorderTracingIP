package com.example.nugroho.tugas3ipc;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ObjectDetector {
    char[][] arr;

    public ObjectDetector(char[][] arr){
        this.arr = arr;
    }

    public List<Character> getObjectChainCode(int x0, int y0, Bitmap bitmap){
        Log.d("detector","called");
        int height = arr.length;
        int width = arr[0].length;
        Log.d("sizing","w,h = "+width+","+height);
        int dir = 7;
        int x,xp,y,yp;
        x=xp=x0;
        y=yp=y0;
        List<Character> directions = new ArrayList<>();

        int nextScanDir = dir;
        do {
            nextScanDir = nextScan8(dir);
            boolean first = true;
            int ctr=0;
            do {
                if (first)
                    first=false;
                else
                    nextScanDir = nextScan8l(nextScanDir);
                x = nextScan8x(xp,nextScanDir);
                y = nextScan8y(yp,nextScanDir);
                ctr++;
            } while ((arr[y][x]!=1)&&(ctr<=8));
            dir = nextScanDir;
            directions.add((char)dir);
            bitmap.setPixel(x,y, Color.RED);
            xp=x;
            yp=y;
        } while(!((xp==x0)&&(yp==y0)));
        fillArea(x0,y0,(char)1,(char)0);
        return directions;
    }

    protected int nextScan4(int dir){
        return (dir+3)%4;
    }

    protected int nextScan4x(int x, int dir){
        if(dir==0){
            return x+1;
        } else if(dir==2){
            return x-1;
        }
        return x;
    }

    protected int nextScan4y(int y, int dir){
        if(dir==3){
            return y+1;
        } else if(dir==1){
            return y-1;
        }
        return y;
    }

    protected int nextScan4l(int dir){
        return (dir+1)%4;
    }

    protected int nextScan8(int dir){
        return (dir%2==0) ? (dir+7)%8 : (dir+6)%8;
    }

    protected int nextScan8l(int dir){
        return (dir+1)%8;
    }

    protected int nextScan8x(int x, int dir){
        if((dir==0)||(dir==1)||(dir==7)){
            return x+1;
        } else if ((dir==3)||(dir==4)||(dir==5)){
            return x-1;
        }
        return x;
    }

    protected int nextScan8y(int y, int dir){
        if((dir==5)||(dir==6)||(dir==7)){
            return y+1;
        } else if ((dir==1)||(dir==2)||(dir==3)){
            return y-1;
        }
        return y;
    }

    public void fillArea (int x, int y, char original, char fill){
//        if (x != 0)
//            x--;
//        if (y!= 0)
//            y--;
        Queue<Point> queue = new LinkedList<Point>();
        if (arr[y][x] != original){
            return;
        }
        queue.add(new Point(x, y));

        while (!queue.isEmpty()){
            Point p = queue.remove();
            if (arr[p.y][p.x] == original){
                arr[p.y][p.x] = fill;
                queue.add(new Point(p.x-1, p.y));
                queue.add(new Point(p.x+1, p.y));
                queue.add(new Point(p.x, p.y-1));
                queue.add(new Point(p.x, p.y+1));
            }
        }

        return;
    }
}
