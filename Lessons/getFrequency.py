# -*- coding: utf-8 -*-
"""
Created on Fri Nov 16 10:20:25 2018

@author: mjd
"""

import codecs
import jieba
from collections import Counter

def getFrequency(txt):

    seg_list = jieba.cut(txt)
    
    count = Counter()
    for x in seg_list:
#        Remove distracting factors
        if len(x) > 1 and x != '\r\n':
            count[x] += 1
    return count
    
if __name__ == '__main__':
#    Read wangfeng.txt
    with codecs.open('wangfeng', 'r', 'utf8') as file:
        txt = file.read()
#    Outout the result
    print('TOP 5：')
    result=getFrequency(txt).most_common(5)
    for (k, v) in result:
        print('%s：%d times' % (k, v))
