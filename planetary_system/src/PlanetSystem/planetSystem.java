package src.PlanetSystem;

import java.util.List;
import java.util.Stack;

/**
 * Represents a planetary system containing a central star and various planets and satellites.
 */
public class planetSystem {
    Tree planetarySystem;

     /**
     * Constructs a new planetary system with a central star.
     * @param starName the name of the central star
     * @param temp the temperature of the star
     * @param pres the pressure value of the star
     * @param hum the humidity level (must be 0.0 for stars)
     * @param rad the radiation level
     */
    public planetSystem(String starName,double temp,double pres,double hum,double rad){
        createSystem(starName,temp, pres, hum, rad);
    }

     /**
     * Creates the root of the planetary system (the star).
     * @param starName the name of the star
     * @param temp the temperature
     * @param pres the pressure
     * @param hum the humidity (must be 0.0)
     * @param rad the radiation
     */
    private void createSystem(String starName,double temp,double pres,double hum,double rad){
        if(hum!=0.0){
            throw new IllegalArgumentException("Humidity o Star cannot be 0.0 \n");
        }
        if(rad==0.0){
            throw new IllegalArgumentException("Radiation cannot be 0.0 \n");
        }
        planetarySystem=new Tree(starName,"Star" ,temp, rad, hum, pres);
    }

    /**
     * Adds a planet to the planetary system under a given parent node.
     * @param planet name of the planet
     * @param parentName name of the parent node
     * @param temp temperature
     * @param press pressure
     * @param hum humidity
     * @param rad radiation
     */
    public void addPlanet(String planet, String parentName,double temp, double press,double hum, double rad){
        if(planetarySystem.isEmpty()){
            throw new IllegalArgumentException("Planetary system does not exist. \n");
        }
        boolean sucess= planetarySystem.addPlanet(planet, parentName, temp, press, hum, rad);
        if(sucess){
            System.out.println("Planet "+ planet+" is added \n");
        }
        else{
            System.err.println("Planet "+ planet+" could not be added \n");
        }
    }

    /**
     * Adds a satellite to the planetary system under a given parent node.
     * @param satellite name of the satellite
     * @param parentName name of the parent node
     * @param temp temperature
     * @param press pressure
     * @param hum humidity
     * @param rad radiation
     */
    public void addSatellite(String satellite, String parentName, double temp, double press, double hum, double rad){
        if(planetarySystem.isEmpty()){
            throw new IllegalArgumentException("Planetary system does not exist. \n");
        }
        boolean sucess= planetarySystem.addSatellite(satellite, parentName, temp, hum, rad,press);
        if(sucess){
            System.out.println("Satellite "+ satellite+" is added \n");
        }
        else{
            System.err.println("Satellite "+ satellite+" could not be added \n");
        }
    }

    /**
     * Finds and prints planets or satellites with radiation above a specified threshold.
     * @param threshold radiation level threshold
     */
    public void findRadiationAnomalies(double threshold){
        if(planetarySystem.isEmpty()){
            throw new IllegalArgumentException("Planetary system does not exist. \n");
        }
        List<TreeNode> anomalies= planetarySystem.getRadiationAnomalies(threshold);
        if(anomalies.isEmpty()){
            System.out.println("No anomalies found \n");
        }
        else{
            System.out.println("Anomalies found ");
            for(TreeNode anomaly: anomalies){
                System.out.println(anomaly.getName());
            }
        }
    }

    /**
     * Retrieves and prints the path from the root to the specified node.
     * @param planetName name of the node to find the path to
     */
    public void getPathTo(String planetName){
        if(planetarySystem.isEmpty()){
            throw new IllegalArgumentException("Planetary system does not exist. \n");
        }
        Stack<String> path= planetarySystem.getPathTo(planetName);
        if(path.isEmpty()){
            System.err.println("Could not get the path to the palnet "+ planetName+"\n");
        }
        else{
            while(!path.isEmpty()){
                String node=path.pop();
                System.out.println(node);
            }
        }
    }

    /**
     * Prints the entire mission report for the planetary system.
     */
    public void printMissionReport(){
        if(planetarySystem.isEmpty()){
            throw new IllegalArgumentException("Planetary system does not exist. \n");
        }
        planetarySystem.printMissionReport();
    }

     /**
     * Prints the mission report starting from the specified node.
     * @param nodeName name of the node to start the report from
     */
    public void printMissionReport(String nodeName){
        if(planetarySystem.isEmpty()){
            throw new IllegalArgumentException("Planetary system does not exist. \n");
        }
        planetarySystem.printMissionReport(nodeName);
    }
}
