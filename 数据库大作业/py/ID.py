import tkinter as tk
import tkinter.messagebox as msg
import pymysql
import initial
import line_leader
import leader
import driver
import line_leader_operation
import driver_operation
import leader_operation

def id_check(a):  # 检查账号
    global id
    # 连接数据库，root是你数据库的用户名，应该是默认的是root，qwer是你数据库的密码，library是你要连接的数据库名字
    db = pymysql.connect("localhost", "root", "qwe123..00", "bus_company")
    # 建立游标cursor，这个游标可以类比指针，这样理解比较直观
    cursor = db.cursor()
    db.commit()
    db.close()
    if a == '2':  # 在车队长界面下登录，参数是2
        # 把账号/密码框框里输入的字符串赋值给id/password
        id = leader.entry_name.get()
        password = leader.entry_key.get()
    elif a == '1':  # 在路队长界面下登录，参数是1
        # 把账号/密码框框里输入的字符串赋值给id/password
        id = line_leader.entry_name.get()
        password = line_leader.entry_key.get()
    elif a == '0':  # 在司机界面下登录，参数是0
        id = driver.entry_name.get()
        password = driver.entry_key.get()
    getid() # 最后得到id
    # 连接数据库，root是你数据库的用户名，应该是默认的是root，qwer是你数据库的密码，library是你要连接的数据库名字
    db = pymysql.connect("localhost", "root", "qwe123..00", "bus_company")
    # 建立游标cursor，这个游标可以类比指针，这样理解比较直观
    cursor = db.cursor()
    sql = "SELECT password FROM user WHERE id='%s' AND job='%s'" % (id, a)
    cursor.execute(sql)  # sql语句被执行
    result = cursor.fetchone()  # 得到的结果返回给result数组
    if result:  # 如果查询到了账号存在
            if password == result[0] :  # result[0]是数组中的第一个结果
                success_login(a)  # 密码对上了，进入对应的读者/管理员操作界面
            else:  # 有账号但密码没对上
               msg._show(title='错误！', message='账号或密码输入错误或者无权限！')
    else:  # 没有账号
        msg._show(title='错误！', message='您输入的用户不存在！请先注册！')
        if a == '2':
            leader.root1.destroy()  # 关闭登录小窗口，回到管理员界面
        elif a == '1':
            line_leader.root1.destroy()  # 关闭登录小窗口，回到管理员界面
        elif a == '0':
            driver.root1.destroy()
    db.close()  # 查询完一定要关闭数据库啊


def success_login(a):  # 成功登录
    if a == '2':
        leader.root1.destroy()
        leader_operation.frame()  # 销毁登录注册界面，跳转到车队长的操作界面

    elif a == '1':
        line_leader.root1.destroy()
        line_leader_operation.frame()  # 销毁登录注册界面，跳转到路队长的操作界面

    elif a == '0':
        driver.root1.destroy()
        driver_operation.frame()  # 销毁登录注册界面，跳转到司机的操作界面


def id_write(a):  # 写入（注册）账号
    global id
    m=1
    if a == '2':  # 跟check函数里边十分类似
        id = leader.entry_name.get()  # 得到输入的账号
        password = leader.entry_key.get()  # 得到输入的密码
        confirm = leader.entry_confirm.get()  # 得到输入的确认密码
    elif a == '1':  # 跟check函数里边十分类似
        id = line_leader.entry_name.get()  # 得到输入的账号
        password = line_leader.entry_key.get()  # 得到输入的密码
        confirm = line_leader.entry_confirm.get()  # 得到输入的确认密码
    elif a == '0':
        id = driver.entry_name.get()
        password = driver.entry_key.get()
        confirm = driver.entry_confirm.get()

    db = pymysql.connect("localhost", "root", "qwe123..00", "bus_company")
    # 建立游标cursor，这个游标可以类比指针，这样理解比较直观
    cursor = db.cursor()
    sql0 = "SELECT id FROM user WHERE id='%s' AND job='%s'" % (id, a)
    sql1 = "INSERT INTO user VALUES(%s,%s,%s) " % (id, password, a)
# 首先检查两次输入的密码是否一致，一致后再检查注册的账号是否已经存在
    if password == confirm:
        cursor.execute(sql0)
        result = cursor.fetchone()
        if result or m==0:
            msg.showerror(title='错误！', message='该账号已被注册或者无权限，请重新输入！')
        else:
            cursor.execute(sql1)
            db.commit()
            db.close()
            msg.showinfo(title='成功！', message='注册成功，请登录！')

    else:
        msg.showerror(title='错误！', message='两次密码不一致，请重新输入！')


def getid():
    return id
