# Sarah Redford, Student ID: 001003030


from distance_table import get_distance, total_distance, get_address_list
from package_info import get_first_route, get_package_hash
from truck_info import get_time_first_truck

# The first list holds the optimized list for package delivery for truck one
# The second list holds the indexes for each item in the optimized list of packages for truck one
first_route_best = []
first_route_best_index = []


# Sorting algorithm uses a greedy approach to find the optimized list for delivery
# see https://www.geeksforgeeks.org/greedy-algorithms/
# Runtime complexity is O(N^2)
def best_route_one(current_location):
    # The loop iterates while there are still packages in the first route to optimize
    while len(get_first_route()) > 0:
        min_value = 99999
        next_index = -1

        # This loop iterates through all packages contained in the route one truck. The current distance location is
        # found and compared to the current minimum value. If the current distance is less than the minimum value,
        # minimum value is set equal to the current distance. Next index is set equal to the index location for
        # the minimum value from the address list in distance_table.py. See below for how to get the address index.
        for location in get_first_route():
            if get_distance(current_location, int(location[10])) < min_value:
                min_value = get_distance(current_location, int(location[10]))
                next_index = int(location[10])

        # This loop once again iterates through the list of packages on the first truck to check if the minimum value is
        # equal to the current location value. If this is true, the package index is added to the optimized list of
        # packages and the address location index is added to the index list for future use. That index is then removed
        # from the first route list and the current location is set equal to the address location index.
        for location in get_first_route():
            if get_distance(current_location, int(location[10])) == min_value:
                first_route_best.append(location)
                first_route_best_index.append(location[10])
                get_first_route().pop(get_first_route().index(location))
                current_location = next_index

    # The process repeats until all packages have been sorted into the optimized delivery list
    return len(get_first_route())


# The route must be able to return to the hub, so we need to insert the location for the hub
# back into the index list to return the truck to it's original location
first_route_best_index.insert(0, '0')


# Function that holds the first route optimized indexes from the address list.
def get_first_route_best_index():
    return first_route_best_index


# Function that holds the list for the first route optimized package delivery
def get_first_route_best():
    return first_route_best


# This function is used to iterate through the the first route list and the get address list from distance.py. It
# compares the address values from each list found in index 1 of the first route list and index two of the address
# list. If they are equal the first index of the address list is appended to the index list of my first route.
# Index 10 of the first route list is then updated to hold the index number of the address list. (This is where the
# the address is located in the distance table). This address index is then used in the greedy algorithm to find the
# current distance of a vertex in the graph. These loops iterate through all items in the list
# Space-time complexity is O(N^2)
first_truck_index = 0
for k in get_first_route():
    for j in get_address_list():
        if k[1] == j[2]:
            get_first_route()[first_truck_index][10] = j[0]
            break
    first_truck_index += 1

# This is where we call the greedy algorithm to begin optimizing the package delivery for the first route
# The truck begins the route at the hub, so the current distance is set to zero
best_route_one(0)

# Here we create a variable to hold the value of the truck distance
# the variable is updated to hold the total distance traveled by the truck during the route
first_truck_distance = -1

# This variable is used as an input to get the first index (index zero) from the list. We continue adding one to this
# variable in each iteration until there are no more packages
first_truck_package_id = 0

# This function iterates through each index in the length of the first route index list
# Runtime is O(N)
for i in range(len(get_first_route_best_index())):
    try:

        # This calculates the time that it takes to deliver each package based on their optimized stops
        # This calculates the distance that is then run through the time algorithm found in
        # truck_info.py and returns the final time that the package was delivered.
        cost_calculation = get_time_first_truck(get_distance(int(get_first_route_best_index()[i]),
                                                             int(get_first_route_best_index()[i + 1])))
        # Calculates the truck's total distance from one stop to another. The total distance is continually added,
        # giving us the total distance of the truck at the end.
        first_truck_distance = total_distance(int(get_first_route_best_index()[i]),
                                              int(get_first_route_best_index()[i + 1]),
                                              first_truck_distance)

        # Time the package was delivered is set equal to index 8 (delivery status)
        get_first_route_best()[first_truck_package_id][8] = (str(cost_calculation))

        # Information is updated into the package hash table for each package
        get_package_hash().update(int(get_first_route_best()[first_truck_package_id][0]), get_first_route())
        first_truck_package_id += 1
    except IndexError:
        pass


# returns the total distance of the first truck
def get_first_truck_dist():
    return first_truck_distance
