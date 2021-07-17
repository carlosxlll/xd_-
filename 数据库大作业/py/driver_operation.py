import tkinter as tk
import tkinter.messagebox as msg
from tkinter import ttk
import leader_operation
import datetime as dt  # datetime
import pymysql
import ID


def frame():
    window = tk.Tk()
    window.title('司机')
    window.geometry('700x600')
    # 上
    lable0 = tk.Label(window, text='司机您好', bg='pink', font=('微软雅黑', 50)).pack()
    # 下
    lable1 = tk.Label(window, text='请选择操作:', font=('微软雅黑', 20)).place(x=80, y=400)
    tk.Button(window, text='个人信息', font=('微软雅黑', 15), width=10, height=2, command=driver_information).place(x=350, y=250)
    tk.Button(window, text='违章记录', font=('微软雅黑', 15), width=10, height=2, command=driver_record_information_login).place(x=350, y=350)
    window.mainloop()


def driver_information():  # 个人信息
    window1 = tk.Tk()
    window1.title('司机')
    window1.geometry('700x600')
    lable0 = tk.Label(window1, text='您好', bg='pink', font=('微软雅黑', 50)).pack()  # 上

    global tree  # 建立树形图
    yscrollbar = ttk.Scrollbar(window1, orient='vertical')  # 右边的滑动按钮
    tree = ttk.Treeview(window1, columns=('1', '2', '3'), show="headings", yscrollcommand=yscrollbar.set)
    tree.column('1', width=150, anchor='center')
    tree.column('2', width=150, anchor='center')
    tree.column('3', width=150, anchor='center')
    tree.heading('1', text='名字')
    tree.heading('2', text='性别')
    tree.heading('3', text='职业')
    tree.place(x=200, y=100)
    yscrollbar.place(x=955, y=100)

    # 连接数据库，root是你数据库的用户名，应该是默认的是root，qwer是你数据库的密码，library是你要连接的数据库名字
    db = pymysql.connect("localhost", "root", "qwe123..00", "bus_company")
    # 建立游标cursor，这个游标可以类比指针，这样理解比较直观
    cursor = db.cursor()
    sql = "call bus_company.get_driver_information('%s')" % (ID.getid())

    try:
        cursor.execute(sql)
        results = cursor.fetchall()

        if results:
            l = len(results)
            for i in range(0, l):  # 查询到的结果依次插入到表格中
                tree.insert('', i, values=(results[i]))
        else:
            tree.insert('', 0, values=('查询不到结果', '查询不到结果', '查询不到结果', '查询不到结果'))
    except:
        print("Error:unable to fetch data")
        # 关闭数据库连接
    db.close()
    window1.mainloop()


def driver_record_information_login():
    window1 = tk.Tk()
    window1.title('司机')
    window1.geometry('700x600')
    lable0 = tk.Label(window1, text='违章记录', bg='pink', font=('微软雅黑', 50)).pack()  # 上

    global time1,time2
    tk.Label(window1, text='起始时间：', font=('宋体', 12)).place(x=250, y=230)
    tk.Label(window1, text='截止时间：', font=('宋体', 12)).place(x=250, y=270)
    time1 = tk.Entry(window1, font=('宋体', 12), width=25)
    time1.place(x=350, y=230)
    time2 = tk.Entry(window1, font=('宋体', 12), width=25)
    time2.place(x=350, y=270)

    tk.Button(window1, text='搜索', font=('宋体', 12), width=10, command=driver_record_information).place(x=250, y=300)
    tk.Button(window1, text='返回', font=('宋体', 12), width=10, command=frame).place(x=400, y=300)


def driver_record_information():  # 违章记录
    window1 = tk.Tk()
    window1.title('司机')
    window1.geometry('700x700')
    lable0 = tk.Label(window1, text='违章记录', bg='pink', font=('微软雅黑', 50)).pack()  # 上

    global tree  # 建立树形图
    yscrollbar = ttk.Scrollbar(window1, orient='vertical')  # 右边的滑动按钮
    tree = ttk.Treeview(window1, columns=('1', '2', '3', '4', '5'), show="headings", yscrollcommand=yscrollbar.set)
    tree.column('1', width=150, anchor='center')
    tree.column('2', width=150, anchor='center')
    tree.column('3', width=150, anchor='center')
    tree.column('4', width=150, anchor='center')
    tree.column('5', width=150, anchor='center')

    tree.heading('1', text='名字')
    tree.heading('2', text='车牌号')
    tree.heading('3', text='违章类型')
    tree.heading('4', text='时间')
    tree.heading('5', text='违章地点')
    tree.place(x=10, y=130)
    yscrollbar.place(x=555, y=100)

    # 连接数据库，root是你数据库的用户名，应该是默认的是root，qwer是你数据库的密码，library是你要连接的数据库名字
    db = pymysql.connect("localhost", "root", "qwe123..00", "bus_company")
    # 建立游标cursor，这个游标可以类比指针，这样理解比较直观
    cursor = db.cursor()
    sql = "call bus_company.get_violation_record_by_driver_and_datetime('%s', '%s', '%s')" % (ID.getid(), time1.get(), time2.get())

    try:
        cursor.execute(sql)
        results = cursor.fetchall()

        if results:
            l = len(results)
            for i in range(0, l):  # 查询到的结果依次插入到表格中
                tree.insert('', i, values=(results[i]))
        else:
            tree.insert('', 0, values=('查询不到结果', '查询不到结果', '查询不到结果', '查询不到结果'))
    except:
        print("Error:unable to fetch data")
        # 关闭数据库连接
    db.close()
    window1.mainloop()


if __name__ == '__main__':
    frame()




