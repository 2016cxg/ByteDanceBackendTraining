from django.http import HttpResponse
from readerwriterlock import rwlock

lock = rwlock.RWLockFair()

# def A(request):
#     print(id(lock))
#     return HttpResponse('A')
#
# def B(request):
#     print(id(lock))
#     return HttpResponse('B')