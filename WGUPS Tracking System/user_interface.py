# Sarah Redford, Student ID: 001003030


import datetime
from package_info import get_package_hash, package_hash_table, insert_package
from route_three_delivery import get_truck_total_mileage


# This function converts the string times entered below by the user into a time duration that can be used by the program
# runtime is O(1)
def get_time_format(time):
    user_input = time

    (h, m, s) = user_input.split(':')
    updated_time = datetime.timedelta(hours=int(h), minutes=int(m), seconds=int(s))

    return updated_time


# Call to print package information
def print_package_info(value):
    print('Package ID: ', get_package_hash().find(str(value))[0], ' Street Address: ',
          get_package_hash().find(str(value))[1], ' City: ', get_package_hash().find(str(value))[2],
          ' State: ', get_package_hash().find(str(value))[3], ' Zip Code: ',
          get_package_hash().find(str(value))[4], ' Package Deadline: ',
          get_package_hash().find(str(value))[5],
          ' Package Weight: ', get_package_hash().find(str(value))[6], ' Package Notes: ',
          get_package_hash().find(str(value))[7], ' Delivery Status: ',
          get_package_hash().find(str(value))[8])


# User interface created to print prompt statements to the user. The user interface is an interactive tool so the
# user can lookup information about a package or the status of all packages at a specific time. The user can also add
# a package using the user interface.
# runtime is O(N)
def UI():
    print('Welcome to WGUPS user interface!')
    print("\nAll routes were finished in ", get_truck_total_mileage(), " miles.")
    print("All packages were delivered on time.\n")
    user_entry = input('Menu:'
                       '\n To lookup one package, type: lookup package.'
                       '\n To lookup the delivery status of packages at a given time, type: time '
                       '\n To add a new package, type: add package'
                       '\n If you want to exit the program, type: exit'
                       '\n')

    # checks to make sure the user entered a valid entry
    if user_entry.lower() not in ['lookup package', 'time', 'add package', 'exit']:
        print('Invalid entry. Please start again.')
        UI()

    while user_entry.lower() != 'exit':

        # The following code is accessed if the user enters time. They are prompted to enter a
        # time into the console
        if user_entry.lower() == 'time':
            package_time = input('Please enter a start time with the format: HH:MM:SS. ')
            user_time = get_time_format(package_time)
            print('\nDisplaying packages at', package_time)

            # This loop iterates through all of the packages in the hash table so that each package
            # can be accessed for the package information
            for value in range(1, 41):
                try:
                    time_one = get_package_hash().find(str(value))[9]
                    time_two = get_package_hash().find(str(value))[8]
                    time_one_converted = get_time_format(time_one)
                    time_two_converted = get_time_format(time_two)
                except ValueError:
                    pass

                # This checks if the user's input time is before the truck has left the hub.
                # The delivery status is updated to 'At Hub' if this is true.
                if time_one_converted >= user_time:
                    get_package_hash().find(str(value))[8] = 'At Hub'
                    get_package_hash().find(str(value))[9] = 'Package will leave hub at: ' + time_one
                    print('\n' + get_package_hash().find(str(value))[9])
                    print_package_info(value)

                # This checks to see if the user's input is after the truck has left the hub.
                # The delivery status is updated to 'in transit' if the package has left the hub, but has not been
                # delivered. If the package has been delivered, 'delivered' is updated to the delivery status.

                # Checks to see if the truck has left the hub. User's entered time is after the route start time.
                elif time_one_converted <= user_time:

                    # Checks to see if the package has been delivered yet. Updates the delivery status to 'in transit'
                    # if the package is not delivered.
                    if user_time < time_two_converted:
                        get_package_hash().find(str(value))[8] = 'In Transit'
                        get_package_hash().find(str(value))[9] = 'Package left hub at: ' + time_one
                        print('\n' + get_package_hash().find(str(value))[9])
                        print_package_info(value)

                    # If the package has been delivered the delivery status is updated to 'delivered'.
                    else:
                        get_package_hash().find(str(value))[8] = 'Delivered'
                        get_package_hash().find(str(value))[9] = 'Package delivered at: ' + time_two
                        print('\n' + get_package_hash().find(str(value))[9])
                        print_package_info(value)

                # Prints an error message if neither of these is the case
                else:
                    print('Something went wrong. Please try again.')
            exit()

        # The following code is accessed if the user enters 'lookup package'. The user is prompted to enter a package ID
        # to lookup and the time at which they would like to see the status of the package.
        if user_entry.lower() == 'lookup package':

            value = input('Please enter the package ID for the package you would like to lookup: ')
            package_time = str(input('Please enter a time with the format: HH:MM:SS. '))
            user_time = get_time_format(package_time)
            time_one = get_package_hash().find(str(value))[9]
            time_two = get_package_hash().find(str(value))[8]
            time_one_converted = get_time_format(time_one)
            time_two_converted = get_time_format(time_two)

            # Checks to see if the user's input time is before the package has left the hub. Package is at hub if this
            # is true.
            if time_one_converted >= user_time:
                get_package_hash().find(str(value))[8] = 'At Hub'
                get_package_hash().find(str(value))[9] = 'Package leaves hub at: ' + time_one
                print('\n' + get_package_hash().find(str(value))[9] + '\n')
                print_package_info(value)

            # Checks to see if the truck has left the hub. User's entered time is after the start time of the route.
            elif time_one_converted <= user_time:

                # If package has left the hub, but has not been delivered yet
                if user_time < time_two_converted:
                    get_package_hash().find(str(value))[8] = 'In Transit'
                    get_package_hash().find(str(value))[9] = 'Package left the hub at: ' + time_one
                    print('\n' + get_package_hash().find(str(value))[9] + '\n')
                    print_package_info(value)

                # Package left the hub and was delivered
                else:
                    get_package_hash().find(str(value))[8] = 'Delivered'
                    get_package_hash().find(str(value))[9] = 'Package was delivered at ' + time_two
                    print('\n' + get_package_hash().find(str(value))[9])
                    print_package_info(value)

            # Prints error message if something went wrong.
            else:
                print('Something went wrong. Please try again.')
            exit()

        # interactive interface to allow user to add a package to the package hash table
        if user_entry.lower() == 'add package':
            print('To add a package, please input the package information.')

            package_id = int(input('Please enter the package id: \n'))
            package_address = str(input('Please enter the package address: \n'))
            package_city = str(input('Please enter the package city: \n'))
            package_state = str(input('Please enter the package state: \n'))
            package_zip = str(input('Please enter the package zip code: \n'))
            package_deadline = str(input('Please enter the package deadline: \n'))
            package_weight = str(input('Please enter the package weight: \n'))
            package_status = str(input('Please enter the package status: \n'))
            package_notes = str(input('Please enter the package notes: \n'))

            new_package = insert_package(package_id, package_address, package_city, package_state, package_zip,
                                         package_deadline, package_weight, package_status, package_notes)

            # adds the new package information to the hash table with the add function from package_hash_table.py
            if package_hash_table.add(package_id, new_package):
                print('Package inserted successfully!\n')
                break

            # Error message that prints if package was added unsuccessfully
            else:
                print('There was an error. Please re-enter the package information.')
                break

    if user_entry == 'exit':
        print('Have a Good Day!')
        exit()


# Calls user interface
UI()
