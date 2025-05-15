package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

/**
 * Abstract class representing a Motor Driver device.
 * Extends the Device class and provides functionality for controlling motor speed.
 */
public abstract class MotorDriver extends Device{
    /**
     * Constructor to initialize the MotorDriver with a given protocol.
     *
     * @param protocol The protocol used for communication.
     */
    public MotorDriver(Protocol protocol){
        super(protocol);
    }

    /**
     * Returns the device type of the motor driver.
     *
     * @return The device type ("MotorDriver").
     */
    @Override
    public String getDevType(){
        return "MotorDriver";
    }

    /**
     * Abstract method for setting the motor speed.
     *
     * @param speed The desired motor speed.
     */
    public abstract void setMotorSpeed(int speed);
}
