import json

from django.http import HttpResponse
from Community.controller.query_page_info import QueryMessageFlow
from Community.controller.write_page_info import WritePostFlow


def ViewMsg(request, p1):
    # p1 = request.GET.get('p1')
    # print(p1)

    ret, _ = QueryMessageFlow(p1).Do()

    return HttpResponse( ret.toJson() )

def ViewWritePost(request, pid):

    content = request.POST.get('name')
    # print( request.body )
    if content is None :
        tmp = request.body.decode("utf-8")
        content = tmp.split('=')[1]

    msg = WritePostFlow(pid, content).DoWritePost()
        # print(msg.toJson())

    return HttpResponse( msg.toJson() )