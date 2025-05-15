package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

/**
 * Abstract class representing a Display device.
 * Extends the Device class and implements methods for managing a display.
 */
public abstract class Display extends Device{

     /**
     * Constructor to initialize the Display with a given protocol.
     *
     * @param protocol The protocol used for communication.
     */
    public Display(Protocol protocol){
        super(protocol);
    }

     /**
     * Returns the device type of the display.
     *
     * @return The type of the device ("Display").
     */
    @Override
    public String getDevType(){
        return "Display";
    }

    /**
     * Abstract method for printing data on the display.
     *
     * @param data The data to be printed on the display.
     */
    public abstract void printData(String data);
}
