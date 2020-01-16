package com;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

// template arguments are <input key, input value, output key, output value>
public class WordMapper1 extends Mapper<LongWritable, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);

    // Input arguments must match the first two template arguments
    public void map(LongWritable key, Text text, Context context) throws IOException, InterruptedException {
        // convert text to string for easy manipulation
        String inputTextasString = text.toString();

        // splits input data on whitespace
        String[] tokenizedString = inputTextasString.split("\\s+");

        for (String token : tokenizedString) {
            // for every token, output <token>, 1
            Text tokenAsText = new Text(token);
            context.write(tokenAsText, one);
        }
    }
}