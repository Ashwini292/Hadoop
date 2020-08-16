import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CustomOutputTuple implements Writable {

    private int min;
    private int max;
    private long count;
    private double median;
    private double stdDev;

    public CustomOutputTuple(){

    }

    public CustomOutputTuple(int min, int max, long count, double median, double stdDev) {
        this.min = min;
        this.max = max;
        this.count = count;
        this.median = median;
        this.stdDev = stdDev;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public double getStdDev() {
        return stdDev;
    }

    public void setStdDev(double stdDev) {
        this.stdDev = stdDev;
    }

    @Override
    public String toString() {
        return min + "\t" + max + "\t" + count + "\t" + median + "\t" + stdDev;
    }

    public void write(DataOutput out) throws IOException {
        out.writeInt(min);
        out.writeInt(max);
        out.writeLong(count);
        out.writeDouble((median));
        out.writeDouble(stdDev);
    }

    public void readFields(DataInput in) throws IOException {
        min = in.readInt();
        max = in.readInt();
        count = in.readLong();
        median = in.readDouble();
        stdDev = in.readDouble();
    }
}
