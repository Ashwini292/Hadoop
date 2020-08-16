import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class NaturalKeyPartitioner extends Partitioner<CompositeKeyWritable, IntWritable>{
	@Override
	public int getPartition(CompositeKeyWritable key, IntWritable value, int numPartitions) {
		int hash = key.getDate().hashCode();
		int partition = Math.abs(hash) % numPartitions;
		return partition;
	}
}
