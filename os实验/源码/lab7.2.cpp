#include <iostream>
#include <windows.h>
using namespace std;
 
#define BUF_SIZE 1025
char szName[] = "NameOfMappingObject";    // 共享内存的名字
 
int main()
{
	
	HANDLE hMapFile = CreateFileMapping(INVALID_HANDLE_VALUE, NULL, PAGE_READWRITE, 0, BUF_SIZE, szName);         
 
	char *pBuf = (char *)MapViewOfFile(hMapFile, FILE_MAP_ALL_ACCESS, 0, 0, BUF_SIZE);
    
	while(1)
    {
        cout << "press any button to receive data..." << endl;
        getchar();
		cout << pBuf << endl <<endl;
		
    }
 
	UnmapViewOfFile(pBuf);// 
    CloseHandle(hMapFile);// 
	return 0;
}
