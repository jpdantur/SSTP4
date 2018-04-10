package ar.edu.itba.ss.approximations;
import org.apache.commons.math3.*;

public class GearPC extends Oscillation {

    private static final double alfa0 = 3.0/16;
    private static final double alfa1 = 251.0/360;
    private static final double alfa2 = 1;
    private static final double alfa3 = 11.0/18;
    private static final double alfa4 = 1.0/6;
    private static final double alfa5 = 1.0/60;

    public GearPC(double dt) {
        super(dt);
    }

    public double getNextValue(double t) {
        return 0;
    }
}
