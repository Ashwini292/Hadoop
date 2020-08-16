
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReducerClass extends Reducer<CompositeKeyWritable, IntWritable, CompositeKeyWritable, IntWritable> {

    IntWritable result = new IntWritable(0);

    @Override
    protected void reduce(CompositeKeyWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        try {
        	for(IntWritable val : values) {
        		sum += val.get();
	        	
        	}  
        	result.set(sum);
        	context.write(key, result);
        }catch(Exception e) {
        	System.out.println("key: "+key+"value: "+values);
        }
        
    }
}
