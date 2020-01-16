package com;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordReducer2 extends Reducer<IntWritable, Text, IntWritable, Text> {

    static int count;

    @Override
    public void setup(Context context) throws IOException, InterruptedException {

        Configuration conf = context.getConfiguration();
        count = conf.getInt("num_records", -1); // we will use the value passed in num_records at runtime

//    count = 5;

    }

    @Override
    public void reduce(IntWritable key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        // the group stage takes care of sorting in ascending order
        // if we want to get the words with the highest frequency (to simulate descending order), we need to multiply key with -1
        int nb_words = key.get() * -1;

        for (Text val : values) {
            String word = val.toString();

            // we just write top <count> records as output
            if (count > 0) {
                context.write(new IntWritable(nb_words),
                        new Text(word));
                count--;
            }

        }

    }

}