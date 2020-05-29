package calculator;

import java.util.Calendar;
import java.util.HashMap;
/*
* 一个通用的二十四点算法
* 既可以算任意个数的数字运算后得到任意目标值
*
* */

public class commonSolve {
    public commonSolve() {}
    //t是接受运算的数组,target是目标值，x代表两种类型(求一个值和求所有值)
    public HashMap<int[],int[]> commonSolve(int[] t,int target,int x){
        //生成运算符号链
        generateChain gener=new generateChain();
        int[][]chain=gener.generateChain(t);
        //穷举结果
        calculator c=new calculator();
        HashMap<int[],int[]> results=c.calculator(chain,t,target,x);
        return results;

        }
    }








