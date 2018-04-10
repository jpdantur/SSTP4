package ar.edu.itba.ss.approximations;

public class Oscillation {
    private double dt;
    private static final double M = 70;
    private static final double GAMMA = 100;
    private static final double K = 10000;
    private static final double TF = 5;
    private IAlgorithm algorithm;

    public Oscillation(double dt, IAlgorithm algorithm) {
        this.dt = dt;
        this.algorithm = algorithm;
    }
}
