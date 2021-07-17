import tkinter as tk
import tkinter.messagebox as msg
import leader_operation
from tkinter import ttk
import pymysql
import ID


def frame():
    window = tk.Tk()
    window.title('车队长')
    window.geometry('700x600')
    # 上
    lable0 = tk.Label(window, text='车队长您好', bg='pink', font=('微软雅黑', 50)).pack()
    # 下
    lable1 = tk.Label(window, text='请选择操作:', font=('微软雅黑', 20)).place(x=80, y=400)
    tk.Button(window, text='人员信息', font=('微软雅黑', 15), width=10, height=2, command=driver_information_by_fleet).place(x=350, y=250)
    tk.Button(window, text='车辆信息', font=('微软雅黑', 15), width=10, height=2, command=car_information_by_fleet).place(x=350, y=300)
    tk.Button(window, text='违章记录', font=('微软雅黑', 15), width=10, height=2, command=driver_record_information_by_fleet_login).place(x=350, y=350)
    tk.Button(window, text='插入司机信息', font=('微软雅黑', 15), width=10, height=2, command=insert_driver_information).place(x=350, y=400)
    tk.Button(window, text='插入车辆', font=('微软雅黑', 15), width=10, height=2, command=insert_car_information).place(x=350, y=450)
    tk.Button(window, text='插入违章记录', font=('微软雅黑', 15), width=10, height=2, command=insert_driver_record_information).place(x=350, y=500)

    window.mainloop()


def driver_information_by_fleet():  # 车队人员信息
    window1 = tk.Tk()
    window1.title('车队长')
    window1.geometry('700x600')
    lable0 = tk.Label(window1, text='车队人员信息', bg='pink', font=('微软雅黑', 50)).pack()  # 上

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
    sql0 = "SELECT fleet_id FROM fleet WHERE leader_id=('%s')" % (ID.getid())
    cursor.execute(sql0)
    results = cursor.fetchone()
    sql = "call get_driver_information_by_fleet('%s')" % (results)

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


def car_information_by_fleet():  # 车队车辆信息
    window1 = tk.Tk()
    window1.title('车队长')
    window1.geometry('700x600')
    lable0 = tk.Label(window1, text='车辆信息', bg='pink', font=('微软雅黑', 50)).pack()  # 上

    global tree  # 建立树形图
    yscrollbar = ttk.Scrollbar(window1, orient='vertical')  # 右边的滑动按钮
    tree = ttk.Treeview(window1, columns=('1', '2', '3'), show="headings", yscrollcommand=yscrollbar.set)
    tree.column('1', width=150, anchor='center')
    tree.column('2', width=150, anchor='center')
    tree.heading('1', text='车牌号')
    tree.heading('2', text='座位')
    tree.place(x=200, y=100)
    yscrollbar.place(x=955, y=100)

    # 连接数据库，root是你数据库的用户名，应该是默认的是root，qwer是你数据库的密码，library是你要连接的数据库名字
    db = pymysql.connect("localhost", "root", "qwe123..00", "bus_company")
    # 建立游标cursor，这个游标可以类比指针，这样理解比较直观
    cursor = db.cursor()
    sql0 = "SELECT fleet_id FROM fleet WHERE leader_id=('%s')" % (ID.getid())
    cursor.execute(sql0)
    results = cursor.fetchone()
    sql = "call bus_company.get_car_information_by_fleet('%s')" % (results)

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


def driver_record_information_by_fleet_login():
    window1 = tk.Tk()
    window1.title('车队长')
    window1.geometry('700x600')
    lable0 = tk.Label(window1, text='违章记录', bg='pink', font=('微软雅黑', 50)).pack()  # 上

    global time1,time2
    tk.Label(window1, text='起始时间：', font=('宋体', 12)).place(x=250, y=230)
    tk.Label(window1, text='截止时间：', font=('宋体', 12)).place(x=250, y=270)
    time1 = tk.Entry(window1, font=('宋体', 12), width=25)
    time1.place(x=350, y=230)
    time2 = tk.Entry(window1, font=('宋体', 12), width=25)
    time2.place(x=350, y=270)

    tk.Button(window1, text='搜索', font=('宋体', 12), width=10, command=driver_record_information_by_fleet).place(x=250, y=300)
    tk.Button(window1, text='返回', font=('宋体', 12), width=10, command=frame).place(x=400, y=300)


