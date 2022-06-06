import json

from Community.controller.query_page_info import QueryMessageFlow

if __name__ == '__main__':
    a,b = QueryMessageFlow('1').Do()

    print(a, b)

    print(a.getInfo().getTopic())
    c = a.toJson() ;
    print(type(c))
    print('adsf')
    print( c )


    myret = json.loads(c)
    print(myret)


