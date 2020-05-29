package calculator;

public class permutation {

    /*
     * 对数组arr进行全排列
     * 全排列的范围：begin~end
     */
    int[][] result;
    int count=0;
    public permutation(){}
    public int[][] permutation(int[] t){
        result=new int[jiecheng(t.length)][t.length];
         perm(t,0,t.length-1);
        return result;
    }
    public void perm(int[] arr,int begin,int end){
        //设置递归的出口,即当需要全排列的范围只有一个元素，则全排结束，此数组为全排列
        if(begin==end){
            for(int i=0;i<=end;i++){
                result[count][i]=arr[i];
            }
            count++;
            return;
        }else{
            //for循环将begin~end中的每一个数放到begin位置中去，并实现全排列
            for(int j=begin;j<=end;j++){
                swap(arr,begin,j);  //for循环将begin~end中的每一个数放到begin位置中去
                perm(arr,begin+1,end); //假设begin位置确定，那么对begin+1~end中的数组进行全排列
                swap(arr,begin,j); //换过去后再将数组还原
            }
        }
    }
    public void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    //输出数组
    public static void output(int a[][])
    {
        for(int i=0;i<a.length;i++)
        {
            for (int j=0;j<a[i].length;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }

    }
    public static int jiecheng(int n){
        if(n==1)
            return 1;

        return n*jiecheng(n-1);
    }


}
