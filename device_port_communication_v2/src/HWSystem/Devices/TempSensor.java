package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

/**
 * Abstract class representing a Temperature sensor.
 * Inherits from Sensor and provides functionality for reading temperature data.
 */
public abstract class TempSensor extends Sensor{

    /**
     * Constructor to initialize the TempSensor with a given protocol.
     *
     * @param protocol The protocol used for communication.
     */
    public TempSensor(Protocol protocol){
        super(protocol);
    }

    /**
     * Returns the sensor type of the temperature sensor.
     *
     * @return The sensor type ("TempSensor").
     */
    @Override
    public String getSensType(){
        return "TempSensor";
    }

    /**
     * Converts temperature sensor data into a string format.
     *
     * @return A formatted string with the temperature data.
     */
    @Override
    public String data2String(){
        return String.format("Temperature: %.2fC", getTemp());
    }

    /**
     * Reads the temperature data from the sensor.
     *
     * @return The temperature value.
     */
    public float getTemp(){
        String readData=getProtocol().read();
        System.out.println(readData);
        return 24.00f;
        
    }
    
}
