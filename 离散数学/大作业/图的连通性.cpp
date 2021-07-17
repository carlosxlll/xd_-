
#include<iostream>
#define max 500
using namespace std;
int judge,n;
void Judge(int M[max][max]) //判断是否是强连通和弱联通的函数 
{
	judge = 0;
	for(int i = 0;i<n;i++)
	{
		for(int j = 0;j<n;j++)
		{
			if(M[i][j] == 0) judge = 1;
		}
	}
}
void Warshall(int M[max][max])
{
	for(int j = 0;j < n;j++)//将邻接矩阵转化为可达性矩阵 （Warshall算法 ） 
	{
		for(int i = 0;i < n;i++)
		{
			if(M[i][j]==1)
			for(int k = 0;k<n;k++)
			{
				M[i][k] = M[i][k]||M[j][k];
			}
		}
		M[j][j] = 1;
	}	
}
int main()
{
	int Matrix[max][max];//矩阵最大可容纳500个节点 
	int matrix[max][max];
	int i,j;
	cout<<"请输入节点个数"<<endl;
	cin>>n;
	cout<<"请输入邻接矩阵"<<endl;
	for(i = 0;i < n;i++) //输入邻接矩阵 
	{
		for(j = 0;j < n;j++)
		{
			cin>>Matrix[i][j];
			matrix[i][j] = Matrix[i][j];
		}
	}
	Warshall(Matrix);//得出可达矩阵 
	Judge(Matrix);//判断可达矩阵元素是否都是1 
	if(judge == 0)
	{
		cout<<"强连通"<<endl;
		return 0; 
	}
	judge = 0;
	for(i = 0;i<n;i++)//判断是否为单向连通 
	{
		for(j = 0;j<n;j++)
		{
			if(Matrix[i][j]+Matrix[j][i]<=0) judge = 1;
		}
	}
	if(judge==0)
	{
		cout<<"单向联通"<<endl;
		return 0;
	}
	for(i =0;i<n;i++) //将邻接矩阵转化
	{
		for(j = 0;j<n;j++)
		{
			if(matrix[i][j]==1) matrix[j][i]=1;//求邻接矩阵matrix||他的转置矩阵 
		}
	}
	Warshall(matrix);
	Judge(matrix);
	if(judge == 0)
	{
		cout<<"弱联通"<<endl;
		return 0; 
	}
	else{
		cout<<"不连通"<<endl;
		return 0;
	}
}


//过程；
//先根据输入的邻接矩阵，调用warshall函数转化为可达性矩阵，再调动判断（Judge）函数判断是否为强连通，若为强连通，
//则输出“强连通” 并结束程序，若不是强连通，则判断是否是单向联通，若是结束程序，若不是单向连通则进行矩阵转换为无向，
//并将转换过的矩阵运用Warshall函数转化为无向图（基图）的可达性矩阵 ，判断若基图的是连通的，则该矩阵为弱联通矩阵，
//反之则为不连通 

//问题：
//第一次编写时，忘记将基图再次进行Warshall函数求可达性矩阵，结果弱联通的情况都被判定为不连通 
