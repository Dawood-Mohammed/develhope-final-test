public class Vehicle {
    private String licensePlate;
    private String colour;
    private double pricePerDay;
    private VehicleType type;

    public Vehicle(String licensePlate, String colour, double pricePerDay, VehicleType type) {
        this.licensePlate = licensePlate;
        this.colour = colour;
        this.pricePerDay = pricePerDay;
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "licensePlate='" + licensePlate + '\'' +
                ", colour='" + colour + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", type='" + type.name() + '\''+
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return this.licensePlate.equals(((Vehicle)obj).getLicensePlate()) &&
                this.colour.equals(((Vehicle)obj).getColour()) &&
                this.pricePerDay == ((Vehicle) obj).getPricePerDay() &&
                this.type.equals(((Vehicle)obj).getType());
    }
}
