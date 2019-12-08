package functions;

import java.util.List;
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
        int middle = a.size()/2;
 
        if (a.size() % 2 == 1) {
            return a.get(middle);
        } else {
           return (a.get(middle-1) + a.get(middle)) / 2.0;
        }
    }

}