import networkx as nx
import numpy as np
import matplotlib.pyplot as plt
import random


G = nx.read_gml('D:/DataMining/Ex3/karate/karate.gml', label='id')
print('网络的节点个数：', G.number_of_nodes())
print('网络的边数：', G.number_of_edges())
# nx.draw(G, with_labels=True, font_weight='bold')
# plt.show()

'''计算相似度矩阵'''
N = {i: set(G[i]) for i in G}
S = np.zeros((G.number_of_nodes()+1, G.number_of_nodes()+1))
for i in G:
    for j in G:
        S[i, j] = len(N[i] & N[j]) / len(N[i] | N[j])
# print(S)
'''定义密度函数'''
def compute_density_avg(C):
    if len(C) == 1:
        return 1
    density = 0.0
    for i in C:
        for j in C:
            density += S[i, j]
    density /= len(C)**2
    return density

# print(compute_density_avg(list(G.nodes)))



def get_club(threshould):
    clubs = []
    candidate = list(G.nodes)
    one_club = []
    while len(candidate) > 0:
        if len(one_club) == 0:
            picked = candidate[random.randint(0, len(candidate) - 1)]
            candidate.remove(picked)
            one_club.append(picked)
            one_density = compute_density_avg(one_club)
        else:
            min_det = float('inf')
            min_id = -1
            for i in candidate:
                new_club = one_club + [i, ]
                new_density = compute_density_avg(new_club)
                det = one_density - new_density  # 密度的降低值
                min_det, min_id = (det, i) if det < min_det else (min_det, min_id)

            new_density = one_density - min_det
            if new_density < threshould:
                clubs.append(one_club)
                one_club = []
            else:
                candidate.remove(min_id)
                one_club.append(min_id)

    clubs.append(one_club)
    return clubs
def getRandomColor():
    colorArr = ['1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F']
    color = ""
    for i in range(6):
        color += colorArr[random.randint(0, 14)]
    return "#"+color
def draw_clubs(clubs):
    plt.figure(figsize=(6, 5))
    plt.title('threshold=%.2f' %0.25)
    style = nx.spring_layout(G)
    nx.draw(G, pos=style)
    for club in clubs:
        nx.draw(G, pos=style, nodelist=club, node_color=getRandomColor(), with_labels=True)
    plt.show()

club = get_club(0.25)
print(club)
draw_clubs(club)






