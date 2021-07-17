#include <stdio.h>
#include <stdlib.h>
//防止编译器的自动对齐
#pragma pack(1)

/*BMP图像文件被分成4个部分：
位图文件头（Bitmap File Header）
位图信息头（Bitmap Info Header）
颜色表（Color Map）
位图数据（即图像数据，Data Bits或Data Body）
*/ 

//位图文件头  长度固定：14字节 
typedef struct { 
	unsigned short int type; // 位图文件类型，必须是0x424D，即字符串“BM”
	unsigned int size;		 // 位图文件大小，包括这14个字节
	unsigned short int reserved1;
	unsigned short int reserved2;
	unsigned int offset;	// 文件头到实际的位图数据的偏移字节数，bitmap文件前3个部分(文件头、信息头、颜色表)的长度之和
} FILEHEADER; 

// 位图信息头  长度固定：40字节 
typedef struct { 
	unsigned int size;			//本结构的长度，为40个字节 
	int width,height;			//位图的宽高（单位：像素） 

	unsigned short int planes;	// 目标设备的级别，必须是1。
	unsigned short int bits;	// 每个像素所占的位数（bit），其值必须为1（黑白图像）、4（16色图）、8（256色）、24（真彩色图），新的BMP格式支持32位色。
	unsigned int compression; 	// 位图压缩类型，有效的值为BI_RGB（未经压缩）、BI_RLE8、BI_RLE4...... 
	unsigned int imagesize;		// 实际的位图数据占用的字节数
	int xresolution,yresolution;// 指定目标设备的水平分辨率、垂直分辨率 （单位：像素/米） 
	unsigned int ncolours;		// 位图实际用到的颜色数
	unsigned int importantcolours; 	// 位图显示过程中重要的颜色数
} INFOHEADER;

void change_bmp(char begin[],char target[],double a);
int main(int argc, char** argv){
	
	double a = atoi(argv[2])/100.0;
	change_bmp(argv[1],argv[3],a);
	printf("转换文件完成  1.0--->%f",a);
	return 0;
}

void change_bmp(char begin[],char target[],double a){
	FILE *beg;
	FILE *tar;
	//将给定文件和目标文件打开
	beg = fopen(begin , "rb"); //读入文件 
	tar = fopen(target , "wb"); //写入文件 
	//判断读入文件是否为空 
	if(beg == NULL){
		printf("错误：读入文件为空\n");
		return ;
	}
	FILEHEADER filehead;
	INFOHEADER infohead;
	//读取给定文件的信息并保存
	fread(&filehead , sizeof(filehead) , 1 , beg);//位图文件头
	fread(&infohead , sizeof(infohead) , 1 , beg);//位图信息头
	//获取宽度和高度 
	int width = infohead.width;
	int height = infohead.height;
	//获取真正的数据信息 
	unsigned char *data = (unsigned char*)malloc(sizeof(unsigned char)*width*3*height);
    fseek(beg,54,SEEK_SET);
	fread(data,width*3*height,1,beg);
	
	
	//新的图片的高度和宽度 
	int new_width = (int)1.0*a*width;
	int new_height = (int)1.0*a*height;
	//更改文件的信息头和BMP信息头 
    filehead.size = new_width*new_height*3+54;
    infohead.height = new_height;
	infohead.width = new_width;
	fwrite(&filehead,sizeof(filehead),1,tar);
	fwrite(&infohead,sizeof(infohead),1,tar);
	//分配新的数据空间 
	unsigned char *new_data = (unsigned char*)malloc(sizeof(unsigned char)*new_width*3*new_height);
	long src_x,src_y;//分别代表原先的x,原先的y;
	//下面运行核心代码，思路是把后来的位置映射到原先的位置，把原来像素的数据写入后来像素的数据 
	long i,j; 
	for(i=0;i<new_height;i++)
	{
		src_y = (long)1.0*i/a;
		unsigned char *src_row_begin = data + src_y*3*width;//换算成原先行的起始处; 
		for(j=0;j<new_width;j++)
		{
			src_x = (long)1.0*j/a;
			memcpy(new_data + i*new_width*3+j*3,src_row_begin + src_x * 3,3);
		}
		
	} 
	fseek(tar,54,SEEK_SET);
	fwrite(new_data,new_width*3*new_height,1,tar);
	printf("写入文件成功！");
	free(data);
	free(new_data);
	fclose(beg);
	fclose(tar);
}
	

