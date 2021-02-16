# Sarah Redford, Student ID: 001003030
# See route_one_delivery.py for code comments describing process


from distance_table import get_distance, get_address_list, total_distance
from package_info import get_second_route, get_package_hash
from truck_info import get_time_second_truck

second_route_best = []
second_route_best_index = []


# Runtime is O(N^2)
def best_route_two(current_location):
    while len(get_second_route()) > 0:
        minimum_value = 99999.99
        next_location = -1
        for index in get_second_route():
            if get_distance(current_location, int(index[10])) < minimum_value:
                minimum_value = get_distance(current_location, int(index[10]))
                next_location = int(index[10])

        for i in get_second_route():
            if get_distance(current_location, int(i[10])) == minimum_value:
                second_route_best.append(i)
                second_route_best_index.append(i[10])
                get_second_route().pop(get_second_route().index(i))
                current_location = next_location
    return len(get_second_route())


second_route_best_index.insert(0, '0')


def get_second_route_best_index():
    return second_route_best_index


def get_second_route_best():
    return second_route_best


# Runtime is O(N^2)
second_truck_index = 0
for k in get_second_route():
    for j in get_address_list():
        if k[1] == j[2]:
            get_second_route()[second_truck_index][10] = j[0]
            break
    second_truck_index += 1

best_route_two(0)
second_truck_distance = -1
second_truck_package_id = 0


# Runtime is O(N^2)
for i in range(len(get_second_route_best_index())):
    try:
        cost_calculation_two = get_time_second_truck(get_distance(int(get_second_route_best_index()[i]),
                                                                  int(get_second_route_best_index()[i + 1])))

        second_truck_distance = total_distance(int(get_second_route_best_index()[i]),
                                               int(get_second_route_best_index()[i + 1]),
                                               second_truck_distance)

        get_second_route_best()[second_truck_package_id][8] = str(cost_calculation_two)
        get_package_hash().update(int(get_second_route_best()[second_truck_package_id][0]), get_second_route())
        second_truck_package_id += 1
    except IndexError:
        pass


def get_second_truck_dist():
    return second_truck_distance
