# EECS 498 WordCount Hadoop Example

This project will give an example of a simple Hadoop project and will include instructions on how to run it on Flux

First clone the repo on Flux:
```
git clone https://gitlab.eecs.umich.edu/straitl/chained-job-demo
```

Source code can be found in the directory *src/main/java/com/solarz/eric/eecs498*

Next build the project: 
```
cd WordCount498
./gradlew clean jar
```
This will produce a jar file with all dependencies and source code in the jar. 
The jar file can be found in the directory: *build/libs/*

To run the project use the command:
```
hadoop jar build/libs/WordCount-1.0-SNAPSHOT.jar --input_path <HDFS_INPUT_FILE> --output_path <HDFS_OUTPUT_LOCATION>
```
If you would like to change the jar file's name change the version number in the file *build.gradle* and the root name
in the file *settings.gradle*

A sample input file can be found at */var/eecs498f18/words* on HDFS

Make sure that *<HDFS_INPUT_FILE>* is a file on HDFS. Most input files will be found in the */var/eecs498f18/* directory.
Make sure that *<HDFS_OUTPUT_DIRECTORY>* is a directory on HDFS that does NOT already exist. 

To verify that *<HDFS_OUTPUT_DIRECTORY>* does not exist you can use the HDFS filesystem commands: 
```
# acts like a regular fs command
hadoop fs -ls 

# remove the output directory if it already exists
hadoop fs -rm -r -f <HDFS_OUTPUT_DIRECTORY> 
```

If you want to run this locally, make sure you have hadoop properly installed. 
The rest should be the same, but instead of reading from HDFS your files will be read from your local filesystem.
