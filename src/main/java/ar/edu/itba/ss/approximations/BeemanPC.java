package ar.edu.itba.ss.approximations;

import org.apache.commons.math3.util.FastMath;

public class BeemanPC extends Oscillation {

    private double curX = lastR;
    private double curV = lastR1;
    private double curA = -(K / M) * lastR - (GAMMA / M) * lastR1;
    private double lastA = curA;
    private boolean second=true;

    public BeemanPC(double dt) {
        super(dt);
    }

    @Override
    public double getNextValue() {
        if (first) {
            first=false;
            return curX;
        }
        double nextX = curX + curV*dt + 2.0/3*curA*FastMath.pow(dt,2) - 1.0/6*lastA*FastMath.pow(dt,2);
        double predV = curV + 3.0/2*curA*dt - 1.0/2*lastA*dt;
        double nextA = (-K*nextX - GAMMA*predV)/M;
        double nextV = curV +1.0/3*nextA*dt + 5.0/6*curA*dt - 1.0/6*lastA*dt;
        if (!second)
            lastA = curA;
        second = false;
        curA = nextA;
        curV = nextV;
        curX = nextX;
        return curX;
    }
}
