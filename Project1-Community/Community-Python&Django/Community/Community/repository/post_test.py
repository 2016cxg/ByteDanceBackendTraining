from post import  PostDAO
import os

postdao = PostDAO.getSingletonInstanceObject()

chck = 1

if chck == 1:
    a,b = postdao.QueryPostsByParentId(1)
    print(a, b)

elif chck == 2:
    a, b = postdao.WritePostIndexMapByParentId(1, 'abc')
    print(postdao.postIndexMap)
    print('aaaaaaaaa')
    print(a)
    print(b)

    a, b = postdao.WritePostFile(a)
    print(a, b)

elif chck == 3:
    a, b = postdao.DoWritePost(1, 'abc')
    print(a,  b)
