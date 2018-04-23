package ar.edu.itba.ss.planet;

import java.util.Map;

/**
 * Created by scamisay on 22/04/18.
 */
public abstract class StopCondition {

    protected Planet observer;
    protected Planet target;
    protected Map<Planet, Particle> planets;
    protected Double minDistance;
    protected Double timeForMinDistance;
    protected Double lastTime;
    protected Planet secondTarget;
    protected Double minDistanceForTwoTargets;
    protected Double timeForMinDistanceForTwoTargets;

    public void setBasicValues(Planet observer, Planet target, Planet secondTarget, Map<Planet, Particle> planets) {
        this.observer = observer;
        this.target = target;
        this.secondTarget = secondTarget;
        this.planets = planets;
    }

    abstract boolean canContinue(double currentTime);

    public void start(){
        minDistance = null;
        timeForMinDistance = null;
        minDistanceForTwoTargets = null;
        timeForMinDistanceForTwoTargets = null;
    }

    protected Double getDistanceToTarget() {
        return planets.get(observer).getPosition().distance(planets.get(target).getPosition());
    }

    protected Double getDistanceToSecondTarget() {
        return planets.get(observer).getPosition().distance(planets.get(secondTarget).getPosition());
    }

    protected void evaluateMinDistance(double currentTime){
        if(minDistance==null){
            minDistance = getDistanceToTarget();
            timeForMinDistance = currentTime;
        }else {
            double tempPosition = getDistanceToTarget();
            if(tempPosition < minDistance){
                minDistance = tempPosition;
                timeForMinDistance = currentTime;
            }
        }

        if(minDistanceForTwoTargets==null){
            minDistanceForTwoTargets = getDistanceToTarget()+getDistanceToSecondTarget();
            timeForMinDistanceForTwoTargets = currentTime;
        }else {
            double tempPosition = getDistanceToTarget()+getDistanceToSecondTarget();
            if(tempPosition < minDistanceForTwoTargets){
                minDistanceForTwoTargets = tempPosition;
                timeForMinDistanceForTwoTargets = currentTime;
            }
        }
    }

    public Double getMinDistance() {
        return minDistance;
    }

    public double getLastTime() {
        return lastTime;
    }

    public double getTimeForMinDistance() {
        return timeForMinDistance;
    }

    public double getCurrentDistance() {
        return getDistanceToTarget();
    }

    public Double getMinDistanceForTwoTargets() {
        return minDistanceForTwoTargets;
    }

    public double getTimeForMinDistanceForTwoTargets() {
        return timeForMinDistanceForTwoTargets;
    }
}
