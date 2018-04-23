package ar.edu.itba.ss.planet;

import java.util.Map;

/**
 * Created by scamisay on 22/04/18.
 */
public class ProgrammedTime extends StopCondition {

    private double timeLimit;

    public ProgrammedTime(double timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Override
    public boolean canContinue(double currentTime) {
        lastTime = currentTime;
        evaluateMinDistance();
        return currentTime < timeLimit;
    }
}
