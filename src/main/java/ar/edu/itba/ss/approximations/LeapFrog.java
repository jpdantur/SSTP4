package ar.edu.itba.ss.approximations;

/**
 * Created by scamisay on 16/04/18.
 */
public class LeapFrog extends Oscillation{

    private double curX = lastR;
    private double curV = lastR1;
    private double curA = -(K / M) * lastR - (GAMMA / M) * lastR1;
    private double lastA = curA;

    public LeapFrog(double dt) {
        super(dt);
    }

    @Override
    public double getNextValue() {
        if (first) {
            first=false;
            return curX;
        }

        double nextV = curV + dt*curA;
        double nextX = curX + dt*nextV;
        double nextA = (-K*nextX - GAMMA*nextV)/M;

        curA = nextA;
        curV = nextV;
        curX = nextX;
        return curX;
    }
}
