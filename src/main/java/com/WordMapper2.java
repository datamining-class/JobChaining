package com;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class WordMapper2 extends Mapper< Text, Text, IntWritable, Text> {

  IntWritable frequency = new IntWritable();

  @Override
  public void map(Text key, Text value, Context context)
    throws IOException, InterruptedException {

    int newVal = Integer.parseInt(value.toString());
    frequency.set(newVal * -1);
    context.write(frequency, key);
  }
}