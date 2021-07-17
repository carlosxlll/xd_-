package com.org.xidian;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.ArrayList;

public class MapReduceWordCountDemo {

    public static class MyMapper extends org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, Text>{

        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
                throws IOException, InterruptedException {

            String line = value.toString();
            String[] splited = line.split(",");
            String child = splited[0];
            String parent = splited[1];
            context.write(new Text(child), new Text("<"+parent));
            context.write(new Text(parent), new Text(">"+child));
        }
    }



    /*
    四个泛型解释：
    keyin：K2的类型
    valuein：v2的类型

    keyout：k3的类型
    valueout：v3的类型
     */
    public static class MyReducer extends org.apache.hadoop.mapreduce.Reducer<Text, Text, Text, Text>{
        //reduce方法将新的k2，v2转为k3，v3，将k3，v3写入上下文中

        @Override
        protected void reduce(Text k2, Iterable<Text> v2s,
                              Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {



            ArrayList<Text> grandparent = new ArrayList<Text>();
            ArrayList<Text> grandchild = new ArrayList<Text>();
            for (Text v2 : v2s) {//对各个values中的值进行处理
                String ss = v2.toString();
                String s = ss.substring(0, 1);
                if (s.equals("<")) {
                    grandchild.add(new Text(ss.substring(1)));
                } else {
                    grandparent.add(new Text(ss.substring(1)));
                }
            }
            //再将grandparent与grandchild中的东西，一一对应输出。
            for (int i = 0; i < grandchild.size(); i++) {
                for (int j = 0; j < grandparent.size(); j++) {
                    context.write(grandchild.get(i), grandparent.get(j));
                }
            }
        }
    }

    //客户端代码，写完交给ResourceManager框架去执行
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        //1：创建了一个job任务对象
        Job job = Job.getInstance(conf, MapReduceWordCountDemo.class.getSimpleName());
        //打成jar执行
        job.setJarByClass(MapReduceWordCountDemo.class);

        //2配置job任务对象（八大步骤）
        //第一步：指定文件的读取方式和读取路径
        FileInputFormat.setInputPaths(job, args[0]);
        //FileInputFormat.setInputPaths(job, new Path("http://localhost:9870/explorer.html#/input"));

        //第二步：指定map阶段的处理方式和数据类型
        job.setMapperClass(MyMapper.class);
        //设置map阶段k2的类型
        job.setMapOutputKeyClass(Text.class);
        //设置map阶段v2的类型
        job.setMapOutputValueClass(Text.class);

        //第三四五六采用默认方式

        //第7步：指定reduce阶段的处理方式和数据类型
        job.setReducerClass(MyReducer.class);
        //设置map阶段k3的类型
        job.setOutputKeyClass(Text.class);
        //设置map阶段v3的类型
        job.setOutputValueClass(Text.class);

        //第八步：设置输出类型
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //交给yarn去执行，直到执行结束才退出本程序
        job.waitForCompletion(true);
    }
}