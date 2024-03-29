package ar.edu.itba.ss.planet;

/**
 * Created by scamisay on 23/04/18.
 */
public class MimCounterCondition extends StopCondition {

    private int limitCounter;
    private int currentCount;
    private Double tempMinDistance;

    public MimCounterCondition(int limitCounter) {
        this.limitCounter = limitCounter;
        start();
    }

    @Override
    boolean canContinue(double currentTime) {
        if(tempMinDistance == null){
            tempMinDistance=getDistanceToTarget();
        }
        evaluateMinDistance(currentTime);
        if(minDistance < tempMinDistance){
            tempMinDistance = minDistance;
            currentCount++;
        }
        return currentCount <= limitCounter;
    }

    @Override
    public void start() {
        super.start();
        currentCount = 0;
    }
}
