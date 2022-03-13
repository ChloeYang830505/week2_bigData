import Dto.MapperResultDto;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author YangChunping
 * @version 1.0
 * @date 2022/3/12 9:50
 * @description
 */
public class SumReducer extends Reducer<Text, MapperResultDto, Text, MapperResultDto > {

    @Override
    protected void reduce(Text key, Iterable<MapperResultDto> inputValue, Context context) throws IOException, InterruptedException {
        AtomicReference<Long> upperSum = new AtomicReference<>(0L);
        AtomicReference<Long> downStreamSum = new AtomicReference<>(0L);
        long totalSum = 0L;


        inputValue.forEach(x -> {
            upperSum.updateAndGet(v -> v + x.getUpFlow());
            downStreamSum.updateAndGet(v -> v + x.getDownFlow());
        });

        totalSum = upperSum.get() + downStreamSum.get();

        MapperResultDto mapperResultDto = new MapperResultDto();
        mapperResultDto.setMobileNumber(key.toString());
        mapperResultDto.setUpFlow(upperSum.get());
        mapperResultDto.setDownFlow(downStreamSum.get());
        mapperResultDto.setSumFlow(totalSum);

        context.write(key, mapperResultDto);

    }
}

