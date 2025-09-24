abstract class GeometricObject {
    private String color = "white";
    private boolean filled;

    public GeometricObject() {
    }

    public GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public abstract double getArea();
    public abstract double getPerimeter();
}

class Octagon extends GeometricObject implements Comparable<Octagon>, Cloneable {
    private double side;

    public Octagon() {
        this(1.0, "white", false);
    }

    public Octagon(double side) {
        this(side, "white", false);
    }

    public Octagon(double side, String color, boolean filled) {
        super(color, filled);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return 2 * (1 + Math.sqrt(2)) * side * side;
    }

    @Override
    public double getPerimeter() {
        return 8 * side;
    }

    @Override
    public int compareTo(Octagon other) {
        return Double.compare(this.getArea(), other.getArea());
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Octagon: side = " + side +
               ", area = " + getArea() +
               ", perimeter = " + getPerimeter();
    }
}

public class TestOctagon {
    public static void main(String[] args) {
        Octagon o1 = new Octagon(5.0, "blue", true);
        System.out.println("First octagon: " + o1);

        Octagon o2 = (Octagon) o1.clone();
        System.out.println("Cloned octagon: " + o2);

        int result = o1.compareTo(o2);
        if (result == 0) {
            System.out.println("The two octagons are equal in area.");
        } else if (result > 0) {
            System.out.println("The first octagon is larger.");
        } else {
            System.out.println("The first octagon is smaller.");
        }
    }
}