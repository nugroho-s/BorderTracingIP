package com.example.nugroho.tugas3ipc;

import java.util.List;

public class NumberDetector extends ObjectDetector{
    public NumberDetector(char[][] arr){
        super(arr);
        this.arr = arr;
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
