package org.stepik.java.shapes;

public class Triangle extends Shape {

    private final Point a;
    private final Point b;
    private final Point c;

    public Triangle(Point a, Point b, Point c, Color color) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getArea() {
        // cross-like product
        Point vecA = new Point(c.getX() - a.getX(), c.getY() - a.getY());
        Point vecB = new Point(b.getX() - a.getX(), b.getY() - a.getY());

        // Parallelogram area, for triangle should be half of it
        double crossProduct = vecA.getX() * vecB.getY() - vecA.getY() * vecB.getX();
        double signedArea = Math.abs(crossProduct);
        return signedArea / 2;
    }


    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", color=" + getColor() +
                "}";
    }
}
