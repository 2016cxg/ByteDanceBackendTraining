"""Community URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import include, re_path
from django.urls import path

from Community.controller import views

from Community.tests.A import A
from Community.tests.B import B

urlpatterns = [
    path('admin/', admin.site.urls),
    re_path(r'^Community/PageInfo/get/(\d+)', views.ViewMsg),
    re_path(r'^Community/PageInfo/post/(\d+)', views.ViewWritePost),
    re_path(r'^Community/A', A),
    re_path(r'^Community/B', B)
]
