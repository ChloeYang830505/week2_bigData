import Dto.MapperResultDto;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author YangChunping
 * @version 1.0
 * @date 2022/3/13 11:07
 * @description
 */
public class FlowMapper extends Mapper<Object, Text, MapperResultDto, Text > {
    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
         String line = value.toString();
         String[] valueItems = line.split("\\t");
         String mobile = valueItems[0];
         MapperResultDto mapperResultDto = new MapperResultDto();
         mapperResultDto.setMobileNumber(mobile);
         mapperResultDto.setUpFlow(Long.valueOf(valueItems[1]));
         mapperResultDto.setDownFlow(Long.valueOf(valueItems[2]));
         mapperResultDto.setSumFlow(Long.valueOf(valueItems[3]));

         context.write(mapperResultDto, new Text(mobile));
    }
}
