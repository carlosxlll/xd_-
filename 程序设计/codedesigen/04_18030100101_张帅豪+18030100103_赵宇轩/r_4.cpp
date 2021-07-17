#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_CHAR 2000000
unsigned char file_src[MAX_CHAR];
unsigned char file_dst[MAX_CHAR];
int Isrepeat(unsigned char* src,int left)
{
	if(left<3)////判断是否是重复数据，注意，只有重复数据大于3的时候才被判定为i重复数据 
	{
		return 0;
	}
	if(src[0] == src[1]&&src[1] == src[2])
	{
		return 1;
	}
	return 0;
	
} 
//获得重复数据的长度 
int getrepeatlength(unsigned char* src,int left)
{
	unsigned char data = src[0];
	int len = 1;
	while(len<left&&len<127&&src[len] == data)
	{
		len++;
	}
	return len;
} 
//获得不重复数据的长度 
int getnorepeatlength(unsigned char* src,int left)
{
	if(left < 3)
	return left;
	//不重复数据的长度至少为2 
	int len = 2;
	//first和second代表前后两个字符，不断向后移动 
	unsigned char first = src[0];
	unsigned char second = src[1];
	while(len<left&&len<127)
	{
		if(!(first==second&&src[len]==second))
		{
		    first = second;
		    second = src[len];
		    len++;
		}
		else
		{
			break;
		}
	}
	return len;
	 
	
} 
//RLE算法，编码，输入参数为源数组，源数组长度，目标数组，目标数组长度 
int  RLEencode(unsigned char* src,int src_len,unsigned char* dst,int dst_len)
{
	unsigned char *data =  src;
	int left = src_len;
	int point = 0;//输出缓冲区指针 
	while(left > 0)
	{
		int count = 0;
		if(Isrepeat(data,left))
		{
			count =getrepeatlength(data,left);
			dst[point++] = count|0x80;//写重复数据的数据量 
			dst[point++] = *data;//写字符 
			//指针移动 
			data+=count;
			left-=count;
		}
		else{
			count =getnorepeatlength(data,left);
			dst[point++] = count;
			for(int i=0;i<count;i++)
			{
				dst[point++] = *data;
				data++;
			}
			left-=count;
		} 
	} 
	return point;
} 

//RLE 解码算法，正好是编码算法的反过程
int RLEdecode(unsigned char* src,int src_len,unsigned char* dst,int dst_len)
{
	unsigned char *data =  src;
	int left = src_len;
	int point = 0;//输出缓冲区指针 
	while(data < src + src_len)
	{
		unsigned char zifu = *data++;
		int count = zifu & 0x7f;
		//请注意，一定要注意，这里是巨大bug易发生地，因为==的优先级其实比&要高，一定要加括号，一定要注意 
		if((zifu&0x80)==0x80)//是重复字符 
		{
		    for(int i=0;i<count;i++)
			{
				dst[point++] = *data;
				
			}
			data++; 
		}else
		{
			 for(int i=0;i<count;i++)
			{
				dst[point++] = *data++;
				
			}
		}
	}
	return point;
}

int main(int argc, char** argv) {
    FILE* f1;
    FILE* f2;
    f1 = fopen(argv[1], "rb");
    if(f1 == NULL){
    	printf("找不到这文件!\n");
    	return 0;
	}
    int t = 0;
    int a = 0;
    //读取文件 
    while ((a = fgetc(f1)) != EOF) {
        file_src[t++] = a;
    }
    f2 = fopen(argv[3], "wb");
    int size = t; 
    printf("输入字符数%d",size);
    //输入-d为解码，-e为编码 
    if( strcmp(argv[2], "-d") == 0){
    	size = RLEdecode(file_src, size, file_dst, MAX_CHAR);
    	printf("输出字符数%d",size);
	}else if(strcmp(argv[2], "-c") == 0){
    	size = RLEencode(file_src, size, file_dst, MAX_CHAR);
    	printf("输出字符数%d",size);
	}
	fwrite(file_dst, size, sizeof(unsigned char), f2);
	fclose(f1);
	fclose(f2);
    return 0;
}



