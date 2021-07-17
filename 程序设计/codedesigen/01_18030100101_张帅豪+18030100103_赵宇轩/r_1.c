#include<stdio.h>
#include<string.h>

//加法函数 
void add(int *num1,int *num2,int num1_length,int num2_length);
//减法函数 
void sub(int *num1,int *num2,int num1_length,int num2_length); 


int main()
{
	//初始化数组 
	int num1[100] = {0};
	int num2[100] ={0};

	char str1[100], str2[100];
    printf("请输入第一个数字串: ");
    scanf("%s", str1);
    printf("第一个数字串为%s  长度为%d\n",str1,strlen(str1));
    printf("请输入第二个数字串: ");
    scanf("%s", str2);
    printf("第二个数字串为%s  长度为%d\n",str2,strlen(str2));

    
    int i = 0;
    int num1_j =0;
    
    //将字符串1转换为数字数组 1 
	for(i =strlen(str1)-1;i>=0;i--){
		num1[num1_j++] = str1[i] - '0';
	}
	//将字符串2转换为数字数组 2 
	int num2_j =0;
	for(i =strlen(str2)-1;i>=0;i--){
		num2[num2_j++] = str2[i] - '0';
	}
	
	
	
	//加法 
	printf("%s + %s = ",str1,str2);
	add(num1,num2,num1_j,num2_j);

	//减法
	//先判断数字串str1是否大于str2 
	int isbig = 0;//默认str1 < str2 
	if(num1_j > num2_j){//str1位数多，则str1大 
		isbig = 1;
	}else if(num1_j == num2_j){//位数相等，str1和str2第一个不相等的数字str1的大，则str1大 
		int right = 1;
		i = num1_j;
		isbig = 1;
		while(right){
			if(num1[i]<num2[i] || i < 0){
				isbig = 0;
				right = 0;
			}
			i = i-1;
		}	
	}

	if(isbig){
		printf("\n%s - %s =  ",str1,str2);
		sub(num1,num2,num1_j,num2_j);//调用减法函数 
		printf("\n%s - %s = -",str2,str1);
		sub(num1,num2,num1_j,num2_j);//调用减法函数 
	}else{
		printf("\n%s - %s =  ",str2,str1);
		sub(num2,num1,num2_j,num1_j);//调用减法函数 
		printf("\n%s - %s = -",str1,str2);
		sub(num2,num1,num2_j,num1_j);//调用减法函数 
	}

    
	 
}

//加法函数 
void add(int *num1,int *num2,int num1_length,int num2_length){
	int num[100]={0};
	int i = 0;
	//先找到大的加数 
	int length = num1_length > num2_length ? num1_length : num2_length;
	int more = 0;
	for(i = 0;i<=length;i++){
		num[i] = (num1[i] + num2[i] + more)%10;//取余数得到当前位 
		more = (num1[i] + num2[i])/10;//进位数字 
	}
	if(num[length]==0){//如果最高位没有进位，就将总长度减一 
		length = length -1;
	}
	for(i = length;i>=0;i--){	
		printf("%d",num[i]);
	}	
}

//减法函数 
void sub(int *num1,int *num2,int num1_length,int num2_length){
	int num[100]={0};
	int i = 0;

	//从低位开始减 
	for(i = 0;i<=num1_length;i++){
		if(num1[i]<num2[i]){//如果被减数的当前位小，向前借位 
			num[i] = 10 + num1[i] - num2[i];
			num[i+1] -= 1;
		}else{// 被减数的当前位不小，直接进行减法 
			num[i] = num1[i] - num2[i];
		}
	}

    int iszero = 1;
	for(i = num1_length-1;i>=0;i--){
		if(num[i] !=0){//防止最高位为0 
			iszero = 0;
		}
		if(iszero != 1){
			printf("%d",num[i]);
		}	
	}	
}



