package calculator;

/*
* 生成所有对应的加减乘除方法
* 这样可以在输出结果的时候方便输出运算过程
*/

public class generateChain {
    generateChain() {
    }

    public int[][] generateChain(int[] numbers) {
        int col = numbers.length - 1;//列
        int row = (int) Math.pow(4, col);//行
        int[][] chain = new int[row][col];
        for (int i = 0; i < row; i += row / 4) {
            for (int j = 0; j < col; j++) {
                int len = row / 4;
                //4^3 4^2 4^1 4^0
                int n = col - 1 - j;
                nums(chain, i, j, len, n);
            }
        }

        return chain;
        /*//输出
        for (int i = 0; i < chain.length; i++) {
            for (int j = 0; j < chain[i].length; j++) {
                System.out.print(chain[i][j] + " ");
            }
            System.out.println();
        }*/

    }

    //生成符号
    public static void nums(int[][] chain, int r, int c, int len, int n) {
        int times = len / (int) Math.pow(4, n);
        int i;
        for (int t = 0; t < times; t++) {
            for (i = r; i < r + len / times; i++) {
                chain[i][c] = t % 4;
            }
            r = i;
        }
        nums_0(chain);
    }

    //解决第一列都是0的问题
    public static void nums_0(int[][] chain) {
        int lo = chain.length / 4;
        int j = 0;
        int t = 0;
        for (int i = 0; i < 4; i++) {
            for (j = t; j < lo + t; j++) {
                chain[j][0] = i % 4;
            }
            t = j;

        }
    }

    //输出打印链
    /*public void printChain(int[][] chain) {
        for (int i = 0; i < chain.length; i++) {
            for (int j = 0; j < chain[i].length; j++) {
                System.out.print(chain[i][j] + " ");
            }
            System.out.println();
        }
    }*/

}