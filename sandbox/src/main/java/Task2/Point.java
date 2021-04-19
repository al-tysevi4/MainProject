package Task2;

public class Point {
    private double a;
    private double b;

    public Point(double a1, double b1) {
        this.a = a1;
        this.b = b1;
    }
    public double distance(Point p2) {
        return Math.sqrt((p2.a - this.a) * (p2.a - this.a) + (p2.b - this.b) * (p2.b - this.b));
    }

    @Override
    public String toString() {
        return "Point{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
