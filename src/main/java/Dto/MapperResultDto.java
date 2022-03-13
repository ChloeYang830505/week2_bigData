package Dto;

import lombok.Data;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author YangChunping
 * @version 1.0
 * @date 2022/3/9 11:27
 * @description mapper阶段每行的输出
 */
@Data
public class MapperResultDto implements WritableComparable<MapperResultDto> {

    public MapperResultDto() {
        super();
    }

    public MapperResultDto(String phoneNum, long upFlow, long downFlow) {
        super();
        this.mobileNumber = phoneNum;
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }


    /*
        手机号
         */
    private String mobileNumber;
    /*
    上行流量
     */
    private Long upFlow;
    /*
    下行流量
     */
    private Long downFlow;
    /*
    总流量
     */
    private Long sumFlow;

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(mobileNumber);
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        mobileNumber = in.readUTF();
        upFlow = in.readLong();
        downFlow = in.readLong();
        sumFlow = in.readLong();
    }

    @Override
    public int compareTo(MapperResultDto o) {
        return this.sumFlow>o.getSumFlow()?-1:1;

    }
}
