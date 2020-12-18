#include<stdio.h> 
#include<stdlib.h> 
#include<windows.h> 
int main(){ 
HANDLE hEvent=OpenEvent(EVENT_ALL_ACCESS,TRUE,"event"); 
printf("Signal the event to Parent?[Y\\N]:"); 
char ch; 
scanf("%c", &ch); 
if(ch=='Y'){ 
SetEvent(hEvent); 
} 
else{ 
return 0; 
} 
Sleep(1000); 
return 0; 
} 

