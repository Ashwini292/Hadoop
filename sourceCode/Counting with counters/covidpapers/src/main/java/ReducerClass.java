
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReducerClass extends Reducer<Text, IntWritable, Text, IntWritable> {

   IntWritable result = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> value, Context context) throws IOException, InterruptedException {
       
       int sum = 0;
        	for(IntWritable val : value) {
        		sum += val.get();	
        	}      
        	result.set(sum);
        	context.write(key, result);
    }
}
