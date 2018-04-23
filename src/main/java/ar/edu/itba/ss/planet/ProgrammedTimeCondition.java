package ar.edu.itba.ss.planet;

/**
 * Created by scamisay on 22/04/18.
 */
public class ProgrammedTimeCondition extends StopCondition {

    private double timeLimit;

    public ProgrammedTimeCondition(double timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Override
    public boolean canContinue(double currentTime) {
        lastTime = currentTime;
        evaluateMinDistance(currentTime);
        return currentTime < timeLimit;
    }
}
