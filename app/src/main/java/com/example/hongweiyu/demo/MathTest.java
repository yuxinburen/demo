package com.example.hongweiyu.demo;

/**
 * Created by hongweiyu on 15/11/21.
 */
public class MathTest {

    public static void main(String [] args){
//        int result = getResult(13, 30);
        sub(256,123);
    }

    public static  int getResult(int num1, int num2){
        int result = 0;

        int tem = num1;//移位后的num1
        int mod = 0;//余数
        int count = 0;//移动的位数
        int per = 1;

        while(tem != 0){

            mod = tem % 10;//余数

            for(int i = 0; i < count; i++){
                per = per * 10;
            }

            result += mod * (per) * num2;

            count++;
            tem = tem / 10;
            per = 1;
        }
        return  result;
    }

    public static void sub(int num1, int num2){

        int max = num1 < num2 ? num2 : num1;
        int min =  max == num1 ? num2 : num1;
        int sub = num1 * num2;
        int temp = min;

        int width = 1, height = 4;

        while( (temp = temp/10) != 0){
            height++;//矩阵的高
        }

        while( (sub = sub/10) != 0){
            width++;//矩阵长
        }

        int [][] array = new int[height][width];
        sub = num1 * num2;
        for (int i = width - 1; i >= 0 ; i--){

            array[0][i] = max % 10;
            max /= 10;

            array[1][i] = min % 10;
            min /= 10;

            array[height-1][i] = sub % 10;
            sub /= 10;
        }

        max = num1 < num2 ? num2 : num1;
        int mode = 1;
        int minTemp = max == num1 ? num2 : num1;
        for (int i = 2; i <= height - 2; i++){
            min =  max == num1 ? num2 : num1;
            mode = minTemp % 10;
            temp = max * mode;
            minTemp /= 10;
            for (int j = width - i +1 ; j >= 0; j--){
                array[i][j] = temp % 10;
                temp /= 10;
                min /= 10;
            }
        }

        for (int i = 0; i < height; i++){//高
            for (int j = 0; j < width; j++){//宽
                System.out.print(array[i][j]);
                if(j == width-1){
                    System.out.println();
                }
            }
        }
    }
}