def driver_record_information_by_fleet():  # 违章记录
    window1 = tk.Tk()
    window1.title('车队长')
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

    tree.heading('1', text='类型')
    tree.heading('2', text='总数')
    tree.place(x=10, y=130)
    yscrollbar.place(x=555, y=100)

    # 连接数据库，root是你数据库的用户名，应该是默认的是root，qwer是你数据库的密码，library是你要连接的数据库名字
    db = pymysql.connect("localhost", "root", "qwe123..00", "bus_company")
    # 建立游标cursor，这个游标可以类比指针，这样理解比较直观
    cursor = db.cursor()
    sql0 = "SELECT fleet_id FROM fleet WHERE leader_id=('%s')" % (ID.getid())
    cursor.execute(sql0)
    results = cursor.fetchone()
    sql = "call bus_company.get_violation_record_by_fleet_and_datetime2('%s', '%s', '%s')" % (results[0], time1.get(), time2.get())

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


def insert_driver_information():  # 插入违章记录
    window1 = tk.Tk()
    window1.title('插入司机')
    window1.geometry('700x600')
    lable0 = tk.Label(window1, text='人员信息', bg='pink', font=('微软雅黑', 50)).pack()  # 上

    global driver_id
    tk.Label(window1, text='司机工号:', font=('宋体', 15)).place(x=100, y=200)
    driver_id = tk.Entry(window1, font=('宋体', 10), width=25)
    driver_id.place(x=200, y=200)

    global name
    tk.Label(window1, text='姓名:', font=('宋体', 15)).place(x=100, y=230)
    name = tk.Entry(window1, font=('宋体', 10), width=25)
    name.place(x=200, y=230)

    global sex
    tk.Label(window1, text='性别:', font=('宋体', 15)).place(x=100, y=260)
    sex = tk.Entry(window1, font=('宋体', 10), width=25)
    sex.place(x=200, y=260)

    global profession
    tk.Label(window1, text='职业:', font=('宋体', 15)).place(x=100, y=290)
    profession = tk.Entry(window1, font=('宋体', 10), width=25)
    profession.place(x=200, y=290)

    global line_id
    tk.Label(window1, text='路线信息:', font=('宋体', 15)).place(x=100, y=320)
    line_id = tk.Entry(window1, font=('宋体', 10), width=25)
    line_id.place(x=200, y=320)

    tk.Button(window1, text='确认提交', font=('宋体', 11), width=10, command=add1).place(x=100, y=345)


def add1():  # 添加内容到数据库中

    # 连接数据库，root是你数据库的用户名，应该是默认的是root，qwer是你数据库的密码，library是你要连接的数据库名字
    db = pymysql.connect("localhost", "root", "qwe123..00", "bus_company")
    # 建立游标cursor，这个游标可以类比指针，这样理解比较直观
    cursor = db.cursor()
    sql = "call bus_company.insert_driver_information('%s','%s','%s','%s','%s') " % (driver_id.get(), name.get(), sex.get(), profession.get(), line_id.get())
    cursor.execute(sql)
    db.commit()
    db.close()
    msg.showinfo(title='成功!', message='新司机信息已录入!')


