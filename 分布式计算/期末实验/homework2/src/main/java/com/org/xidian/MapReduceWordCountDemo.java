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

public class MapReduceWordCountDemo {
    //自定义的mapper，继承org.apache.hadoop.mapreduce.Mapper
    /*
    四个泛型解释：
    keyin：K1的类型
    valuein：v1的类型

    keyout：k2的类型
    valueout：v2的类型
     */
    public static class MyMapper extends org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, LongWritable>{

        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context)
                throws IOException, InterruptedException {

            String line = value.toString();
//split  函数是用于按指定字符（串）或正则去分割某个字符串，结果以字符串数组形式返回，这里按照"\t"来分割text文件中字符，即一个制表符，这就是为什么我在文本中用了空格分割，导致最后的结果有很大的出入。
            String[] splited = line.split(",");
            String banji = splited[0];
            String subject = splited[2];
            String feature = subject+"\t"+banji+"\t";
            Integer score = Integer.parseInt(splited[4]);
            context.write(new Text(feature), new LongWritable(score));
        }
    }



    /*
    四个泛型解释：
    keyin：K2的类型
    valuein：v2的类型

    keyout：k3的类型
    valueout：v3的类型
     */
    public static class MyReducer extends org.apache.hadoop.mapreduce.Reducer<Text, LongWritable, Text, DoubleWritable>{
        //reduce方法将新的k2，v2转为k3，v3，将k3，v3写入上下文中

        @Override
        protected void reduce(Text k2, Iterable<LongWritable> v2s,
                              Reducer<Text, LongWritable, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {



            long count = 0L;
            long sum = 0L;
            double aver;
            for (LongWritable v2 : v2s) {
                count += v2.get();
                sum = sum + 1;
            }
            aver = count*1.0 /sum;
            DoubleWritable v3 = new DoubleWritable(aver);
            context.write(k2, v3);
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
        job.setMapOutputValueClass(LongWritable.class);

        //第三四五六采用默认方式

        //第7步：指定reduce阶段的处理方式和数据类型
        job.setReducerClass(MyReducer.class);
        //设置map阶段k3的类型
        job.setOutputKeyClass(Text.class);
        //设置map阶段v3的类型
        job.setOutputValueClass(DoubleWritable.class);

        //第八步：设置输出类型
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //交给yarn去执行，直到执行结束才退出本程序
        job.waitForCompletion(true);
    }
}