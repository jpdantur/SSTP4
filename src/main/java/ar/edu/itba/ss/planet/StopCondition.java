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
    protected double lastTime;

    public void setBasicValues(Planet observer, Planet target, Map<Planet, Particle> planets) {
        this.observer = observer;
        this.target = target;
        this.planets = planets;
    }

    abstract boolean canContinue(double currentTime);

    protected Double getDistanceToTarget() {
        return planets.get(observer).getPosition().distance(planets.get(target).getPosition());
    }

    protected void evaluateMinDistance(){
        if(minDistance==null){
            minDistance = getDistanceToTarget();
        }else {
            double tempPosition = getDistanceToTarget();
            if(tempPosition < minDistance){
                minDistance = tempPosition;
            }
        }
    }

    public Double getMinDistance() {
        return minDistance;
    }

    public double getLastTime() {
        return lastTime;
    }
}
