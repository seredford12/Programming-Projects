# Sarah Redford, Student ID: 001003030

class HashTable:

    # Constructor with an initial capacity parameter
    # All buckets are assigned to an empty array
    def __init__(self, initial_capacity=10):
        self.table = []
        for i in range(initial_capacity):
            self.table.append([])

    # This function creates a getter that is used to create a hash key for the table
    # O(N) is the space-time complexity
    def get_hash_key(self, key):
        hash_one = 0
        for char in str(key):
            hash_one += ord(char)
        return hash_one % len(self.table)

    # Searches for an item in the hash table with the matching package ID
    # If the item is found in the hash table, it returns the item. If the item
    # is not found, None is returned
    # O(N) is the spacetime complexity
    def find(self, key):
        key_hash = self.get_hash_key(key)
        if self.table[key_hash] is not None:
            for value in self.table[key_hash]:
                if value[0] == key:
                    return value[1]
        return None

    # Updates a packages information in the hash table
    # O(N) is the spacetime complexity
    def update(self, key, value):
        key_hash = self.get_hash_key(key)
        if self.table[key_hash] is not None:
            for pair in self.table[key_hash]:
                if pair[0] == key:
                    pair[1] = value
                    print(pair[1])
                    return True

    # This function adds a package into the hash table
    # O(N) is the spacetime complexity
    def add(self, key, packages):
        key_hash = self.get_hash_key(key)
        key_value = [key, packages]

        if self.table[key_hash] is None:
            self.table[key_hash] = list([key_value])
            return True
        else:
            for pair in self.table[key_hash]:
                if pair[0] == key:
                    pair[1] = packages
                    return True
            self.table[key_hash].append(key_value)
            return True

    # Removes an item from the hash table with the matching package ID
    # Space time complexity is O(N)
    def delete(self, key):
        key_hash = self.get_hash_key(key)

        if self.table[key_hash] is None:
            return False
        for i in range(0, len(self.table[key_hash])):
            if self.table[key_hash][i][0] == key:
                self.table[key_hash].pop(i)
                return True

    # Function used to print the hash table. I used this to make sure the hash table
    # was working properly before continuing with the project
    # runtime is O(N)
    def __str__(self):
        index = 0
        p = " -Hash Table-\n"
        for bucket in self.table:
            p += "%d%s\n" % (index, bucket)
            index += 1
        p += " --------"
        return p


