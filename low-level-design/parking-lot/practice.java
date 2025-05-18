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

// Abstract class for ParkingSpot
abstract class ParkingSpot {
    private int spotNumber;
    private VehicleType spotType;
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSpot(int spotNumber, VehicleType spotType) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.isOccupied = false;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public VehicleType getSpotType() {
        return spotType;
    }

    public void setSpotType(VehicleType spotType) {
        this.spotType = spotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public abstract boolean canParkVehicle(Vehicle vehicle);

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        if(this.isOccupied) {
            throw new IllegalStateException("Parking spot is already occupied");
        }
        if(!this.canParkVehicle(vehicle)) {
            throw new IllegalStateException("Parking spot "+ this.spotType + " is not suitable for vehicle "+ vehicle.getType());
        }
        this.vehicle = vehicle;
        this.isOccupied = true;

        return this;
    }

    public ParkingSpot vacate() {
        if(!this.isOccupied) {
            throw new IllegalStateException("Spot is already empty");
        }
        this.vehicle = null;
        this.isOccupied = false;

        return this;
    }
}

// Class for car parking spot
class CarParkingSpot extends ParkingSpot {
    CarParkingSpot(int spotNumber) {
        super(spotNumber, VehicleType.CAR);
    }

    public boolean canParkVehicle(Vehicle vehicle) {
        return "car".equals(vehicle.getType().toLowercase());
    }
}

// Class for bike parking spot
class BikeParkingSpot extends ParkingSpot {
    BikeParkingSpot(int spotNumber) {
        super(spotNumber, VehicleType.BIKE);
    }

    public boolean canParkVehicle(Vehicle vehicle) {
        return "bike".equals(vehicle.getType().toLowercase());
    }
}

// Class for other parking spot
class OtherParkingSpot extends ParkingSpot {
    OtherParkingSpot(int spotNumber) {
        super(spotNumber, VehicleType.OTHER);
    }

    public boolean canParkVehicle(Vehicle vehicle) {
        return "other".equals(vehicle.getType().toLowercase());
    }
}

// Class for ParkingLot
class ParkingLot {
    private List<ParkingSpot> spots;

    public ParkingLot(List<ParkingSpot> spots) {
        this.spots = spots;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public ParkingSpot getSpot(int spotNumber) {
        for(ParkingSpot spot : this.spots) {
            if(spot.getSpotNumber() == spotNumber)
                return spot;
        }
        return null;
    }

    public ParkingSpot parkVehicle(int spotNumber, Vehicle vehicle) {
        try {
            ParkingSpot spot = this.getSpot(spotNumber);
            if(spot != null) {
                spot = spot.parkVehicle(vehicle);
            } else {
                System.out.println("Spot not found")
            }
            return spot;
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    public ParkingSpot vacate(int spotNumber) {
        try {
            ParkingSpot spot = this.getSpot(spotNumber);
            if(spot != null) {
                spot = spot.vacate();
            } else {
                System.out.println("Spot not found")
            }
            return spot;
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}


// Option1 Extensibility: for supporting multiple floors, we can leverage spotNumber and make it unique in complete parking lot system
// Option2 Extensibility: for supporting multiple floors, we can introlduce parking floors class and restructure as following code

class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber, List<ParkingSpot> spots) {
        this.floorNumber = floorNumber;
        this.spots = spots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public void setSpots(List<ParkingSpot> spots) {
        this.spots = spots;
    }

    public ParkingSpot getSpot(int spotNumber) {
        for(ParkingSpot spot : spots) {
            if(spot.getSpotNumber() == spotNumber)
                return spot;
        }
        return null;
    }
}

// Class for ParkingLot with multifloorSupport
class ParkingLot {
    private List<ParkingFloor> parkingFloors;

    public ParkingLot(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public ParkingSpot getSpot(int floorNumber, int spotNumber) {
        for(ParkingFloor floor : this.parkingFloors) {
            if(floor.getFloorNumber() == floorNumber)
                return floor.getSpot(spotNumber);
        }
        return null;
    }

    public ParkingSpot parkVehicle(int floorNumber, int spotNumber, Vehicle vehicle) {
        try {
            ParkingSpot spot = this.getSpot(floorNumber, spotNumber);
            if(spot != null) {
                spot = spot.parkVehicle(vehicle);
            } else {
                System.out.println("Spot not found")
            }
            return spot;
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    public ParkingSpot vacate(int floorNumber, int spotNumber) {
        try {
            ParkingSpot spot = this.getSpot(spotNumber);
            if(spot != null) {
                spot = spot.vacate();
            } else {
                System.out.println("Spot not found")
            }
            return spot;
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}


