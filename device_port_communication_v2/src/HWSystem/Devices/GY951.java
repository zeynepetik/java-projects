package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

/**
 * Class representing the GY-951 IMU sensor.
 * Inherits from the IMUSensor class and provides specific functionality for the GY-951 sensor.
 */
public class GY951 extends IMUSensor{

    /**
     * Constructor to initialize the GY-951 sensor with a given protocol.
     *
     * @param protocol The protocol used for communication.
     */
    public GY951(Protocol protocol){
        super(protocol);
    }

     /**
     * Returns the name of the sensor.
     *
     * @return The name of the sensor ("GY-951").
     */
    @Override
    public String getName(){
        return "GY-951";
    }
 
    /**
     * Turns the sensor ON and sends a command to the protocol.
     */
    @Override
    public void turnON(){
        state=State.ON;
        System.out.println(getName()+": Turning ON.");
        getProtocol().write("turnON");
    }

    /**
     * Turns the sensor OFF and sends a command to the protocol.
     */
    @Override
    public void turnOFF(){
        state=State.OFF;
        System.out.println(getName()+": Turning OFF.");
        getProtocol().write("turnOFF");
    }

    /**
     * Returns the current state of the sensor.
     *
     * @return The current state of the sensor.
     */
    @Override
    public State getState(){
        return state;
    }
    
}