def insert_car_information():  # 插入车辆信息
    window1 = tk.Tk()
    window1.title('插入违章记录')
    window1.geometry('700x600')
    lable0 = tk.Label(window1, text='插入车辆信息', bg='pink', font=('微软雅黑', 50)).pack()  # 上

    global car_id
    tk.Label(window1, text='车牌号:', font=('宋体', 15)).place(x=100, y=200)
    car_id = tk.Entry(window1, font=('宋体', 10), width=25)
    car_id.place(x=200, y=200)

    global line_id
    tk.Label(window1, text='线路号:', font=('宋体', 15)).place(x=100, y=230)
    line_id = tk.Entry(window1, font=('宋体', 10), width=25)
    line_id.place(x=200, y=230)

    global seat
    tk.Label(window1, text='座位数:', font=('宋体', 15)).place(x=100, y=260)
    seat = tk.Entry(window1, font=('宋体', 10), width=25)
    seat.place(x=200, y=260)

    global remark
    tk.Label(window1, text='附加信息:', font=('宋体', 15)).place(x=100, y=290)
    remark = tk.Entry(window1, font=('宋体', 10), width=25)
    remark.place(x=200, y=290)


    tk.Button(window1, text='确认提交', font=('宋体', 11), width=10, command=add2).place(x=100, y=345)


def add2():  # 添加内容到数据库中

    # 连接数据库，root是你数据库的用户名，应该是默认的是root，qwer是你数据库的密码，library是你要连接的数据库名字
    db = pymysql.connect("localhost", "root", "qwe123..00", "bus_company")
    # 建立游标cursor，这个游标可以类比指针，这样理解比较直观
    cursor = db.cursor()
    sql = "call bus_company.insert_car_information('%s','%s','%s','%s') " % (car_id.get(), line_id.get(), seat.get(), remark.get())
    cursor.execute(sql)
    db.commit()
    db.close()
    msg.showinfo(title='成功!', message='新车辆记录已录入!')


def insert_driver_record_information():  # 插入违章记录
    window1 = tk.Tk()
    window1.title('插入违章记录')
    window1.geometry('700x600')
    lable0 = tk.Label(window1, text='人员信息', bg='pink', font=('微软雅黑', 50)).pack()  # 上

    global driver_id
    tk.Label(window1, text='司机工号:', font=('宋体', 15)).place(x=100, y=200)
    driver_id = tk.Entry(window1, font=('宋体', 10), width=25)
    driver_id.place(x=200, y=200)

    global car_id
    tk.Label(window1, text='车牌号:', font=('宋体', 15)).place(x=100, y=230)
    car_id = tk.Entry(window1, font=('宋体', 10), width=25)
    car_id.place(x=200, y=230)

    global type
    tk.Label(window1, text='违章类型:', font=('宋体', 15)).place(x=100, y=260)
    type = tk.Entry(window1, font=('宋体', 10), width=25)
    type.place(x=200, y=260)

    global time
    tk.Label(window1, text='时间:', font=('宋体', 15)).place(x=100, y=290)
    time = tk.Entry(window1, font=('宋体', 10), width=25)
    time.place(x=200, y=290)

    global port
    tk.Label(window1, text='地点:', font=('宋体', 15)).place(x=100, y=320)
    port = tk.Entry(window1, font=('宋体', 10), width=25)
    port.place(x=200, y=320)

    tk.Button(window1, text='确认提交', font=('宋体', 11), width=10, command=add3).place(x=100, y=345)


def add3():  # 添加内容到数据库中

    # 连接数据库，root是你数据库的用户名，应该是默认的是root，qwer是你数据库的密码，library是你要连接的数据库名字
    db = pymysql.connect("localhost", "root", "qwe123..00", "bus_company")
    # 建立游标cursor，这个游标可以类比指针，这样理解比较直观
    cursor = db.cursor()
    sql = "call bus_company.insert_driver_violation_information('%s','%s','%s','%s','%s') " % (driver_id.get(), car_id.get(), type.get(), time.get(), port.get())
    cursor.execute(sql)
    db.commit()
    db.close()
    msg.showinfo(title='成功!', message='新记录已录入!')




if __name__ == '__main__':
    frame()
