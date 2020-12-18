#include <windows.h>
#include <stdio.h>
#include <string.h>

int main()
{
	int n;
	HANDLE Pi;
	DWORD Written;
	char str[1000];
 
	if (WaitNamedPipe("\\\\.\\Pipe\\mypipe", NMPWAIT_WAIT_FOREVER) == 0)
	{
		printf("等待管道失败\n");
		return 0;
	}
	else
   	 	printf("找到管道了！\n");
	
	if ((Pi=CreateFile("\\\\.\\Pipe\\mypipe",GENERIC_READ | GENERIC_WRITE, 0 ,(LPSECURITY_ATTRIBUTES)NULL, OPEN_EXISTING, FILE_ATTRIBUTE_NORMAL, (HANDLE)NULL)) == INVALID_HANDLE_VALUE)
		printf("创建失败\n");
	else
		printf("创建成功！\n");
			
	printf("请输入数据:");	
	gets(str);
	n=strlen(str);

	if(WriteFile(Pi,str,n,&Written, NULL) == 0)
	{
		printf("写数据失败\n");
		CloseHandle(Pi);
	}
	else
	 	printf("成功写入数据！\n");
 
	CloseHandle(Pi);
	system("pause");
 
}


