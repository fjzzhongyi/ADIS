from django.shortcuts import render
from django.http import JsonResponse
import re


def index(request):
    return render(request, 'GIS.html')


def get_graph(request):
    true_nodes = []
    with open('./graph_interactive_system/static/com_true.dat', 'r') as true_nodes_file:
        true_nodes = eval(true_nodes_file.read())

    graph_data = {
        'nodes': [],
        'edges': []
    }
    with open('./graph_interactive_system/static/com_edges.dat', 'r') as graph_data_file:
        graph_data_str = graph_data_file.read()
    graph_pattern = re.compile(r'(\d+) (\d+)')
    for edge in graph_pattern.finditer(graph_data_str):
        new_node1 = {'name': str(edge.group(1))}
        new_node2 = {'name': str(edge.group(2))}
        if new_node1 not in graph_data['nodes']:
            graph_data['nodes'].append(new_node1)
        if new_node2 not in graph_data['nodes']:
            graph_data['nodes'].append(new_node2)
        graph_data['edges'].append({'source': new_node1['name'], 'target': new_node2['name']})

    for node in graph_data['nodes']:
        if node['name'] in true_nodes:
            node['symbol'] = 'diamond'
            node['symbolSize'] = 20
            node['itemStyle'] = {
                'normal': {
                    'color': 'pink'
                }
            }

    return JsonResponse(graph_data)
