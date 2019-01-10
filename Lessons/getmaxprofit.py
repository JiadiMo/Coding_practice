# -*- coding: utf-8 -*-
"""
Created on Fri Nov 16 10:20:25 2018

@author: mjd
"""

import sys
import random

def get_maxprofit(A):
    if len(A)<=1:
        return 0
    else:
        mid=len(A)//2
        AL=A[:mid]
        AR=A[mid:]
        mp_l=get_maxprofit(AL)
        mp_r=get_maxprofit(AR)
        
        tempMax=0
        for index in range(len(AR)):
            if A[index]> tempMax:
                tempMax=A[index]
                global saleindex
                saleindex=index+mid
                
        tempMin = sys.maxsize
        for index in range(len(AL)):
            if A[index] < tempMin:
                tempMin = A[index]
                global buyindex
                buyindex = index+mid
                
        return max(mp_l,mp_r,tempMax-tempMin)

def get_maxprofit2(A):
    B=[]
    C=[]
    tempMin = sys.maxsize
    maxProfit=0
    indexList=[]
    tempIndex=0
    for index in range(len(A)):
        if A[index] < tempMin:
            tempMin = A[index]
            tempIndex=index
        indexList.append(tempIndex)
        B.append(tempMin)
        tempProfit=A[index]-B[index]
        C.append(tempProfit)
        
    for index in range(len(A)):
        if C[index] > maxProfit:
            maxProfit=C[index]
            global saleindex
            saleindex=index
            global buyindex
            buyindex=indexList[index]
    return max(C) 
if __name__ == '__main__':
    buyindex=0
    saleindex=0
    A=[]
    B=[]
    print("======Test1======")
    num=random.randint(1,10)
    for index in range(num):
        A.append(random.randint(1,20))
    print(A)
    print("Profit:" + str(get_maxprofit(A)))
    print("Buy index:" + str(buyindex)+" value:"+ str(A[buyindex]))
    print("Sell index:" + str(saleindex)+" value:"+ str(A[saleindex]))
    print("======Test2======")
    num2=random.randint(1,10)
    for index in range(num2):
        B.append(random.randint(1,20))
    print(B)
    print("Profit:" + str(get_maxprofit2(B)))
    print("Buy index:" + str(buyindex)+" value:"+ str(B[buyindex]))
    print("Sell index:" + str(saleindex)+" value:"+ str(B[saleindex]))
    
    
    
    
    