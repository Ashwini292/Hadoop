

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.IOException;
import java.util.List;


public class MapperClass extends Mapper<LongWritable, List<Text>, CompositeKeyWritable, IntWritable> {

	//IntWritable cases = new IntWritable();

    
    @Override
    protected void map(LongWritable key, List<Text> value, Context context) throws IOException, InterruptedException{

        if(key.get() != 0 && value.size() == 18) {
        		System.out.println(String.format("Country: %s, Type: %s, Cases: %s, Date: %s", value.get(6), value.get(0), value.get(2), value.get(4)));
        		String country = String.valueOf(value.get(6));
        		//String state = String.valueOf(value.get(7));
        		String date = String.valueOf(value.get(4));
        		String type = String.valueOf(value.get(0));
        		IntWritable cases = new IntWritable(Integer.parseInt(String.valueOf(value.get(2))));
        		//String country_region = state+"_"+country;
        		if(type.equals("Confirmed")){
	        		CompositeKeyWritable compkey= new CompositeKeyWritable(country, date, type);
	        	
	        		context.write(compkey, cases);
        		}
        		
        }
    }
}

