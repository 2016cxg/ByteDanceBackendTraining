from django.http import HttpResponse

from Community.tests.lock import lock

def A(request):
    print(id(lock))
    return HttpResponse()