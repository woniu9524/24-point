package game;
/*
* 将24点所有可能全排列
* */
public class combination {
    public combination(){}
    public int[][] combination_do() {
        int[] allNums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};//24点的值
        int[][] results = new int[5 * 11 * 13][4];//所有组合的结果
        int[] chain = {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        results[0] = getNums(chain);
        for (int i = 1; i < results.length; i++) {
            for (int j = 0; j < chain.length - 1; j++) {
                //遇到"10组合"
                if (chain[j] == 1 && chain[j + 1] == 0) {
                    //
                    //交换成为“01”
                    int temp = chain[j];
                    chain[j] = chain[j + 1];
                    chain[j + 1] = temp;
                    //返回结果
                    results[i] = getNums(chain);
                    //把左边的1移动到最左端
                    if (i == 13)
                        System.out.println();
                    for (int k = 0; k < j; k++) {
                        if (chain[k] == 1) {
                            //重新遍历k前面，如果有0，那交换数值;
                            for (int m = 0; m < k; m++) {
                                if (chain[m] == 0) {
                                    chain[m] = 1;
                                    chain[k] = 0;
                                    break;//找了好久，才发现少了你
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
        return results;

    }

    //获取链中的4个数
    public static int[] getNums(int[] t) {
        int[] num = new int[4];
        int count = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] == 1) {
                num[count] = i + 1;
                count++;
            }
        }
        return num;
    }
}

