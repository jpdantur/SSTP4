package ar.edu.itba.ss.planet;

/**
 * Created by scamisay on 22/04/18.
 */
public class Observer extends StopCondition{

    private int visitLimits;
    private int currentVisits = 0;
    private double obsevationLimit;
    private boolean inObservation = false;

    public Observer(int visitLimits, double obsevationLimit) {
        this.visitLimits = visitLimits;
        this.obsevationLimit = obsevationLimit;
    }

    @Override
    boolean canContinue(double currentTime) {
        lastTime = currentTime;
        evaluateMinDistance();

        if(isTargetObservable() && !inObservation){
            inObservation = true;
            currentVisits++;
        }else if(!isTargetObservable() && inObservation){
            inObservation = false;
            if(currentVisits == visitLimits){
                return false;
            }
        }
        return true;
    }

    private boolean isTargetObservable() {
        return getDistanceToTarget() <= obsevationLimit;
    }
}
