interface Colorable {
    void howToColor();
}

abstract class GeometricObject {
    private String color = "white";
    private boolean filled;

    protected GeometricObject() {}

    protected GeometricObject(String color, boolean filled) {
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

class Triangle extends GeometricObject implements Colorable {
    private double side1 = 1.0;
    private double side2 = 1.0;
    private double side3 = 1.0;

    public Triangle() {}

    public Triangle(double s1, double s2, double s3, String color, boolean filled) {
        super(color, filled);
        side1 = s1;
        side2 = s2;
        side3 = s3;
    }

    @Override
    public double getArea() {
        double s = getPerimeter() / 2.0;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public void howToColor() {
        System.out.println("Color all three sides");
    }
}

class Square extends GeometricObject {
    private double side = 1.0;

    public Square() {}

    public Square(double side, String color, boolean filled) {
        super(color, filled);
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }
}

public class TestColorable {
    public static void main(String[] args) {
        GeometricObject[] shapes = new GeometricObject[5];
        shapes[0] = new Triangle(3, 4, 5, "red", true);
        shapes[1] = new Square(4, "blue", false);
        shapes[2] = new Triangle(6, 6, 6, "green", true);
        shapes[3] = new Square(2.5, "yellow", true);
        shapes[4] = new Triangle(5, 5, 8, "purple", false);

        for (GeometricObject shape : shapes) {
            System.out.println("Area: " + shape.getArea());
            if (shape instanceof Colorable) {
                ((Colorable) shape).howToColor();
            }
            System.out.println();
        }
    }
}