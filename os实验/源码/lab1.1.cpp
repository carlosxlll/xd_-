#include<stdio.h> 
#include<windows.h> 
int main(){ 
STARTUPINFO si; 
PROCESS_INFORMATION pi; 
ZeroMemory(&pi,sizeof(pi)); 
ZeroMemory(&si,sizeof(si)); 
si.cb=sizeof(STARTUPINFO); 
if(CreateProcess("lab1.2.exe",NULL,NULL,NULL,FALSE,CREATE_NEW_CONSOLE,NULL
,NULL,&si,&pi)){ 
printf("子进程已创建～\n"); 
int i,sum=0; 
for(i=1;i<=100;++i){ 
sum+=i; 
printf("sum=%d\n",sum); 
} 
WaitForSingleObject(pi.hProcess,INFINITE); 
FILE *fp; 
fp=fopen("date.txt","r"); 
char ch=fgetc(fp); 
while(ch!=EOF){ 
putchar(ch); 
ch=fgetc(fp); 
} 
fclose(fp); 
} 
else 
printf("子进程创建失败～\n"); 
return 0; 
} 

