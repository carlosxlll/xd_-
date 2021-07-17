#include <fstream>
#include <iostream>
#include <string>
#include <cstring>
#include <vector>

using namespace std;
//书籍信息结构体
typedef struct ibook {
    int bookid;//编号
    string name;//名字
    int sum;//数量
    
}Book;
typedef struct ireader {
    int num;//编号
    string name;//读者名字
    int bookid;//所借图书的id   
}Reader;

vector<Book> book;//内存数据,每次操作需要将外存的数据读入内存，然后写入外存
vector<Reader> reader;//内存数据，每次都需要将外存的reader数据读入内存，然后写入外存
string readername;//读者状态时当前的用户名字,全局变量
int readernum;//读者状态时的当前的用户编号，全局变量
void write_to_file()//每次进行增删改查之后都需要刷新外存数据
{
    ofstream outputfile1("reader.txt", ios::out);
    ofstream outputfile2("book.txt", ios::out);
    if ((!outputfile1) || (!outputfile2))
    {
        cout << "文件系统损毁" << endl;
        return;
    }
    for (int i = 0; i < book.size(); i++)
    {
        outputfile2 << book[i].bookid << " " << book[i].name << " " << book[i].sum << endl;
    }
    for (int i = 0; i < reader.size(); i++)
    {
        outputfile1 << reader[i].num << " " << reader[i].name << " " << reader[i].bookid << endl;

    }
    outputfile1.close();
    outputfile2.close();
}

