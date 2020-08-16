import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import java.awt.*;
public class CompositeKeyComparator extends WritableComparator{

	
	public CompositeKeyComparator() {
		super(CompositeKeyWritable.class, true);
	
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable a, WritableComparable b) {		
		int result =0;
		CompositeKeyWritable ckw1 = (CompositeKeyWritable) a;
		CompositeKeyWritable ckw2 = (CompositeKeyWritable) b;
		result = ckw1.getDate().compareTo(ckw2.getDate());
		if(result == 0) {
			try {
				if(ckw1.getCountry()!=null && ckw2.getCountry()!=null) {
					result = -1 * (ckw1.getCountry().compareTo(ckw2.getCountry()));
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	    return result;
	}

}
