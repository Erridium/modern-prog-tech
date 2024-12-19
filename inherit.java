class Bicycle {
    public int gear;
    public int speed;
    public Bicycle(int gear, int speed) {
        this.gear = gear;
        this.speed = speed;
    }
    public void applyBrake(int decrement) {
        speed -= decrement;
    }
    public void SpeedUp(int increment) {
        speed += increment;
    }
    public String toString() {
        return ("Количество передач равно " + gear + "\n" + "скорость велосипеда равна " + speed);
    }
}

class MountainBike extends Bicycle {
    public int seatHeight;
    public MountainBike(int gear, int speed, int startHeight) {
        super(gear, speed);
        seatHeight = startHeight;
    }
    public void setHeight(int newValue) {
        seatHeight = newValue;
    }
    @Override
    public String toString() {
        return (super.toString() + "высота \nseat равна " + seatHeight);
    }
}

public class Test {
    public static void main(String [] args) {
        MountainBike mb = new MountainBike(3, 100, 25);
    }
}
