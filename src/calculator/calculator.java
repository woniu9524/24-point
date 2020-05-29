package calculator;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
/*
* 将生成的符号链和所有的排列情况对应运算
*将最后符号条件的数据以键值对的形式保存<被计算数字,计算符号>
* */
public class calculator {
    int[][] chain;//符号集合
    int[] nums;//传入数字集合
    int target;//目标值
    HashMap<int[],int[]> outcome=new HashMap<int[], int[]>();//结果字典<数字数组，符号数组>
    public calculator(){}
    HashMap<int[],int[]> calculator(int[][] chain,int[] nums,int target,int x){
        this.chain=chain;
        this.nums=nums;
        this.target=target;
        int[][] arrs=per(nums);//传入数组的全排列
        for(int k=0;k<arrs.length;k++){
            if(x==0){
                for(int j=0;j<chain.length;j++){//所有的运算链

                    int sum=arrs[k][0];
                    for(int i=0;i<arrs[k].length-1;i++){
                        sum=(calculate(sum,arrs[k][i+1],chain[j][i]));
                        if(sum==-9999999)
                            break;
                    }
                    if(sum==target){
                        //outcome1.add(chain[j]);
                        //outcome2.add(arrs[k]);
                        outcome.put(arrs[k],chain[j]);
                        return outcome;
                    }


                }
            }
            for(int j=0;j<chain.length;j++){//所有的运算链

                int sum=arrs[k][0];
                for(int i=0;i<arrs[k].length-1;i++){
                    sum=(calculate(sum,arrs[k][i+1],chain[j][i]));
                    if(sum==-9999999)
                        break;
                }
                if(sum==target){
                    //outcome1.add(chain[j]);
                    //outcome2.add(arrs[k]);
                    outcome.put(arrs[k],chain[j]);
                }


            }
        }
        return outcome;
    }
    //加减乘除运算
    public int calculate(int a,int b,int sign){
        if(a==0)
            return -9999999;
        if(sign==0)
            return a+b;
        if(sign==1)
            return a-b;
        if(sign==2)
            return a*b;
        if (sign==3){
            if(a%b==0){
                return a/b;
            }else {
                return -9999999;//一刀999999，是兄弟就来砍我
            }
        }
        return -9999999;
    }
    //调用permutation类，对数组全排列，返回一个二维数组[数量][顺序数组]
    public int[][] per(int[] n){
        permutation p=new permutation();
        return p.permutation(n);
    }
    //将数组连接成字符串，并返回，例如<[5,6,7,4],[1,0,2]>连接成"(5-6+7)*4=24"
    //最后将每一组结果储存在一个字符串数组中返回
    public static String[] print(HashMap<int[],int[]> a,int target){
    String[] string;
    if(a==null){
        string=new String[1];
        string[0]="No values";
        return string;
    }

    Set<int[]> keys=a.keySet();
    string=new String[keys.size()];
    String output="";
    int count=0;
    for(int[] key:keys){
        for(int i=0;i<key.length;i++){
            output+=key[i];
            if(i!=key.length-1){
                output=getsign(output,a.get(key),i);
            }
        }
        output+="="+target;
        string[count]=output;
        output="";
        count++;
    }
    return string;
}
    //给数字加上+-*/以及()
    public static String getsign(String text ,int[] sign,int i ){
    //text是原文本,sign是符号集，i是位置
        int t=sign[i];
        if(t==2||t==3){//遇到乘除加()
            if(i!=0){//排除2*3这类情况
                if(sign[i-1]!=2&&sign[i-1]!=3){
                    text="("+text+")";
                }
            }
        }
        if(t==0)
            text+="+";
        if(t==1)
            text+="-";
        if(t==2)
            text+="*";
        if(t==3)
            text+="/";
        return text;
    }
}
