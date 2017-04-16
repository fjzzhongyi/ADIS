"""BDBC_web_apps URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.10/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url
from django.contrib import admin
from BDBC_web_apps import views as BDBC_views
from graph_interactive_system import views as GIS_views
from map_interactive_system import views as MIS_views

urlpatterns = [
    url(r'^$', BDBC_views.index, name='BDBC'),
    url(r'^admin/', admin.site.urls, name='admin'),
    url(r'^GIS/$', GIS_views.index, name='GIS'),
    url(r'^GIS/get_graph/$', GIS_views.get_graph, name='get_graph'),
    url(r'^MIS/$', MIS_views.index, name='MIS'),
    url(r'^MIS/get_map/$', MIS_views.get_map, name='get_map'),
]
