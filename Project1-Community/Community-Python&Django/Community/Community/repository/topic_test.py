from topic import  TopicDAO
import os

topicdao = TopicDAO.getSingletonInstanceObject()

a,b = topicdao.QueryTopicsById(1)

print(a)
print(b)