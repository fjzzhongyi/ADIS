from django.shortcuts import render
from django.http import JsonResponse
import re
import copy
import os

def index(request):
    return render(request, 'GIS.html')


def get_graph(request):
    algorithm_options = {}
    time_length = 30
    options = []
    graph_data = {
        'data': [],
        'links': []
    }

    with open('./graph_interactive_system/static/G.dat', 'r', encoding='utf-8') as graph_data_file:
        graph_data_str = graph_data_file.read()
        graph_pattern = re.compile(r'(\d+) (\d+)')
        for edge in graph_pattern.finditer(graph_data_str):
            new_node1 = {'name': edge.group(1)}
            new_node2 = {'name': edge.group(2)}
            if new_node1 not in graph_data['data']:
                graph_data['data'].append(new_node1)
            if new_node2 not in graph_data['data']:
                graph_data['data'].append(new_node2)
            graph_data['links'].append({'source': new_node1['name'], 'target': new_node2['name']})

    graph_data['data'].sort(key=lambda node: int(node['name']))

    for i in range(time_length):
        options.append({'series': copy.deepcopy(graph_data)})

    with open('./graph_interactive_system/static/P.dat', 'r', encoding='utf-8') as nodes_values_file:
        for i, node_values in enumerate(nodes_values_file.readlines()):
            for j, value in enumerate(node_values.split(' ')):
                options[j]['series']['data'][i]['value'] = int(float(value) * 10)

    result_path = './graph_interactive_system/static/result'
    for file_name in list(os.walk(result_path))[0][2]:
        algorithm_name_pattern = re.compile(r'([a-zA-Z0-9]+)\.dat')
        algorithm_name = algorithm_name_pattern.match(file_name).group(1)
        algorithm_options[algorithm_name] = copy.deepcopy(options)
        with open('./graph_interactive_system/static/result/' + algorithm_name + '.dat', 'r', encoding='utf-8') as true_nodes_file:
            for i, nodes_indexes in enumerate(true_nodes_file.readlines()):
                for node_index in map(lambda x: int(x), nodes_indexes.split(' ')):
                    algorithm_options[algorithm_name][i]['series']['data'][node_index]['symbol'] = 'diamond'
                    algorithm_options[algorithm_name][i]['series']['data'][node_index]['symbolSize'] = 20

    return JsonResponse({'algorithm_options': algorithm_options, 'time_data': list(range(1, time_length+1))})
