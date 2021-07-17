from pyspark import SparkConf,SparkContext

def select(x):
    m = x.split(",")
    name = m[1]
    score =  int(m[4])
    return (name, score) 
 
def sort(x):
    if(x>=90 and x<=100):
        return ("A", 1) 
    if(x>=80 and x<=89):
        return ("B", 1) 
    if(x>=70 and x<=79):
        return ("C", 1) 
    if(x>=60 and x<=69):
        return ("D", 1) 
    if(x<60):
        return ("E", 1) 

conf = SparkConf().setMaster("local").setAppName("spark")
sc = SparkContext(conf=conf)

textData = sc.textFile("/input1/grades.txt")
lines = textData.filter(lambda line : "必修" in line).map(lambda x:select(x))
# mapValues只对集合中value做操作、key保持不变
# reduceByKey就是对元素为KV对的RDD中Key相同的元素的Value进行binary_function的reduce操作
# 使用reduceByKey()和mapValues()计算每个键对应的平均值
avgData = lines.mapValues(lambda x : (x,1)).reduceByKey(lambda x,y : (x[0]+y[0],x[1] + y[1])).mapValues(lambda x : int(x[0] / x[1]))
avgData.saveAsTextFile("/result001")
fData = avgData.map(lambda x:sort(x[1])).reduceByKey(lambda x,y : (x+y))
fData.saveAsTextFile("/result002")

