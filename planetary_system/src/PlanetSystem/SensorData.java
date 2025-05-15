package src.PlanetSystem;

/**
 * Stores and manages environmental sensor data such as temperature, humidity, pressure, and radiation.
 */
public class SensorData{
    private double temp, pres, hum, rad;

    /**
     * Constructs a new SensorData object with specified environmental values.
     * @param temp temperature (must be >= 0.0)
     * @param hum humidity (0.0 to 100.0)
     * @param pres pressure
     * @param rad radiation (must be >= 0.0)
     */
    public SensorData(double temp, double hum, double pres, double rad){
        if(hum<0.0 || hum>100.0){
            throw new IllegalArgumentException("Humidity can be between 0.0 and 100.0. \n");
        }
        if(rad<0.0){
            throw new IllegalArgumentException("Radiation can not be negative. \n");
        }
        if(temp<0.0){
            throw new IllegalArgumentException("Temperature can not be lower than absolute temperature 0.0 Kelvin. \n");
        }
        this.temp = temp;
        this.hum = hum;
        this.pres = pres;
        this.rad = rad;
    }

     /**
     * Constructs a default SensorData object with all values set to 0.
     */
    public SensorData(){
        this.temp = 0.0;
        this.hum = 0.0;
        this.pres = 0.0;
        this.rad = 0.0;
    }

    /** @return temperature */
    public double getTemp(){
        return this.temp;
    }

    /** @return humidity */
    public double getHum(){
        return this.hum;
    }

    /** @return pressure */
    public double getPres(){
        return this.pres;
    }

    /** @return radiation */
    public double getRad(){
        return this.rad;
    }

     /** @param temp sets the temperature */
    public void setTemp(double temp){
        this.temp = temp;
    }

    /** @param hum sets the humidity */
    public void setHum(double hum){
        this.hum = hum;
    }
    
    /** @param pres sets the pressure */
    public void setPres(double pres){
        this.pres = pres;
    }

    /** @param rad sets the radiation */
    public void setRad(double rad){
        this.rad = rad;
    }
}