package ar.edu.itba.ss.approximations;

import org.apache.commons.math3.util.FastMath;

public class GearPC extends Oscillation {

    //Order 5 constants (Slide 28)
    private static final double ALPHA0 = 3.0 / 16;
    private static final double ALPHA1 = 251.0 / 360;
    private static final double ALPHA2 = 1;
    private static final double ALPHA3 = 11.0 / 18;
    private static final double ALPHA4 = 1.0 / 6;
    private static final double ALPHA5 = 1.0 / 60;

    //Initial Values (Slide 29 --Modified for dampened oscillator, check Slide 35--)
    private double lastR2 = -(K / M) * lastR - (GAMMA / M) * lastR1;
    private double lastR3 = -(K / M) * lastR1 - (GAMMA / M) * lastR2;
    private double lastR4 = -(K / M) * lastR2 - (GAMMA / M) * lastR3;
    private double lastR5 = -(K / M) * lastR3 - (GAMMA / M) * lastR4;

    public GearPC(double dt) {
        super(dt);
    }

    public double getNextValue() {
        if (first) {
            first = false;
            return lastR;
        }

        //Predict Values (Slide 24)
        double r5p = lastR5;
        double r4p = lastR4 + lastR5 * dt;
        double r3p = lastR3 + lastR4 * dt + lastR5 * (FastMath.pow(dt, 2) / 2);
        double r2p = lastR2 + lastR3 * dt + lastR4 * (FastMath.pow(dt, 2) / 2) + lastR5 * (FastMath.pow(dt, 3) / 6);
        double r1p = lastR1 + lastR2 * dt + lastR3 * (FastMath.pow(dt, 2) / 2) + lastR4 * (FastMath.pow(dt, 3) / 6) +
                lastR5 * (FastMath.pow(dt, 4) / 24);
        double rp = lastR + lastR1 * dt + lastR2 * (FastMath.pow(dt, 2) / 2) + lastR3 * (FastMath.pow(dt, 3) / 6) +
                lastR4 * (FastMath.pow(dt, 4) / 24) + lastR5 * (FastMath.pow(dt, 5) / 120);

        //Calculate DR2 (Slide 25)
        double r2 = (-K / M) * rp - (GAMMA / M) * r1p;
        double da = r2 - r2p;
        double dR2 = da * FastMath.pow(dt, 2) / 2;


        //Correct Values (Slide 26)
        lastR = rp + ALPHA0 * dR2;
        lastR1 = r1p + ALPHA1 * dR2 / dt;
        lastR2 = r2p + ALPHA2 * dR2 * 2 / FastMath.pow(dt, 2);
        lastR3 = r3p + ALPHA3 * dR2 * 6 / FastMath.pow(dt, 3);
        lastR4 = r4p + ALPHA4 * dR2 * 24 / FastMath.pow(dt, 4);
        lastR5 = r5p + ALPHA5 * dR2 * 120 / FastMath.pow(dt, 5);
        return lastR;
    }
}
