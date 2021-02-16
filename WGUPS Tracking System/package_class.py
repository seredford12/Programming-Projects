# Sarah Redford, Student ID: 001003030

in_route = 'In Route'
delivered = 'Delivered'


class Package:

    def __init__(self, package_id, package_address, package_deadline, package_weight, delivery_status):
        self.package_id = package_id
        self.package_address = package_address
        self.package_deadline = package_deadline
        self.package_weight = package_weight
        self.delivery_status = delivery_status
        self.truck = None
        self.left_hub_at = None
        self.delivered_at = None

    def __hash__(self):
        return hash(self.package_id)

    def __eq__(self, key):
        return self.package_id == key