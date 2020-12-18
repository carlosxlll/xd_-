#include <windows.h>
#include <stdio.h>

int diaoyongzi()//调用子程序 
{
	char Path[] = "lab5.2.exe";
	STARTUPINFO si = {sizeof(si)};
	PROCESS_INFORMATION pi;
	bool m1 = CreateProcess(Path,NULL,NULL,NULL,FALSE,CREATE_NEW_CONSOLE,NULL,NULL,&si,&pi); 
	
	if(m1)
		printf("成功调用子进程\n");
		
	else
		printf("Error\n");
		
	
	return 0;
} 
int main()
{
	HANDLE Pi;
	DWORD BytesRead;
	char read[1024] = {0};
 //创建管道 
	if ((Pi=CreateNamedPipe("\\\\.\\Pipe\\mypipe",PIPE_ACCESS_DUPLEX, PIPE_TYPE_BYTE|PIPE_READMODE_BYTE,1,0,0,1000,NULL))==INVALID_HANDLE_VALUE)
	{
		printf("创建失败\n");
		return 0;
	}
 
	printf("管道已创建!\n");
 	diaoyongzi();
	  
	if (ConnectNamedPipe(Pi,NULL) == 0)
	{
		printf("失败了。。。\n");
		CloseHandle(Pi);
	}
	
	else
		printf("成功！\n");
	 //读取数据 
	if(ReadFile(Pi,read,sizeof(read),&BytesRead,NULL) <= 0)
	{
		printf("没有成功读取\n");
		CloseHandle(Pi);
	}
	
	else
		printf("收到的数据为 %s \n",read);
 
	DisconnectNamedPipe(Pi);		
	CloseHandle(Pi);
	system("pause");
 	return 0;
}


