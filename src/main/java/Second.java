import Dto.MapperResultDto;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author YangChunping
 * @version 1.0
 * @date 2022/3/13 11:26
 * @description
 */
public class Second {

        public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

            Configuration configuration = new Configuration();
            Job job = Job.getInstance(configuration, "second");
            FileInputFormat.setInputPaths(job, "c:\\work\\test\\hadoop\\aa\\part-r-00000");
            job.setMapperClass(FlowMapper.class);
            job.setReducerClass(FlowReducer.class);
            FileOutputFormat.setOutputPath(job,
                    new Path("c:\\work\\test\\hadoop\\aa"));

            job.setJarByClass(Second.class);


            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(MapperResultDto.class);

            job.setMapOutputKeyClass(MapperResultDto.class);
            job.setMapOutputValueClass(Text.class);


            System.exit(job.waitForCompletion(true) ? 0 : 1);

    }


}
