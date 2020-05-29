package game;
import calculator.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class calculate {

    public calculate(){}
    public int[][] calculate_do() {
        //得到所有的组合
        combination result = new combination();
        int[][] allCombination = result.combination_do();//所有的组合
        //得到所有可能
        commonSolve cs = new commonSolve();
        int[][] all_results = new int[allCombination.length][5];// 每一种组合的 内容+单个数量
        for (int i = 0; i < allCombination.length; i++) {//遍历每一个组合
            //获取每一组的所有结果
            HashMap<int[], int[]> dic = new HashMap<int[], int[]>();
            dic = cs.commonSolve(allCombination[i], 24, 1);//每一个组合的键值对<所有解,对应所有运算符>
            for (int j = 0; j < 5; j++) {
                if (j == 4) {
                    all_results[i][j] = dic.size();//最后一个=每个组合的解的数量
                } else {
                    all_results[i][j] = allCombination[i][j];
                }
            }
            //all_results[i]的样式,例如[1,2,3,4,15]前四个是组合，最后一个是数量
        }
        //结果筛选1~5，6~12，13~24
        return all_results;
    }


}
