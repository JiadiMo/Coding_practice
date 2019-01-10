# -*- coding: utf-8 -*-
"""
Created on Tue Oct 30 20:36:30 2018

@author: mjd
"""
import time
import random
def merge(a, b):
    c = []
    h = j = 0
    while j < len(a) and h < len(b):
        if a[j] < b[h]:
            c.append(a[j])
            j += 1
        else:
            c.append(b[h])
            h += 1

    if j == len(a):
        for i in b[h:]:
            c.append(i)
    else:
        for i in a[j:]:
            c.append(i)

    return c

def merge_sort(lists):
    if len(lists) <= 1:
        return lists
    middle = int (len(lists)/2)
    left = merge_sort(lists[:middle])
    right = merge_sort(lists[middle:])
    return merge(left, right)

if __name__ == '__main__':
    print("Merge Sort:")
    print("======Test1======")
    array1=[8,7,6,5,4]
    print(merge_sort(array1))
    print("======Test2======")
    array2=[6,3,2,1]
    print(merge_sort(array2))
    print("======Test3======")
    array3=[10,7,6]
    print(merge_sort(array3))
    List = []
    for k in range(0,10000):
        List.append(random.randint(0,2147483647))
    start = time.clock()
    print(merge_sort(List))
    end = time.clock()
    c=end-start

    print("Runtime is ：",c)