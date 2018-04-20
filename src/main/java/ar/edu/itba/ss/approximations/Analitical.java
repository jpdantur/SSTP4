package ar.edu.itba.ss.approximations;

import org.apache.commons.math3.util.FastMath;

/**
 * Created by scamisay on 16/04/18.
 */
public class Analitical extends Oscillation{

    private double curX = lastR;
    private double curV = lastR1;
    private double curA = -(K / M) * lastR - (GAMMA / M) * lastR1;
    private double lastA = curA;
    private double curT;

    public Analitical(double dt) {
        super(dt);
        curT = dt;
    }

    @Override
    public double getNextValue() {
        double A = 1;//TODO que es A????
        double powBase = (K/M) - FastMath.pow(GAMMA,2)/(4*FastMath.pow(M,2));
        curX = A* FastMath.exp(-1*(GAMMA/(2*M))*curT)
                *FastMath.cos(FastMath.pow(powBase,0.5)*curT);
        curT += dt;
        return curX;
    }
}
