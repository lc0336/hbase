package com.atguigu.MapReduc;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.NullWritable;

import java.io.IOException;

/**
 * @program: hbase
 * @description:
 * @author: li chao
 * @create: 2019-06-04 20:02
 * @Version 1.0
 */
public class DataTransferReducer extends TableReducer<ImmutableBytesWritable,Put,NullWritable>{
    @Override
    protected void reduce(ImmutableBytesWritable key, Iterable<Put> values, Context context) throws IOException, InterruptedException {
        for (Put put:values) {
            context.write(NullWritable.get(),put);
        }
    }
}