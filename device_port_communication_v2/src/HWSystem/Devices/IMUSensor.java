package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

/**
 * Abstract class representing an IMU sensor.
 * Extends the Sensor class and provides methods for reading acceleration and rotation data.
 */
public abstract class IMUSensor extends Sensor{

    /**
     * Constructor to initialize the IMU sensor with a given protocol.
     *
     * @param protocol The protocol used for communication.
     */
    public IMUSensor(Protocol protocol){
        super(protocol);
    }

    /**
     * Returns the type of the sensor ("IMUSensor").
     *
     * @return The sensor type.
     */
    @Override
    public String getSensType(){
        return "IMUSensor";
    }

    /**
     * Converts sensor data into a string format.
     *
     * @return A formatted string of acceleration and rotation data.
     */
    @Override
    public String data2String(){
        return String.format("Accel: %.2f, Rot: %.2f",getAccel(),getRot());
    }

    /**
     * Reads the acceleration data from the sensor.
     *
     * @return The acceleration value.
     */
    public float getAccel(){
        String readData=getProtocol().read();
        System.out.println(readData);
        return 1.00f;
    }
    
    /**
     * Reads the rotation data from the sensor.
     *
     * @return The rotation value.
     */
    public float getRot(){
        String readData=getProtocol().read();
        System.out.println(readData);
        return 0.50f;
    }
}
