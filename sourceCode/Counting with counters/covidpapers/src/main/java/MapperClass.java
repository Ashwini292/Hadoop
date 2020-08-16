

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.IOException;
import java.util.List;


public class MapperClass extends Mapper<LongWritable, Text, Text, IntWritable> {

    
    Text word = new Text();
    IntWritable one = new IntWritable(1);
    
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
        String line = value.toString();
        String[] token = line.split(" ");
        
        for(String val : token){
        	if(val.matches(".*[^a-zA-Z].*") && val.charAt(0)=='"') {
        		val=val.substring(1);
        	}
        	System.out.println("word :"+val);
            word.set(val);
            context.write(word, one);
        }
        
    }
}

