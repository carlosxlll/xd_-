#include<stdio.h>
int main()
{
	int a[100]={0},b[100]={0},c[100][100]={0};
	//初始化数组 
	int n,i,j,t,judge=1;
	printf("请输入集合元素个数\n");
	scanf("%d",&n);
	printf("请输入集合元素\n"); 
	for(i=1;i<=n;i++)
	{
		scanf("%d",&a[i]);
		b[i]=a[i];
	 } 
	//输入集合 
	for(i=1;i<=n;i++)
	 {
	 	for(j=1;j<=n;j++)
		 {
		 	scanf("%d",&c[i][j]);//为手动输入 
//		 	if((a[i]%3)==(b[j]%3)) 
//		 	{
//		 		c[i][j]=1;
//			 }
//			 else
//			 {
//			 	c[i][j]=0;
//			 }
		  } 
	 }
	//定义二元关系，并建立关系矩阵
	//1.直接输入0 or 1 代表，有点可以脱离具体的题，适应更大
	//2.在程序内写入关系式，计算机判断赋值0 or 1 
	
	for(i=1,j=1;j<=n;i++,j++)
	 {
	 	if(c[i][j]!=1)
	 	{
	 		judge=0;
	 		break;
		 }
	 }
	 //判断二元关系的自反性 
   
    for(i=1;i<=n;i++)
	 {
	 	for(j=1;j<=n;j++)
		 {
		 	if(c[i][j]^c[j][i])
		 	{
		 		
		 		judge=0;
		 		break;
				 
			 }
		 	
		  } 
	 }
	 //判断二元关系的对称性 


	for(i=1;i<=n;i++)
	 {
	 	for(j=1;j<=n;j++)
		 {
		 	if(c[i][j]==1)
		 	{
		 		for(t=1;t<=n;t++)
		 		{
		 			if(c[j][t]==1)
		 			{
		 				if(c[i][t]==0)
		 				{
		 					judge=0;
		 					break;
						 }
					 }
		 			
		 			
				 }
			 }
		  } 
	 }
	 //判断二元关系的传递性 
	 
   
    if(judge==1)//由自反性、对称性、传递性 判断该二元关系是否为等价关系 
	 {
	 	printf("等价类如下\n") ;
	  for(i=1;i<=n;i++)	
	  {
	  	for(j=1;j<=n;j++)
	  	{
	  		if(c[i][j]==1)
	  		{
	  			printf("%d ",b[j]);
	  			
			  }
		  }
		  printf("\n");
	  }
	 }
	 //分割等价类 
	 else
	 {
	 	printf("该二元关系非等价关系\n");
	 }
	 return 0;
}

