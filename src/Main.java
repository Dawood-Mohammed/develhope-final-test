import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Map<Customer, List<Vehicle>> rentedVehicles = new HashMap<>();
    public static void main(String[] args) {
        Vehicle vehicle1 = new Vehicle("AUG-123", "Red", 255.5, VehicleType.CAR);
        Vehicle vehicle2 = new Vehicle("AUG-333", "White", 145.5, VehicleType.CAR);
        Vehicle vehicle3 = new Vehicle("AUG-111", "Yellow", 555.5, VehicleType.TRUCK);
        Vehicle vehicle4 = new Vehicle("AUG-142", "Silver", 455.5, VehicleType.TRUCK);
        Vehicle vehicle5 = new Vehicle("AUG-712", "Red", 225.5, VehicleType.CAR);
        Vehicle vehicle6 = new Vehicle("AUG-987", "Black", 145.5, VehicleType.CAR);
        Customer customer1 = new Customer("Ahmed", "Kampala", 23);
        Customer customer2 = new Customer("Ali", "Hassan", 29);
        rentVehicle(vehicle1, customer1);
        rentVehicle(vehicle2, customer1);
        rentVehicle(vehicle3, customer1);
        rentVehicle(vehicle4, customer2);
        rentVehicle(vehicle5, customer2);
        rentVehicle(vehicle6, customer2);
        System.out.println("rented vehicles: "+rentedVehicles);
        System.out.println("Is vehicle1 rented? "+isRented(vehicle1));
        returnVehicle(vehicle1, customer1);
        System.out.println("Is vehicle1 rented? "+isRented(vehicle1));
        System.out.println("Total price for customer1: "+calculateTotalPriceForCustomer(customer1));
        System.out.println("Total price for customer2: "+calculateTotalPriceForCustomer(customer2));
        System.out.println("Max 5 rentals per vehicle: "+getMaxFiveRentals());
    }

    /**
     * @param vehicle is the vehicle to be rented
     * @param customer is the customer who is renting the vehicle
     * the method will check if the customer has already rented vehicles
     * if true then will add the vehicle to customers rented vehicles list
     * otherwise will initialize a new list and add the vehicle to it then link it with the customer
     */
    public static void rentVehicle(Vehicle vehicle, Customer customer){
        List<Vehicle> customerVehicles = rentedVehicles.get(customer);
        if(customerVehicles != null){
            customerVehicles.add(vehicle);
        }else {
            customerVehicles = new ArrayList<>();
            customerVehicles.add(vehicle);
        }
        rentedVehicles.put(customer, customerVehicles);
    }

    /**
     * @param vehicle is the vehicle to be rented
     * @param customer is the customer who is renting the vehicle
     * the methods removes the vehicle from the customer's rented vehicle list
     * if it exists
     */
    public static void returnVehicle(Vehicle vehicle, Customer customer){
        List<Vehicle> customerVehicles = rentedVehicles.get(customer);
        customerVehicles.removeIf(v -> v.equals(vehicle));
        rentedVehicles.put(customer, customerVehicles);
    }

    /**
     * @param vehicle is the vehicle to be checked if it's rented
     * @return true if the vehicle is rented otherwise false
     */
    public static boolean isRented(Vehicle vehicle){
        return rentedVehicles.entrySet()
                .stream()
                .anyMatch(entry -> entry.getValue().contains(vehicle));
    }

    /**
     * @param customer is the customer who's rental prices are going to be calculated for total
     * @return (double) the total price for the customers rentals
     */
    public static double calculateTotalPriceForCustomer(Customer customer){
        return rentedVehicles.get(customer)
                .stream()
                .mapToDouble(Vehicle::getPricePerDay)
                .sum();
    }

    /**
     * @return get a list of max last 5 rentals per vehicle
     */
    public static List<Vehicle> getMaxFiveRentals(){
        List<Vehicle> vehicles = rentedVehicles
                .values()
                .stream()
                .reduce(new ArrayList<Vehicle>(), (List<Vehicle> list, List<Vehicle> vList) -> {
                    list.addAll(vList);
                    return list;
                });
        Collections.sort(vehicles, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return Double.compare(o1.getPricePerDay(), o2.getPricePerDay());
            }
        });
        int size = vehicles.size();
        if(size > 5)
            return vehicles.subList(size - 5, size);
        return vehicles;
    }
}