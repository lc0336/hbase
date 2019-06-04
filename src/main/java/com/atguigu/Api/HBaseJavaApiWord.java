package com.atguigu.Api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;

/**
 * @program: hbase
 * @description: 课件上的api操作
 * @author: li chao
 * @create: 2019-06-04 12:54
 * @Version 1.0
 */
public class HBaseJavaApiWord {

    /*   Connection可以通过ConnectionFactory类实例化。
    Connection的生命周期由调用者管理，使用完毕后需要执行close()以释放资源。
    Connection是线程安全的，多个Table和Admin可以共用同一个Connection对象。因此一个客户端只需要实例化一个连接即可。
    反之，Table和Admin不是线程安全的！因此不建议并缓存或池化这两种对象。
    */
    public static Configuration conf;

    static {
        //使用HBaseConfiguration 的单例方法实例化
        conf = HBaseConfiguration.create();
        conf.set("HBase.zookeeper.quorum", "192.168.47.101");
        conf.set("HBase.zookeeper.property.clientPort", "2181");
    }

    /*Admin为HBase的管理类，可以通过Connection.getAdmin（）获取实例，且在使用完成后调用close（）关闭。
    Admin可用于创建，删除，列出，启用和禁用以及以其他方式修改表，以及执行其他管理操作。
    */
    //判断表是否存在
    public static boolean isTableExist(String tableName) throws MasterNotRunningException,
            ZooKeeperConnectionException, IOException {
        //在HBase中管理、访问表需要先创建HBaseAdmin对象
        //Connection connection = ConnectionFactory.createConnection(conf);
        //HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();
        HBaseAdmin admin = new HBaseAdmin(conf);
        return admin.tableExists(tableName);
    }


    public static void main(String[] args) throws IOException {
        System.out.println(isTableExist("student2"));
    }
}