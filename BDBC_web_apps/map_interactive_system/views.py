from django.shortcuts import render
from django.http import JsonResponse
import re
import os


def index(request):
    return render(request, 'MIS.html')


def get_map(request):
    algorithm_names = []
    algorithm_series = []
    timeline_data = []
    time_show_interval = 30
    options = []

    with open('./map_interactive_system/static/state_event.dat', 'r', encoding='utf-8') as state_event_file:
        provinces_list = state_event_file.read().split('\n')

    with open('./map_interactive_system/static/TrueLabel-haze.txt', 'r', encoding='utf-8') as TrueLabel_file:
        day_haze_pattern = re.compile(r'(.*?) \[ (.*?) \]')
        for i, day_haze_data in enumerate(day_haze_pattern.finditer(TrueLabel_file.read())):
            day_haze_time = day_haze_data.group(1)
            timeline_data.append(day_haze_time)
            day_haze_list = day_haze_data.group(2).split(' ')
            options.append({
                'name': 'TruthLabel',
                'title': {
                    'subtext': day_haze_time
                },
                'series': [
                    {'data': list(map(lambda p, d: {'name': p, 'value': d}, provinces_list, day_haze_list))}
                ]
            })

    for i in range(int(len(timeline_data)/time_show_interval)+1):
        timeline_data[i * time_show_interval] = {
            'value': timeline_data[i * time_show_interval],
            'symbol': 'diamond',
            'symbolSize': 8,
            'label': {
                'normal': {
                    'show': True
                },
            },
        }

    result_path = './map_interactive_system/static/result/'
    for file_name in list(os.walk(result_path))[0][2]:
        algorithm_name_pattern = re.compile(r'([a-zA-Z]+)-.*')
        algorithm_name = algorithm_name_pattern.match(file_name).group(1)
        algorithm_names.append(algorithm_name)
        algorithm_series.append({
                'name': algorithm_name,
                'type': 'map',
                'mapType': 'china',
                'roam': False,
                'label': {
                    'normal': {
                        'show': False
                    },
                    'emphasis': {
                        'show': True
                    }
                }
        })
        with open(result_path+file_name, 'r', encoding='utf-8') as result_file:
            day_haze_pattern = re.compile(r'(.*?) \[ (.*?) \]')
            for i, day_haze_data in enumerate(day_haze_pattern.finditer(result_file.read())):
                day_haze_list = day_haze_data.group(2).split(' ')
                options[i]['series'].append({
                    'data': list(map(lambda p, d: {'name': p, 'value': d}, provinces_list, day_haze_list))
                })

    return JsonResponse({'timeline_data': timeline_data, 'options': options, 'algorithm_names': algorithm_names, 'algorithm_series': algorithm_series})