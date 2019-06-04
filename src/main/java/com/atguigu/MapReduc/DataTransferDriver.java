package com.atguigu.MapReduc;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

/**
 * @program: hbase
 * @description:
 * @author: li chao
 * @create: 2019-06-04 20:06
 * @Version 1.0
 */
public class DataTransferDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = HBaseConfiguration.create();

        Job job = Job.getInstance(configuration);

        job.setJobName("hbasemr1");

        job.setJarByClass(DataTransferDriver.class);

        Scan scan = new Scan();

        // 设置Job运行的各个组件和参数
        TableMapReduceUtil.initTableMapperJob("fruit", scan,
                ReadFruitMapper.class, ImmutableBytesWritable.class, Put.class, job);

        TableMapReduceUtil.initTableReducerJob("fruit_mr", DataTransferReducer.class, job);

        job.waitForCompletion(true);
    }
}