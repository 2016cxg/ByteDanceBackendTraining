from django.http import HttpResponse

from Community.tests.lock import lock

def B(request):
    print(id(lock))
    return HttpResponse()