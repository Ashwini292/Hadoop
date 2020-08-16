import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.util.Calendar;
import java.util.Date;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MapperClass extends Mapper<LongWritable, Text, IntWritable, CustomOutputTuple> {

    CustomOutputTuple CustomOutputTuple = new CustomOutputTuple();
    IntWritable Month = new IntWritable();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    	String line = value.toString();
    	String tokens[] = line.split(",");
		//String date = String.valueOf(value.get(4));
		//String type = String.valueOf(value.get(0));
		try{
			if(key.get()!=0){
			int numofcases = Integer.parseInt(tokens[19]);
		
            CustomOutputTuple.setMin(numofcases);
            CustomOutputTuple.setMax(numofcases);
            CustomOutputTuple.setCount(1);
            DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
            Date date = format.parse(tokens[0]);
            //System.out.println(date);
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("PST"));
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH);
            Month.set(month);
            //System.out.println("month: "+month);
            context.write(Month, CustomOutputTuple);
			}
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
