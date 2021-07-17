import tkinter as tk
import driver
import line_leader
import leader


def frame():  # 初始界面
    global root
    root = tk.Tk()
    root.geometry('900x700')
    root.title('公交管理系统')
    # 上
    lable0 = tk.Label(root, text='公交管理系统', bg='pink', font=('微软雅黑', 50)).pack()
    # 下
    lable1 = tk.Label(root, text='请选择用户类型:', font=('微软雅黑', 20)).place(x=80, y=500)
    tk.Button(root, text='司机', font=('微软雅黑',15), width=10, height=2, command=exit_driver).place(x=350, y=320)
    tk.Button(root, text='路队长', font=('微软雅黑', 15), width=10, height=2, command=exit_line_leader).place(x=350, y=450)
    tk.Button(root, text='队长', font=('微软雅黑', 15), width=10, height=2, command=exit_leader).place(x=350, y=580)

    # 必须要有这句话，你的页面才会动态刷新循环，否则页面不会显示
    root.mainloop()


def exit_line_leader():  # 跳转至路队长界面
    root.destroy()
    line_leader.frame()


def exit_driver():  # 跳转至司机界面
    root.destroy()
    driver.frame()


def exit_leader():  # 跳转至车队长界面
    root.destroy()
    leader.frame()


if __name__ == '__main__':
    frame()














