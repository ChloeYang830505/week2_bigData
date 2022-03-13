package BigData_week2;

import Dto.MapperResultDto;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author YangChunping
 * @version 1.0
 * @date 2022/3/13 11:16
 * @description
 */
public class FlowReducer extends Reducer<MapperResultDto, Text, Text, MapperResultDto> {

    @Override
    public void reduce(MapperResultDto key, Iterable<Text> value, Context context) throws IOException, InterruptedException {
        Text finalKey = value.iterator().next();
        context.write(finalKey, key);
    }
}
