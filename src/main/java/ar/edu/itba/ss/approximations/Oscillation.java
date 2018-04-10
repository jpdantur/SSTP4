package ar.edu.itba.ss.approximations;

public abstract class Oscillation {
    private double dt;
    private static final double M = 70;
    private static final double GAMMA = 100;
    private static final double K = 10000;
    private static final double TF = 5;

    public Oscillation(double dt) {
        this.dt = dt;
    }

    public abstract double getNextValue(double t);
}
