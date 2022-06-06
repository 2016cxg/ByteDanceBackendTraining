from query_page_info import *

if __name__ == '__main__':
    a,b = QueryPageInfoFlow(1).Do()


    print(a, b)
    print(a.toJson() )



