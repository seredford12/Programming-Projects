# Sarah Redford, Student ID: 001003030
# See route_one_delivery.py for code comments describing process


from distance_table import get_distance, get_address_list, total_distance
from package_info import get_third_route, get_package_hash
from route_one_delivery import get_first_truck_dist
from route_two_delivery import get_second_truck_dist
from truck_info import get_time_third_truck

third_route_best = []
third_route_best_index = []


# Runtime is O(N^2)
def best_route_three(current_location):
    while len(get_third_route()) > 0:
        minimum_value = 99999.99
        next_location = -1
        for index in get_third_route():
            if get_distance(current_location, int(index[10])) < minimum_value:
                minimum_value = get_distance(current_location, int(index[10]))
                next_location = int(index[10])

        for i in get_third_route():
            if get_distance(current_location, int(i[10])) == minimum_value:
                third_route_best.append(i)
                third_route_best_index.append(i[10])
                get_third_route().pop(get_third_route().index(i))
                current_location = next_location
    return len(get_third_route())


third_route_best_index.insert(0, '0')


def get_third_route_best_index():
    return third_route_best_index


def get_third_route_best():
    return third_route_best


# Runtime is O(N^2)
third_truck_index = 0
for k in get_third_route():
    for j in get_address_list():
        if k[1] == j[2]:
            get_third_route()[third_truck_index][10] = j[0]
            break
    third_truck_index += 1

best_route_three(0)
third_truck_distance = -1
third_truck_package_id = 0

# Runtime is O(N)
for i in range(len(get_third_route_best_index())):
    try:
        cost_calculation_three = get_time_third_truck(get_distance(int(get_third_route_best_index()[i]),
                                                                   int(get_third_route_best_index()[i + 1])))

        third_truck_distance = total_distance(int(get_third_route_best_index()[i]),
                                              int(get_third_route_best_index()[i + 1]),
                                              third_truck_distance)

        get_third_route_best()[third_truck_package_id][8] = str(cost_calculation_three)
        get_package_hash().update(int(get_third_route_best()[third_truck_package_id][0]), get_third_route())
        third_truck_package_id += 1
    except IndexError:
        pass


def get_third_truck_dist():
    return third_truck_distance


# Calculates the total mileage from all three trucks rounded to one decimal place
def get_truck_total_mileage():
    return float.__round__((get_first_truck_dist() + get_second_truck_dist() + get_third_truck_dist()), 1)
