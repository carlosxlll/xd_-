#include<stdio.h> 
#include<windows.h> 
static int count; 
DWORD WINAPI ThreadProc(LPVOID IpParameter){ 
printf("新线程运行～\n"); 
for(count=1;count<=5;count++){ 
printf("线程count=%d\n",count); 
} 
printf("线程等待5秒钟～\n"); 
Sleep(5000); 
return 0; 
} 
int main(){ 
count=10; 
printf("进程运行～\n进程count=%d\n",count); 
HANDLE hEvent=CreateThread(NULL,0,ThreadProc,NULL,0,NULL); 
WaitForSingleObject(hEvent,INFINITE); 
CloseHandle(hEvent); 
printf("新线程结束～\n"); 
printf("进程结束～\n"); 
return 0; 
} 

