# EECS 476 Job Chaining Hadoop Example : 

## Given some documents, return the **top k words by frequency**


This project will give an example of a simple Hadoop project and will include instructions on how to run it on Great Lakes

First clone the repo on Great Lakes:
```
git clone https://github.com/datamining-class/JobChaining.git
```

Source code can be found in the directory *src/main/java/com/*

Next build the project: 
```
cd WordCount
./gradlew clean jar
```
NOTE: if you are running on mac you may have to run: 
```
chmod +x ./gradlew
```

This will produce a jar file with all dependencies and source code in the jar. 
The jar file can be found in the directory: *build/libs/*

To run the project on Great Lakes use the command:
```
hadoop jar build/libs/WordCount-1.0-SNAPSHOT.jar --input_path <HDFS_INPUT_FILE> --output_path <HDFS_OUTPUT_LOCATION>
```
NOTE: In our case, the <HDFS_INPUT_FILE> is *input/example.txt* and <HDFS_OUTPUT_LOCATION> is *output*

To run the project locally: 
```
java -jar build/libs/WordCount-1.0-SNAPSHOT.jar --input_path input/example.txt --output_path output
```


If you would like to change the jar file's name change the version number in the file *build.gradle* and the root name
in the file *settings.gradle*

A sample input file can be found at */var/eecs476w20/words* on HDFS

Make sure that *<HDFS_INPUT_FILE>* is a file on HDFS  (or local filesystem if using the second option). Most input files will be found in the */var/eecs476w20/* directory.
Make sure that *<HDFS_OUTPUT_DIRECTORY>* is a directory on HDFS that does NOT already exist. 

To verify that *<HDFS_OUTPUT_DIRECTORY>* does not exist you can use the HDFS filesystem commands: 
```
# acts like a regular fs command
hadoop fs -ls 

# remove the output directory if it already exists
hadoop fs -rm -r -f <HDFS_OUTPUT_DIRECTORY> 
```

If you are running locally, you are free to use regular file system commands to manage these files.
