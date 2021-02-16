# Sarah Redford, Student ID: 001003030

import csv

# This code reads in the information in the distance table to be used to get the distances
# Between two locations
with open('WGUPS Distance Table_1 - WGUPS Distance Table.csv', 'r') as distance_file:
    distance_csv = csv.reader(distance_file, delimiter=',')
    distance_csv = list(distance_csv)

# This code reads in the information from the address csv file
with open('WGUPS Addresses - WGUPS Distance Table (1).csv', 'r') as address_file:
    address_csv = csv.reader(address_file, delimiter=',')
    address_csv = list(address_csv)

    # Create empty array to hold the address information
    address_list = []

    # Reads information from address csv file and appends it into the address_list array
    for row in address_csv:
        address_index = row[0].strip('ï»¿')
        destination = row[1].strip('ï»¿')
        address = row[2].strip('ï»¿')

        address_info = [address_index, destination, address]

        address_list.append(address_info)


# returns the list of all addresses from csv file
def get_address_list():
    return address_list


# This is a 2D array that takes a list of the row and column values from the distance table and finds the current
# distance of one point from the graph
# runtime is O(1)
def get_distance(r_value, c_value):
    cost = distance_csv[r_value][c_value]
    return float(cost)


# This function is also a 2D array that takes in a row and column value, but returns the total distance from one
# location to another
# runtime is O(1)
def total_distance(row_value, column_value, total_distance_traveled):
    cost = float(distance_csv[row_value][column_value])
    total_distance_traveled += float(cost)
    return total_distance_traveled
