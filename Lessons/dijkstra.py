# -*- coding: utf-8 -*-
"""
Created on Mon Nov 26 19:02:26 2018

@author: mjd
"""

import heapq
def dijkstra(graph,start):
    heap=[]
    heapq.heappush(heap,(0,start))
    result={}

    while heap:
#        Pop the minimum distance in the heap list
        (value, key) = heapq.heappop(heap)
        if key not in result:
            result[key]=value
#        If the node do not have neighbor nodes, end this loop
        if not graph[key]:
            continue
#        Traverse all the neighbors of the node
        for neighbor_key,dis in graph[key].items():
#            If neighbor node is in the result already, end this loop
            if neighbor_key in result:
                continue
            new_distance=value+dis
            heapq.heappush(heap,(new_distance,neighbor_key))
    return result

if __name__ == '__main__':
    graph = {
		'S':{'A':4, 'B':16, 'C':8},
		'A':{'C':3},
		'B':{'D':2},
		'C':{'B':7, 'E':1},
		'D':{},
		'E':{'B':5, 'D':6}
    }
    result = dijkstra(graph, 'S')
    for k,v in result.items():
        print('%s：%d' % (k, v))
        