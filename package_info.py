# Sarah Redford, Student ID: 001003030

import csv
from package_hash_table import HashTable

# This code reads the information about the packages from the package csv file
with open('WGUPS Package File_1.csv', 'r') as package_file:
    csv_reader = csv.reader(package_file, delimiter=',')
    csv_reader = list(csv_reader)

    # This creates an object of the package hash table from the hash table class
    package_hash_table = HashTable()

    # Create package list for first truck to hold the packages being loaded onto the
    # first truck
    first_route = []

    # Create package list for second truck to hold the packages being loaded onto the
    # second truck
    second_route = []

    # Create package list for first truck second trip (third route) to hold the packages
    # being loaded onto the truck for the third route
    third_route = []

    # Read in the information from csv file and append that information into the object
    # created from the package hash table class (appended at the end of this code)
    for column in csv_reader:
        package_id = column[0]
        package_address = column[1].strip()
        package_city = column[2].strip()
        package_state = column[3].strip()
        package_zip = column[4].strip()
        package_deadline = column[5].strip()
        package_weight = column[6].strip()
        package_notes = column[7].strip()
        start_delivery = None
        delivery_status = 'At Hub'
        location_index = 'location'
        packages = [package_id, package_address, package_city, package_state, package_zip, package_deadline,
                    package_weight, package_notes, delivery_status, start_delivery, location_index]
        key = package_id

        # The following code is used to load the correct packages on to the trucks for
        # each individual route. The trucks are loaded based on the restrictions for each package
        # such as: package delivery deadline and the package notes. Each package is appended to the
        # correct route list by checking these restrictions. This ensures that they are delivered
        # on time and leave the hub when they should.

        # Change wrong address to right address
        if 'Wrong' in packages[7]:
            packages[1] = '410 S State St'
            packages[2] = 'Salt Lake City'
            packages[3] = 'UT'
            packages[4] = '84111'
            packages[7] = 'Address Corrected'

        # Load packages into second truck
        if packages[5] != 'EOD':
            if 'only' in packages[7]:
                second_route.append(packages)
            if 'Delayed' in packages[7]:
                second_route.append(packages)
        if packages[5] == 'EOD':
            if 'only' in packages[7]:
                second_route.append(packages)
            if 'Delayed' in packages[7]:
                second_route.append(packages)

        # Load packages into first truck
        if packages[5] != 'EOD':
            if packages not in second_route:
                first_route.append(packages)
        if packages[2] == 'Murray' or packages[2] == 'West Valley City':
            if packages not in second_route:
                first_route.append(packages)
        if packages[0] == '19':
            first_route.append(packages)

        # Load packages into first truck second trip (third route)
        if packages not in first_route and packages not in second_route:
            third_route.append(packages)

        # Inserts the time that the truck leaves the hub into the information for each package on the first route.
        if packages in first_route:
            packages[9] = '08:00:00'

        # Inserts the time that the truck leaves the hub into the information for each package on the second route.
        if packages in second_route:
            packages[9] = '09:10:00'

        # Inserts the time that the truck leaves the hub into the information for each package on the third route.
        if packages in third_route:
            packages[9] = '11:30:00'

        # All package information from the csv is added to the package hash table object
        package_hash_table.add(key, packages)


# Insert new package into hash table using all of the parameters/ information required for a package
def insert_package(pkg_id, address, city, state, zip_code, deadline, weight, status, notes):
    # Get the bucket list where the item will go
    package = [(pkg_id, address, city, state, zip_code, deadline, weight, status, notes)]
    # Insert the item into the list of all packages
    package_hash_table.add(key, package)


# Get full list of all packages at the beginning of the day before they are loaded (all packages in the hash table)
def get_package_hash():
    return package_hash_table


# Full list of packages loaded on truck one for route 1
def get_first_route():
    return first_route


# Full list of packages loaded onto truck two for route 2
def get_second_route():
    return second_route


# Full list of packages loaded onto truck one for route 3
def get_third_route():
    return third_route
