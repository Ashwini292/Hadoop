
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;

public class CompositeKeyWritable implements WritableComparable<CompositeKeyWritable> {


	String country;
    String numofcases;
    String type;
    String date;
    

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getNumofcases() {
		return numofcases;
	}

	public void setNumofcases(String numofcases) {
		this.numofcases = numofcases;
	}
	
	public String getType() {
		return type;
	}
	
	public void settype(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
    public CompositeKeyWritable(){
    }

    public CompositeKeyWritable(String country, String date, String type){
        this.country = country;
        this.date = date;
        this.type = type;
    }

    
    public void readFields(DataInput in) throws IOException {
    	country  = in.readUTF();
        date = in.readUTF();
        type = in.readUTF();
        
    }

    public void write(DataOutput out) throws IOException {
    	out.writeUTF(country);
        out.writeUTF(date);  
        out.writeUTF(type);
    }

    @Override
    public String toString() {
        return country  + "," +date+","+type;
    }
    
    public CompositeKeyWritable copy() {
    	CompositeKeyWritable self_copy = new CompositeKeyWritable(this.country, this.numofcases, this.type);
    	return self_copy;
    }	

	public int compareTo(CompositeKeyWritable o) {
		int result = this.date.compareTo(o.getDate());
		return (result < 0 ? -1 : (result == 0 ? 0 : 1));
	}

}
