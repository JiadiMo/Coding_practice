# -*- coding: utf-8 -*-
"""
Created on Tue Nov 27 21:11:09 2018

@author: mjd
"""
import random

def countingSort(list_a,k):
    n=len(list_a)
#    Initialize
    sort=[0 for i in range(n)]
    count=[0 for i in range(k)]
#    Count numbers in list given
    for i in list_a:
        count[i]+=1
#    Count numbers of elements <= this element
    for i in range(1,k):
        count[i]=count[i-1]+count[i]
#    Put into result
    for i in list_a:
        sort[count[i]-1]=i
        count[i]-=1
    return sort
if __name__=='__main__':
    k=9
    a=[random.randint(0,k) for i in range(10)]
    print ('Initialize:')
    print (a)
    print ('Counting sort:')
    print (countingSort(a,k+1))