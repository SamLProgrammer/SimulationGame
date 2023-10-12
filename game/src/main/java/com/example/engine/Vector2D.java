package com.example.engine;

public class Vector2D {

    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D v2) {
        x = v2.getX();
        y = v2.getY();
    }

    public Vector2D addVector(Vector2D v2) {
        return new Vector2D(x + v2.getX(), y + v2.getY());
    }

    public Vector2D add(Vector2D v2) {
        this.x += v2.getX();
        this.y += v2.getY();

        return this;
    }

    public Vector2D scaleVector(double factor) {
        return new Vector2D(x * factor, y * factor);
    }

    public Vector2D scale(double factor) {
        x *= factor;
        y *= factor;

        return this;
    }

    public Vector2D substractVector(Vector2D v2) {
        return new Vector2D(x - v2.getX(), y - v2.getY());
    }

    public Vector2D substract(Vector2D v2) {
        x -= v2.getX();
        y -= v2.getY();

        return this;
    }

    public Vector2D normalized() {
        double length = getLength();
        return new Vector2D(x/length, y/length);
    }
    
    public Vector2D normalize() {
        double length = getLength();
        x /= length;
        y /= length;
        return this;
    }

    public double getLengthSquared() {
        return (x*x) + (y*y);
    }

    public double getLength() {
        return Math.sqrt(getLengthSquared());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}
