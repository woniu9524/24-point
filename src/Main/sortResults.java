package Main;

import java.util.Comparator;
/*
* 因为最后输出结果的时候，因为带()字符串的长短不一比较难受
* 所以这样给字符串排序，从小到大，看着舒服一些
* */
public class sortResults implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.length()-o2.length();
    }

}
