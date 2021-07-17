#include<stdio.h>
#include <stdlib.h>
#include <malloc.h>

typedef struct node
{
    int value;            //数据域
    struct node *Next;//指针域
}Node;

//    定义一个栈结构
typedef struct stack
{
    Node *Top;            //栈顶
    Node *Bottom;        //栈底
}Stack;

void InitStack(Stack *s);        //初始化栈
void Push(Stack *s ,int value);  //入栈
int pop (Stack *s);				 //出栈 
int gettop(Stack *s);			 //获得栈顶元素 
void PrintStack(Stack *s);       //遍历栈



//初始化栈 
void InitStack(Stack *s)
{
    s->Top = (Node*)malloc(sizeof(Node));    //    分配内存空间给栈顶
    s->Bottom = s->Top;                    //    使栈底也指向栈顶空间
    s->Top->Next = NULL;                    //    栈顶指针置为NULL；
    
    return ;
}

//进栈
void Push(Stack *s,int value)
{
    Node* p1 = (Node*)malloc(sizeof(Node));    //    定义一个新节点，并分配内存空间
    p1->value = value;                        //    把要进栈的数据赋给新节点的member成员
    p1->Next = s->Top;                        //    使新节点的指针指向栈顶
    s->Top = p1;                                //    把新节点作为新栈顶
}

//遍历栈的函数
void PrintStack(Stack *s)
{
    Node* p1 = s->Top;
    while(p1!= s->Bottom)                //    只要栈顶不等于栈底，循环
    {
        printf("%d ",p1->value);            //    打印栈顶的成员member
        p1 = p1->Next;                //    栈顶指针向下移动一次
    }
}
//出栈 
int Pop (Stack *s){
	int value = s->Top->value;
	s->Top = s->Top->Next;
	return value; 

}	
//获得栈顶元素		 
int GetTop(Stack *s){
	return s->Top->value;

}			 

int main(){
	
	Stack s;
	InitStack(&s);
	int isquit = 0;
	while(!isquit){
		
		int a = 10;
		int  i =0;
		int value = 0;
		int  num = 0;
		printf("\n******************************************\n请输入选项\n1.进栈\n2.出栈\n3.遍历栈\n4.获得栈顶元素\n0.退出\n******************************************\n");
		scanf("%d",&a);
		switch(a)
    	{
    		case 0: isquit = 1;
    		break;
        	case 1:printf("进栈  输入进栈数据个数\n");
			scanf("%d",&num);
			for (i = 0;i < num;i++)
    		{
        		printf("第%d个数：",i+1);
        		scanf("%d",&value);
        		Push(&s,value);               //    调用Push函数	
    		}
    		PrintStack(&s);                //    调用遍历函数
        	break;
        	case 2:printf("出栈  输入出栈数据个数\n");
			scanf("%d",&num);
			for (i = 0;i < num;i++)
    		{	 
        		printf("第%d个数：%d\n",i+1,Pop(&s));	
    		}
    		printf("现在栈元素为\n");
    		PrintStack(&s);                //    调用遍历函数
        	break;
        	case 3:printf("遍历栈 现在栈中元素\n");
        	PrintStack(&s);
        	break;
        	case 4:printf("栈顶元素为%d\n",GetTop(&s));
        	break;
        	default:printf("重新输入");
    	} 
		
	}
	
	
} 
