package ar.edu.itba.ss.approximations;

public abstract class Oscillation {
    protected double dt;
    protected static final double M = 70;
    protected static final double GAMMA = 100;
    protected static final double K = 10000;
    protected double lastR=1;
    protected double lastR1=-GAMMA/(2*M);
    protected boolean first = true;

    public Oscillation(double dt) {
        this.dt = dt;
    }

    public abstract double getNextValue();
}
