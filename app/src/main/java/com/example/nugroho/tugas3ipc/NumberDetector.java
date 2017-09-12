package com.example.nugroho.tugas3ipc;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;

import java.util.*;

public class NumberDetector {
    char[][] arr;

    public NumberDetector(char[][] arr){
        this.arr = arr;
    }

    public List<Character> detectNumber(int x0, int y0){
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
            do {
                if (first)
                    first=false;
                else
                    nextScanDir = nextScan8l(nextScanDir);
                x = nextScan8x(xp,nextScanDir);
                y = nextScan8y(yp,nextScanDir);
            } while (arr[y][x]!=1);
            dir = nextScanDir;
            directions.add((char)dir);
            xp=x;
            yp=y;
        } while(!((xp==x0)&&(yp==y0)));
        fillArea(x0,y0,(char)1,(char)0);
        return directions;
    }

    public char detectChar(List<SimplifiedDirection> simplifiedDirections){
        if(isZero(simplifiedDirections))
            return '0';
        if(isOne(simplifiedDirections))
            return '1';
        return '-';
    }

    public boolean isOne(List<SimplifiedDirection> simplifiedDirections){
        int last = simplifiedDirections.size();
        if(!((simplifiedDirections.get(last-1).direction==4)&&(simplifiedDirections.get(last-2).direction==2)
                &&(simplifiedDirections.get(last-3).direction==0)&&(simplifiedDirections.get(last-4).direction==6))){
            return false;
        }
        return true;
    }

    public boolean isZero(List<SimplifiedDirection> simplifiedDirections){
        for(int i=0;i<simplifiedDirections.size();i++){
            if(i%2==0){
                if(simplifiedDirections.get(i).direction%2==0)
                    return false;
            } else {
                if(simplifiedDirections.get(i).direction%2!=0)
                    return false;
            }
        }
        return true;
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
