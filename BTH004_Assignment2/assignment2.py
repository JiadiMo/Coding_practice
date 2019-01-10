# -*- coding: utf-8 -*-
"""
Created on Tue Oct 30 19:39:54 2018

@author: mjd
"""
import random
import time
def bubble_sort(array):
    length=len(array)
    count=0
    for i in range(0,length-1):
        for j in range(0,length-1-i):
            if array[j]>array[j+1]:
                temp=array[j]
                array[j]=array[j+1]
                array[j+1]=temp
                count+=1
    print("Count:",count)
    return array

print("Bubble Sort:")
print("======Test1======")
array1=[8,7,6,5,4]
print(bubble_sort(array1))
print("======Test2======")
array2=[6,3,2,1]
print(bubble_sort(array2))
print("======Test3======")
array3=[10,7,6]
print(bubble_sort(array3))

print("======RANDOM======")
List = []
for k in range(0,10000):
    List.append(random.randint(0,2147483647))
start = time.clock()
print(bubble_sort(List))
end = time.clock()
c=end-start

print("Runtime is ：",c)
