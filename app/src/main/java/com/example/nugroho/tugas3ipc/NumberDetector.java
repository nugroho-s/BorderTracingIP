package com.example.nugroho.tugas3ipc;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

import java.util.*;

public class NumberDetector extends ObjectDetector{
    public NumberDetector(char[][] arr){
        super(arr);
        this.arr = arr;
    }

    public List<Character> detectNumber(int x0, int y0, Bitmap bitmap){
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
}
