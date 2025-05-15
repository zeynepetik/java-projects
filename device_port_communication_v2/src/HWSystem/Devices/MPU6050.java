package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

/**
 * Class representing the MPU6050 IMU sensor.
 * Inherits from the IMUSensor class and provides specific functionality for the MPU6050 sensor.
 */
public class MPU6050 extends IMUSensor{

    /**
     * Constructor to initialize the MPU6050 sensor with a given protocol.
     *
     * @param protocol The protocol used for communication.
     */
    public MPU6050(Protocol protocol){
        super(protocol);
    }

     /**
     * Returns the name of the sensor.
     *
     * @return The name of the sensor ("MPU6050").
     */
    @Override
    public String getName(){
        return "MPU6050";
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
