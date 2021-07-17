import tkinter as tk
import initial
import ID


def frame():
    global root
    root = tk.Tk()
    root.geometry('900x700')
    root.title('公交管理系统')
    # 上
    lable0 = tk.Label(root, text='司机登录', bg='pink', font=('微软雅黑', 50)).pack()
    # 下
    lable1 = tk.Label(root, text='请选择:', font=('微软雅黑', 20)).place(x=80, y=400)
    tk.Button(root, text='登录', font=('微软雅黑', 15), width=10, height=2, command=login).place(x=150, y=500)
    tk.Button(root, text='注册', font=('微软雅黑', 15), width=10, height=2, command=register).place(x=350, y=500)
    tk.Button(root, text='退出', font=('微软雅黑', 15), width=10, height=2, command=exit_driver).place(x=550, y=500)
    root.mainloop()


def login():
    global root1
    root1 = tk.Tk()
    root1.wm_attributes('-topmost', 1)
    root1.title('司机登录')
    root1.geometry('500x300')

    lable1 = tk.Label(root1, text='账号：', font=25).place(x=100, y=50)
    lable2 = tk.Label(root1, text='密码：', font=25).place(x=100, y=100)

    global entry_name, entry_key
    name = tk.StringVar()
    key = tk.StringVar()

    entry_name = tk.Entry(root1, textvariable=name, font=25)
    entry_name.place(x=180, y=50)
    entry_key = tk.Entry(root1, textvariable=key, font=25, show='*')
    entry_key.place(x=180, y=100)
    # 百度：tkinter要求由按钮（或者其它的插件）触发的控制器函数不能含有参数,若要给函数传递参数，需要在函数前添加lambda
    button1 = tk.Button(root1, text='确定', height=2, width=10, command=lambda: ID.id_check('0'))
    button1.place(x=210, y=180)


def register():
    global root2
    root2 = tk.Tk()
    root2.wm_attributes('-topmost', 1)
    root2.title('司机注册')
    root2.geometry('500x300')

    lable1 = tk.Label(root2, text='账号：', font=25).place(x=100, y=50)
    lable2 = tk.Label(root2, text='密码：', font=25).place(x=100, y=100)
    lable2 = tk.Label(root2, text='确认密码：', font=25).place(x=80, y=150)

    global entry_name, entry_key, entry_confirm
    name = tk.StringVar()
    key = tk.StringVar()
    confirm = tk.StringVar()
    entry_name = tk.Entry(root2, textvariable=name, font=25)
    entry_name.place(x=180, y=50)
    entry_key = tk.Entry(root2, textvariable=key, font=25, show='*')
    entry_key.place(x=180, y=100)
    entry_confirm = tk.Entry(root2, textvariable=confirm,font=25, show='*')
    entry_confirm.place(x=180, y=150)
    # 百度：tkinter要求由按钮（或者其它的插件）触发的控制器函数不能含有参数,若要给函数传递参数，需要在函数前添加lambda
    button1 = tk.Button(root2, text='确定', height=2, width=10, command=lambda: ID.id_write('0'))
    button1.place(x=210, y=200)


def exit_driver():  # 退出司机界面，跳转至初始界面
    root.destroy()
    initial.frame()

