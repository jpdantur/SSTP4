package ar.edu.itba.ss.planet;

/**
 * Created by scamisay on 22/04/18.
 */
public class ObserverCondition extends StopCondition{

    private int visitLimits;
    private int currentVisits = 0;
    private double obsevationLimit;
    private boolean inObservation = false;
    private double maxDistanceAllowed;

    public ObserverCondition(int visitLimits, double obsevationLimit, double maxDistanceProportion) {
        this.visitLimits = visitLimits;
        this.obsevationLimit = obsevationLimit;
        maxDistanceAllowed = obsevationLimit*maxDistanceProportion;
    }

    @Override
    boolean canContinue(double currentTime) {
        lastTime = currentTime;
        evaluateMinDistance(currentTime);

        if(getDistanceToTarget() >= maxDistanceAllowed){
            return false;
        }

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

    @Override
    void start() {

    }

    private boolean isTargetObservable() {
        return getDistanceToTarget() <= obsevationLimit;
    }
}
