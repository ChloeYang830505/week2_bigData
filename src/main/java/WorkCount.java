import Dto.MapperResultDto;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;
import org.apache.hadoop.fs.Path;

/**
 * @author YangChunping
 * @version 1.0
 * @date 2022/3/9 10:59
 * @description
 */
public class WorkCount {

        public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

            Configuration configuration = new Configuration();
            Job job = Job.getInstance(configuration, "wordCount");
            FileInputFormat.setInputPaths(job, "c:\\work\\test\\hadoop\\HTTP_20130313143750.dat");
            job.setMapperClass(TokenizerMapper.class);
            job.setReducerClass(SumReducer.class);
            FileOutputFormat.setOutputPath(job,
                    new Path("c:\\work\\test\\hadoop\\aa"));

            job.setJarByClass(WorkCount.class);


            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(MapperResultDto.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(MapperResultDto.class);

            Job jobSecond = Job.getInstance(configuration, "second");
            FileInputFormat.setInputPaths(jobSecond, "c:\\work\\test\\hadoop\\aa\\part-r-00000");
            jobSecond.setMapperClass(FlowMapper.class);
            jobSecond.setReducerClass(FlowReducer.class);
            FileOutputFormat.setOutputPath(jobSecond,
                    new Path("c:\\work\\test\\hadoop\\bb"));

            jobSecond.setJarByClass(Second.class);


            jobSecond.setOutputKeyClass(Text.class);
            jobSecond.setOutputValueClass(MapperResultDto.class);

            jobSecond.setMapOutputKeyClass(MapperResultDto.class);
            jobSecond.setMapOutputValueClass(Text.class);

job.waitForCompletion(true);


            System.exit(jobSecond.waitForCompletion(true) ? 0 : 1);

        }
    }

