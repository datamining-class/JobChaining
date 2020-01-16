package com;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class WordCount {

    private static String outputPath;
    private static String inputPath;

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("--input_path")) {
                inputPath = args[++i];
            } else if (args[i].equals("--output_path")) {
                outputPath = args[++i];
            } else {
                throw new IllegalArgumentException("Illegal cmd line arguement");
            }
        }

        Configuration conf = new Configuration();
//        conf.set("mapred.textoutputformat.separator", " ");
        conf.set("mapreduce.job.queuename", "eecs476");         // required for this to work on GreatLakes
        conf.setInt("num_records", 5);


        Job j1 = Job.getInstance(conf, "j1");
        j1.setJarByClass(WordCount.class);
        j1.setMapperClass(WordMapper1.class);
        j1.setReducerClass(WordReducer1.class);
        j1.setMapOutputKeyClass(Text.class);
        j1.setMapOutputValueClass(IntWritable.class);
        j1.setOutputKeyClass(Text.class);
        j1.setOutputValueClass(IntWritable.class);
        j1.setInputFormatClass(TextInputFormat.class);
        j1.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.addInputPath(j1, new Path(inputPath));
        FileOutputFormat.setOutputPath(j1, new Path(outputPath));
        j1.waitForCompletion(true);

        // Start second job below here
        Job j2 = Job.getInstance(conf, "j2");
        j2.setJarByClass(WordCount.class);
        j2.setMapperClass(WordMapper2.class);
        j2.setReducerClass(WordReducer2.class);
        j2.setOutputKeyClass(IntWritable.class);
        j2.setOutputValueClass(Text.class);
        j2.setInputFormatClass(KeyValueTextInputFormat.class);
        j2.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.addInputPath(j2, new Path(outputPath)); // same input as job1 output
        FileOutputFormat.setOutputPath(j2, new Path(outputPath + "1"));
        j2.waitForCompletion(true);

    }


}
