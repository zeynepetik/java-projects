package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

/**
 * Abstract class representing a Sensor device.
 * Extends the Device class and provides functionality for managing sensor data.
 */
public abstract class Sensor extends Device{

    /**
     * Constructor to initialize the Sensor with a given protocol.
     *
     * @param protocol The protocol used for communication.
     */
    public Sensor(Protocol protocol){
        super(protocol);
    }

     /**
     * Returns the device type of the sensor.
     *
     * @return The sensor type.
     */
    @Override
    public String getDevType(){
        return getSensType()+ "Sensor";
    }

     /**
     * Abstract method for getting the sensor type.
     *
     * @return The type of the sensor.
     */
    public abstract String getSensType();

    /**
     * Abstract method for converting sensor data into a string.
     *
     * @return A string representation of sensor data.
     */
    public abstract String data2String();
}
