import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

np.seterr(divide='ignore', invalid='ignore')
"""导入数据集"""
ratings = pd.read_csv('D:/DataMining/Ex1/ml-latest-small/ratings.csv')
movies = pd.read_csv('D:/DataMining/Ex1/ml-latest-small/movies.csv')

# 建立训练集和测试集数据
flag = np.random.rand(len(ratings)) < 0.9  # np.random.rand()返回一组服从”0~1“均匀分布随机样本值
train_ratings = ratings[flag]
test_ratings = ratings[~flag]
# print('trainset_shape = ',train_ratings.shape)
# print('testset_shape = ',test_ratings.shape)

"""建立邻接矩阵A"""
users = ratings.userId.unique()  # m个用户
movie = ratings.movieId.unique()  # n部电影
uid_index = {uid: id for id, uid in enumerate(users)}  # 用enumerate获得索引和值,创建字典
mid_index = {mid: id for id, mid in enumerate(movie)}
m = len(users)
n = len(movie)
# print(m,n)

A = np.zeros((m, n))
for p, rating in train_ratings[train_ratings.rating > 3].iterrows(): #iterrows()返回每行的索引及一个包含行本身的对象，即DataFrame中的一行，即Serie
    A[uid_index[rating.userId], mid_index[rating.movieId]] = 1

A_test = np.zeros((m, n), dtype=np.int64)
for p, rating in test_ratings[test_ratings.rating > 3].iterrows():
    A_test[uid_index[rating.userId], mid_index[rating.movieId]] = 1

"""训练模型，计算出W资源配额矩阵"""
k_movie = A.sum(axis=0)
k_user = A.sum(axis=1)
temp1 = A / k_user.reshape((-1, 1))
temp1[np.isnan(temp1)] = 0
temp2 = np.dot(temp1.T, A)
# print(temp2.shape,k_movie.shape)
W = temp2 / k_movie
W[np.isnan(W)] = 0

"""评价推荐模型的准确性"""
F = np.dot(W, A.T).T
F[A == 1] = 0
F_sort_index = np.argsort(F, axis=1)
F_sort = np.zeros((m, n))
for i in range(m):
    for j in range(n):
        F_sort[i, F_sort_index[i][j]] = n - j  # 所排名次
# print(F.shape,F_sort.shape)

# 计算平均推荐相对位置r
L = n - k_user
r = np.average(F_sort * A_test, axis=1) / L
r_avg = np.average(r)
print(r_avg)

#画出ROC曲线
T_ = np.sum(A_test)
F_ = np.sum(A_test == False)
TPR,FPR = [],[]
for threshold in np.arange(0,1,0.01):
    F_out = F_sort < (n * threshold)
    TP = np.sum(A_test & F_out)
    FP = np.sum(~A_test & F_out)
    TPR.append(TP / T_)
    FPR.append(FP / F_)
AUC = np.sum([tpr * 0.01 for tpr in TPR])
print(AUC)
plt.plot(FPR, TPR, 'b', label='AUC = %0.5f' % AUC)
plt.legend(loc='lower right')
plt.plot([0,1], [0,1], 'r--')
plt.xlim([-0.1, 1.1])
plt.ylim([-0.1, 1.1])
plt.xlabel('False Positive Rate')
plt.ylabel('True Positive Rate')
plt.title('ROC')
plt.show()
