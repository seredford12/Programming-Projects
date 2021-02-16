# Sarah Redford, Student ID: 001003030

import datetime

# create lists to hold the times that the trucks leave the hub so that we can append the time
# in minutes to the list to get the trucks time at a given point in the route
first_time = ['08:00:00']
second_time = ['09:05:00']
third_time = ['11:30:00']


# references
# https://stackoverflow.com/questions/51846547/how-to-convert-float-into-hours-minutes-seconds/51846718#51846718
# https://stackoverflow.com/questions/27496889/converting-a-float-to-hhmm-format
# This function takes the distance input and divides it by 18 (the truck travels at 18 mph)
# Divmod is then called to convert the truck time variable into a time
# O(N) is the spacetime complexity of this function
def get_time_first_truck(distance):
    truck_time = distance / 18
    truck_minutes = '{0:02.0f}:{1:02.0f}'.format(*divmod(truck_time * 60, 60))

    # We need to append the seconds to truck_minutes to match the time format that is throughout the program.
    final_time = truck_minutes + ':00'
    first_time.append(final_time)
    total = datetime.timedelta()

    # This loop iterates through the first_time list which contains the start time and the time in minutes that we
    # just appended. It then takes the minutes that it took to deliver the package and adds it to the original start
    # time of the truck, giving us The time when the package was delivered
    for i in first_time:
        (hrs, min, sec) = i.split(':')
        minutes = datetime.timedelta(hours=int(hrs), minutes=int(min), seconds=int(sec))
        total += minutes
    return total


# The above function is then repeated to get the times that the packages were delivered for each package
# in the second route
# runtime is O(N)
def get_time_second_truck(distance):
    truck_time = distance / 18
    truck_minutes = '{0:02.0f}:{1:02.0f}'.format(*divmod(truck_time * 60, 60))

    # We need to append the seconds to truck_minutes variable to match the time format that is throughout the program.
    final_time = truck_minutes + ':00'
    second_time.append(final_time)
    total = datetime.timedelta()

    # This loop iterates through the first_time list which contains the start time and the time in minutes that we
    # just appended. It then takes the minutes that it took to deliver the package and adds it to the original start
    # time of the truck, giving us The time when the package was delivered
    for i in second_time:
        (hrs, min, sec) = i.split(':')
        minutes = datetime.timedelta(hours=int(hrs), minutes=int(min), seconds=int(sec))
        total += minutes
    return total


# The function is again repeated to get the delivery times of packages for the third route
# runtime is O(N)
def get_time_third_truck(distance):
    truck_time = distance / 18
    truck_minutes = '{0:02.0f}:{1:02.0f}'.format(*divmod(truck_time * 60, 360))

    # We need to append the seconds to truck_minutes variable to match the time format that is throughout the program.
    final_time = truck_minutes + ':00'
    third_time.append(final_time)
    total = datetime.timedelta()

    # This loop iterates through the first_time list which contains the start time and the time in minutes that we
    # just appended. It then takes the minutes that it took to deliver the package and adds it to the original start
    # time of the truck, giving us The time when the package was delivered
    for i in third_time:
        (hrs, min, sec) = i.split(':')
        minutes = datetime.timedelta(hours=int(hrs), minutes=int(min), seconds=int(sec))
        total += minutes
    return total
