#include <iostream>
#include <math.h>
using namespace std;
const double PI = 3.1415926;
class box{
	public:
		int id;
		double radiux;
		double getArea(void);
		double getVolume(void);
		void setRadiux(double rad);		
}; 
void box::setRadiux(double rad){
	radiux = rad;
}
double box::getArea(){
	return 4*PI*radiux*radiux;
} 
double box::getVolume(){
	return 4*PI*pow(radiux,3)/3;
}


// 基类 Shape
class Shape 
{
   public:
      void setWidth(int w)
      {
         width = w;
      }
      void setHeight(int h)
      {
         height = h;
      }
   protected:
      int width;
      int height;
};
 
// 基类 PaintCost
class PaintCost 
{
   public:
      int getCost(int area)
      {
         return area * 70;
      }
};
 
// 派生类
class Rectangle: public Shape, public PaintCost
{
   public:
      int getArea()
      { 
         return (width * height); 
      }
};
 


int main(){
	box box1;
	
	box1.setRadiux(3);
	cout << "半径radiux；" << box1.radiux << "\t" <<"表面积area：" << box1.getArea() << "\t" <<"体积volume：" << box1.getVolume() <<endl; 
	
	
	
   Rectangle Rect;
   int area;
 
   Rect.setWidth(5);
   Rect.setHeight(7);
 
   area = Rect.getArea();
   
   // 输出对象的面积
   cout << "Total area: " << Rect.getArea() << endl;
 
   // 输出总花费
   cout << "Total paint cost: $" << Rect.getCost(area) << endl;
   return 0;
	
}
