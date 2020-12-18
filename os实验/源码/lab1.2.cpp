#include<stdio.h> 
#include<stdlib.h> 
int main(){ 
printf("子进程运行～\n"); 
FILE *fp; 
if(fp=fopen("date.txt","w")){ 
printf("已经创建文件!\n"); 
int i; 
for(i=48;i<58;i++) fputc(i,fp); 
fputc('\n',fp); 
fclose(fp); 
printf("已经写入数据:"); 
fp=fopen("date.txt","r"); 
char ch=fgetc(fp); 
while(ch!=EOF){ 
putchar(ch); 
ch=fgetc(fp); 
} 
fclose(fp); 
} 
else printf("创建文件失败!\n"); 
system("pause"); 
return 0; 
} 

