import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReducerClass extends Reducer<IntWritable, CustomOutputTuple, IntWritable,  CustomOutputTuple> {

    CustomOutputTuple result = new CustomOutputTuple();
    List<Integer> numofcases = new ArrayList<Integer>();
    @Override
    protected void reduce(IntWritable key, Iterable<CustomOutputTuple> values, Context context) throws IOException, InterruptedException {
	    result.setMin(0);
	    result.setMax(0);
	    result.setCount(0);
        result.setMedian(0.0);
        result.setStdDev(0.0);
        long count = 0;
        int sum = 0;

        for(CustomOutputTuple customTuple : values){
            sum += customTuple.getMax();
            numofcases.add(customTuple.getMax());

            if(result.getMin() > customTuple.getMin()){
                result.setMin(customTuple.getMin());
            }
            if(result.getMax() == 0 || customTuple.getMax() > result.getMax()){
                result.setMax(customTuple.getMax());
            }

            count += customTuple.getCount();
            result.setCount(count);
        }
        System.out.println("count :"+count+", "+"sum :"+sum);
        Collections.sort(numofcases);
        int len = numofcases.size();

        if(len%2 !=0){
            result.setMedian(numofcases.get(len/2));
        } else{
            result.setMedian((numofcases.get((len-1)/2) + numofcases.get(len/2))/2.0);
        }

        double mean = sum/count;
        double stdDev = 0.0;
        for(int cases : numofcases){
            stdDev =  (cases-mean)*(cases-mean);
        }

        result.setStdDev(Math.sqrt(stdDev/(len-1)));
        System.out.println("result:"+result);
        System.out.println("key: "+key);
        context.write(key, result);
    }
}
