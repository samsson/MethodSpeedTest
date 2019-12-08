package speedtest;

import java.util.List;
/**
 * MathFunctions
 */
public class MathFunctions {

    public static int averageSum(final long value, final int count)
    {
        // Calculate average result
        return (int)value / count;

    }
    public static float getPercentage(int val1, int val2, int size)
    {
        final double q = (((double)averageSum(val1, size) - averageSum(val2, size)) / averageSum(val1, size))*100;
        return (float)q;
    }

    public static double median(List<Long> a){
        int middle = a.size()/2;
 
        if (a.size() % 2 == 1) {
            return a.get(middle);
        } else {
           return (a.get(middle-1) + a.get(middle)) / 2.0;
        }
    }

}