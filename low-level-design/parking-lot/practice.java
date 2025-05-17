// Enum of vehicle type
enum VehicleType {
    CAR, BIKE, OTHER;
}

// Abstract class for Vehicle
abstract class Vehicle {
    protected String licencePlate;
    protected VehicleType type;
    protected ParkingFeeStrategy feeStrategy;

    public Vehicle(String licencePlate, VehicleType type, ParkingFeeStrategy feeStrategy) {
        this.licencePlate = licencePlate;
        this.type = type;
        this.feeStrategy = feeStrategy;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public int calculateFee(VehicleType type, int duration, DurationType durationType) {
        feeStrategy.calculateFee(type, duration, durationType);
    }
}

// Class for car vehicle
class CarVehicle {
    public CarVehicle(String licencePlate, VehicleType type, ParkingFeeStrategy feeStrategy) {
        super(licencePlate, type, feeStrategy)
    }
}

// Class  for bike vehicle
class BikeVehicle {
    public BikeVehicle(String licencePlate, VehicleType type, ParkingFeeStrategy feeStrategy) {
        super(licencePlate, type, feeStrategy)
    }
}

// Class for other vehicle
class OtherVehicle {
    public OtherVehicle(String licencePlate, VehicleType type, ParkingFeeStrategy feeStrategy) {
        super(licencePlate, type, feeStrategy)
    }
}

// Class for vehicle factory
class VehicleFactory {

    public Vehicle createVehicle(String licencePlate, VehicleType type, ParkingFeeStrategy feeStrategy) {
        switch (type.toLowercase()) {
            "car": return new CarVehicle(licencePlate, type, feeStrategy);
            "bike": return new BikeVehicle(licencePlate, type, feeStrategy);
            default: return new OtherVehicle(licencePlate, type, feeStrategy);
        }
    }
}

// Enum for duration type
enum DurationType {
    HOURLY, DAILY;
}

// Class for parking fee strategy
interface ParkingFeeStrategy {
    public int calculateFee(VehicleType type, int duration, DurationType durationType);
}

// Class for basic parking fee strategy
class BasicParkingFeeStrategy implements ParkingFeeStrategy {
    @Override
    public int calculateFee(VehicleType type, int duration, DurationType durationType) {
        switch (type.toLowercase()) {
            case "car": durationType == DurationType.HOURLY ? duration * 10 : duration * 10 * 24;
            case "bike": durationType == DurationType.HOURLY ? duration * 5 : duration * 5 * 24;
            default: durationType == DurationType.HOURLY ? duration * 8 : duration * 8 * 24;
        }
    }
}

// Class for preminum parking fee strategy
class PremiumParkingFeeStrategy implements ParkingFeeStrategy {
    @Override
    public int calculateFee(VehicleType type, int duration, DurationType durationType) {
        switch (type.toLowercase()) {
            case "car": durationType == DurationType.HOURLY ? duration * 12 : duration * 12 * 24;
            case "bike": durationType == DurationType.HOURLY ? duration * 8 : duration * 8 * 24;
            default: durationType == DurationType.HOURLY ? duration * 9 : duration * 9 * 24;
        }
    }
}

// Interface for payment strategy
interface PaymentStrategy {
    public void processPayment(int amount);
}

// Class for cash payment strategy
class CashPaymentSrategy implements PaymentStrategy {
    @Override
    public void processPayment(int amount) {
        System.out.println("Cash processing... ")
    }
}

// Class for debit card payment strategy
class DebitCardPaymentSrategy implements PaymentStrategy {
    @Override
    public void processPayment(int amount) {
        System.out.println("Debit Card processing... ")
    }
}

class Payment {
    private int amount;
    private PaymentStrategy paymentStrategy;

    public Payment(int amount, PaymentStrategy paymentStrategy) {
        this.amount = amount;
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment() {
        paymentStrategy.processPayment(this.amount);
    }
}

// Class for ParkingSlot
class ParkingSpot {
    private VehicleType spotType;
    private boolean isOccupied;
}

// Class for ParkingLot
class ParkingLot {
    private List<ParkingSlot> slots;
}
