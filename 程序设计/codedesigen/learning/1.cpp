#include <iostream>
#include <ctime>
#include <cstdlib>
using namespace std;
int  main() {
	cout << "hello \nworld!" << endl;
	cout << "hello world!" << "\n";
	cout << "hello world!" << "ewrwrrw" << "fedwafsaef" <<endl;
	cout << "hello world!" << endl;
	enum color {
		red,
		green,
		white = 5,
		black
	} c ;
	
	c = black;
	cout << c << endl;
	c = white;
	cout << c << endl;
	c = green;
	cout << c << endl;
	int i,j;
 
   // 设置种子
   srand( (unsigned)time( NULL) );
 
   /* 生成 10 个随机数 */
   for( i = 0; i < 10; i++ )
   {
      // 生成实际的随机数
      j= rand();
      cout <<"随机数： " << j << endl;
   }
   
   int balance[5] = {1000, 2, 3, 7, 50};
   int *s;
   s = &balance[1];
   cout << &balance[1]<<" "<< &balance[2] << endl;
   cout << s <<" "<< s+1 << endl;
   cout << *(s)  <<" "<< *(s+1)<< endl;
   
   int  var1;
   int var2[10];
 
   cout << "var1 变量的地址： ";
   cout << &var1 << endl;
 
   cout << "var2 变量的地址： ";
   cout << &var2 << &var2[0]<< &var2[1]<< &var2[2]<< &var2[3]<< &var2[4]<< endl;
   
    // 声明简单的变量
   int i_1;
 
   // 声明引用变量
   int&    r = i_1;
   
   i_1 = 5;
   cout << "Value of i : " << i_1 << endl;
   cout << "Value of i reference : " << r  << endl;
   r = 12;
   cout << "Value of i : " << i_1 << endl;
   cout << "Value of i reference : " << r  << endl;
  

	// 基于当前系统的当前日期/时间
   time_t now = time(0);
   cout << now <<endl; 
   // 把 now 转换为字符串形式
   char* dt = ctime(&now);
 
   cout << "本地日期和时间：" << dt << endl;
 
   // 把 now 转换为 tm 结构
   tm *gmtm = gmtime(&now);
   dt = asctime(gmtm);
   cout << "UTC 日期和时间："<< dt << endl;
   
   char name[50];
   int age;
   cout << "please input your name and age: " ;
   cin >> name >> age;
   cout << "姓名:" << name << "  年龄:" << age << endl;
   
   
	return 0;
}












