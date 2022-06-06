
from db_init import Init
import os

def TestInitTopicIndexMap():

    os.environ.setdefault('PYCHARM_PROJECT', r'/')
    # Community
    a,b = Init().InitTopicIndexMap()
    print(a)
    print(b)

def TestInitPostIndexMap():
    os.environ.setdefault('PYCHARM_PROJECT', r'/')
    a,b = Init().InitPostIndexMap()
    print(a)
    print(b)
TestInitPostIndexMap()