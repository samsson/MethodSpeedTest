package functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
/**
 * MathFunctions
 */
public class MathFunctions {

    public static int averageSum(long value, int count)
    {
        // Calculate average result
        return (int)value / count;

    }
    public static float getPercentage(int val1, int val2, int size)
    {
        double q = (((double)val1 - val2) / val1)*100;
        return (float)q;    
    }

    public static double median(List<Integer> a){
        List<Integer> medianList = new ArrayList<Integer>(a);
        int middle = medianList.size()/2;
        Collections.sort(medianList);
        if (medianList.size() % 2 == 1) {
            return medianList.get(middle);
        } else {
           return (medianList.get(middle-1) + medianList.get(middle)) / 2.0;
        }
    }

    public static List<Integer> getSortedList(List<Integer> a){
        List<Integer> medianList = new ArrayList<Integer>(a);
        Collections.sort(medianList, Collections.reverseOrder());
        return medianList;
    }

}