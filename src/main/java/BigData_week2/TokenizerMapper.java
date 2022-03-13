package BigData_week2;

import Dto.MapperResultDto;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author YangChunping
 * @version 1.0
 * @date 2022/3/12 9:49
 * @description
 */
public class TokenizerMapper  extends Mapper<Object, Text, Text, MapperResultDto> {

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] splitedLine = line.split("\\t");

        MapperResultDto mapperResultDto = new MapperResultDto();
        String mapperKey = splitedLine[1];
        mapperResultDto.setMobileNumber(mapperKey);
        mapperResultDto.setUpFlow(Long.valueOf(splitedLine[7]));
        mapperResultDto.setDownFlow(Long.valueOf(splitedLine[8]));

        context.write(new Text(mapperKey), new MapperResultDto(mapperKey,Long.valueOf(splitedLine[7]),Long.valueOf(splitedLine[8])));
    }
}