void file_read()//更新内存数据，每次初始化都要读入内存
{
    ifstream inputfile1("book.txt", ios::in);
    ifstream inputfile2("reader.txt", ios::in);
    if ((!inputfile1) || (!inputfile2))
    {
        cout << "文件系统已经损坏" << endl;
        return;
    }
    Book tmp;
    Reader tmp2;
    while(inputfile1 >> tmp.bookid >> tmp.name >> tmp.sum)
    {
        
        
        book.push_back(tmp);
    }
    while(inputfile2 >> tmp2.num >> tmp2.name >> tmp2.bookid)
    {
        
        
        reader.push_back(tmp2);
    }
}
//查询所有书本信息
void Select_all_book()
{
    for (int i = 0; i < book.size(); i++)
    {
        cout << "书本id: " << book[i].bookid ;
        cout << "书本名字: " << book[i].name ;
        cout << "所剩书本数量:" << book[i].sum << endl;

    }
}
//根据id查询书本信息
void Select_by_id()
{
    int bookid;
    cout << "输入bookid" << endl;
    cin >> bookid;
    for (int i = 0; i < book.size(); i++)
    {
        if (book[i].bookid == bookid)
        {
            cout << "------------------------------" << endl;
            cout << "书本id:" << book[i].bookid << endl;
            cout << "书本名字:" << book[i].name << endl;
            cout << "所剩书本数量:" << book[i].sum << endl;
            cout << "------------------------------" << endl;
        }
    }
}
//根据名字查询书本信息
void Select_by_name()
{
    string name;
    cout << "输入book的名字" << endl;
    cin >> name;
    for (int i = 0; i < book.size(); i++)
    {
        if (book[i].name == name)
        {
            cout << "------------------------------" << endl;
            cout << "书本id:" << book[i].bookid << endl;
            cout << "书本名字:" << book[i].name << endl;
            cout << "所剩书本数量:" << book[i].sum << endl;
            cout << "------------------------------" << endl;
        }
    }
}
//查询读者的编号 
int Select_reader()
{
	for(int i=0;i<reader.size();i++)
	{
		if(reader[i].name == readername)
		{
			return reader[i].num;
		}
	}
	return -1;
}
//读者借书
void borrowbook()
{
    int bookid;
    cout << "请输入借阅书本的编号" << endl;
    cin >> bookid;
    for (int i = 0; i < book.size(); i++)
    {
        if (book[i].bookid == bookid)
        {
            if (book[i].sum > 0)
            {
                book[i].sum--;
                cout << "借书成功" << endl;

                //要更新reader数组;
                Reader tmp;
                tmp.bookid = bookid;
                tmp.name = readername;
                tmp.num = readernum;
                reader.push_back(tmp);
                //更新外存状态
                write_to_file();
                cout << "借书成功" << endl;
                return;
            }
            else
            {
                cout << "没有剩余的书本，借书失败" << endl;
                return;
            }
        }
    }
    cout << "没有对应id的书本,借书失败" << endl;
}
//读者还书
void givebackbook()
{
    int bookid;
    cout << "请输入借阅书本的编号" << endl;
    cin >> bookid;
    for (int i = 0; i < book.size(); i++)
    {
        if (book[i].bookid == bookid)
        {
            book[i].sum++;
            //同时要更新reader数组,删除reader的借书信息
            
            for (int j = 0; j < reader.size(); j++)
            {
                if (reader[j].bookid == bookid && reader[j].name == readername)
                {
                    reader.erase(reader.begin() + j);
                }
            }
            //更新外存数据
            write_to_file();
            cout << "还书成功" << endl;
            return;
        }
    }
    cout << "没有对应编号的书本或者没有对应的读者数据，请检查书本id和用户名" << endl;
}
//查询每本书被谁借走了
void Admin_select_by_bookid()
{
    int bookid;
    cout << "输入bookid" << endl;
    cin >> bookid;
    cout << "显示接走此书的用户" << endl;
    for (int i = 0; i < reader.size(); i++)
    {
        if (reader[i].bookid == bookid)
        {
            cout << reader[i].name << endl;
        }
    }
}
//管理员进行插入图书数据
void Admin_insert_book()
{
    Book tmp;
    cout << "请输入需要增加的书的id:" << endl;
    cin >> tmp.bookid;
    cout << "请输入需要增加的书的名称" << endl;
    cin >> tmp.name;
    cout << "请输入需要增加的书的数量" << endl;
    cin >> tmp.sum;
    book.push_back(tmp);
    //更新外存信息
    write_to_file();
}
//管理员增加读者信息
void Admin_insert_reader()
{
    Reader tmp;
    cout << "请输入需要增加的读者id" << endl;
    cin >> tmp.num;
    cout << "请输入这个读者的名字" << endl;
    cin >> tmp.name;
    cout << "请输入读者借了这本书的id" << endl;
    cin >> tmp.bookid;
    reader.push_back(tmp);
    //更新外存信息
    write_to_file();
}
//管理员删除一条读者借阅的记录
void Admin_delete_reader()
{
    Reader tmp;
    cout << "请输入需要删除借阅记录的读者的姓名" << endl;
    cin >> tmp.name;
    cout << "请输入需要删除借阅记录的读者借的那本书的id" << endl;
    cin >> tmp.bookid;
    for (int i = 0; i < reader.size(); i++)
    {
        if ((reader[i].bookid == tmp.bookid)&&(reader[i].name == tmp.name))
        {
            reader.erase(reader.begin() + i);
            //也要更新这个book数据,剩余书本的数量+1
            for (int j = 0; j < book.size();j++)
            {
                if (book[j].bookid == tmp.bookid)
                {
                    book[j].sum++;
                }
            }
        }
    }
    //需要更新外存数据
    write_to_file();
    cout << "删除成功" << endl;
}
//管理员删除书本数据
void Admin_delete_book()
{
    cout << "请输入你需要删除的书本的id" << endl;
    int bookid;
    cin >> bookid;
    for (int i = 0; i < book.size(); i++)
    {
        if (bookid == book[i].bookid)
        {
            book.erase(book.begin() + i);
        }
    }
    write_to_file();
    cout << "删除成功" << endl;
}
//管理员更新书本信息
void Admin_update_book()
{
    cout << "请输入需要更新的书本的id" << endl;
    int bookid;
    cin >> bookid;
    cout << "请输入这本书的新名字" << endl;
    int sum;
    string name;
    cin >> name;
    cout << "请输入这本书的新的现存数量" << endl;
    cin >> sum;
    for (int i = 0; i < book.size(); i++)
    {
        if (book[i].bookid == bookid)
        {
            book[i].name = name;
            book[i].sum = sum;
            write_to_file();
            return;
        }
    }
    cout << "没有这本书的id" << endl;

}
//管理员更新读者名字
void Admin_update_reader()
{
    int id;
    cout << "请输入读者id" << endl;
    cin >> id;
    cout << "请输入此读者的新的名字" << endl;
    string newname;
    cin >> newname;
    for (int i = 0; i < reader.size(); i++)
    {
        if (reader[i].num == id)
        {
            reader[i].name = newname;
        }
    }
    write_to_file();
    cout << "更新成功" << endl;
}
//管理员显示最详细的再借信息
void Admin_show_all()
{
    for (int i = 0; i < book.size(); i++)
    {
        cout << "书id:" << book[i].bookid ;
        cout << "  书名字:" << book[i].name ;
        cout << "  书现存数量:" << book[i].sum << endl;
        for (int j = 0; j < reader.size(); j++)
        {
            if (book[i].bookid == reader[j].bookid)
            {
                cout << "读者id:" << reader[j].num << "  读者名字:"<<reader[j].name << endl;
            }
        }
    }
}
int main(int argc,char** argv)
{
    //首先将文件读入内存
    file_read();
    cout << "*************************************图书管理系统*************************************" << endl;
    readername = argv[2];
    string mode = argv[1];
    //管理员
    if (mode == "-a")
    {
        while (1)
        {
        	cout << "********************************************************" << endl; 
            cout << "1.查看所有的书本，读者信息" << endl;
            cout << "2.增加读者信息" << endl;
            cout << "3.增加书本信息" << endl;
            cout << "4.删除读者信息" << endl;
            cout << "5.删除书本信息" << endl;
            cout << "6.更新读者信息" << endl;
            cout << "7.更新书本信息" << endl;
            cout << "8.查询对应编号被哪些读者借了" << endl;
            cout << "9.根据图书编号进行图书信息查询" << endl;
            cout << "10.根据书本名字进行图书信息查询" << endl;
         	cout << "********************************************************" << endl;            
            int m;
            cin >> m;
 	        switch(m){
        	case 1:Admin_show_all();
        	break;
        	case 2:Admin_insert_reader();
        	break;
        	case 3:Admin_insert_book();
        	break;
        	case 4:Admin_delete_reader();
        	break;
        	case 5:Admin_delete_book();
        	break;
        	case 6:Admin_update_reader();
        	break;        	
        	case 7:Admin_update_book();
        	break;
        	case 8:Admin_select_by_bookid();
        	break;			
        	case 9:Select_by_id();
        	break;			
        	case 10:Select_by_name();
        	break;					        	
        	default:printf("重新输入");
    	} 
            
        }
    }
    //用户
    if (mode == "-u")
    {
    	readernum = Select_reader();
    	if(readernum == -1)
    	{
    		cout<<"没有对应的用户"<<endl; 
    		exit(0); 
		}
        while (1)
        {
        	cout << "********************************************************" << endl; 
            cout << "1.查看所有的书本信息" << endl;
            cout << "2.借书" << endl;
            cout << "3.还书" << endl;
            cout << "4.据图书编号进行图书信息查询" << endl;
            cout << "5.根据图书名字进行图书信息查询" << endl;
         	cout << "********************************************************" << endl;            
           
            int m;
            cin >> m;
             switch(m){
        	case 1:Select_all_book();
        	break;
        	case 2:borrowbook();
        	break;
        	case 3:givebackbook();
        	break;
        	case 4:Select_by_id();
        	break;
        	case 5:Select_by_name();
        	break;  	
        	default:printf("重新输入");
    	} 
        }
    }
}




