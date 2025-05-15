package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

/**
 * Class representing an LCD display device.
 * Extends the Display class and provides functionality for an LCD screen.
 */
public class LCD extends Display{

    /**
     * Constructor to initialize the LCD with a given protocol.
     *
     * @param protocol The protocol used for communication.
     */
    public LCD(Protocol protocol){
        super(protocol);
    }

    /**
     * Prints data on the LCD display.
     *
     * @param data The data to be printed.
     */
    @Override
    public void printData(String data){
        getProtocol().write(data);
    }

     /**
     * Returns the name of the display.
     *
     * @return The name of the display ("LCD").
     */
    @Override
    public String getName(){
        return "LCD";
    }

    /**
     * Turns the LCD display ON.
     */
    @Override
    public void turnON(){
        state=State.ON;
        System.out.println(getName()+": Turning ON.");
        getProtocol().write("turnON");
    }

    /**
     * Turns the LCD display OFF.
     */
    @Override
    public void turnOFF(){
        state=State.OFF;
        System.out.println(getName()+": Turning OFF.");
        getProtocol().write("turnOFF");
    }

     /**
     * Returns the current state of the display.
     *
     * @return The current state of the display.
     */
    @Override
    public State getState(){
        return state;
    }
}
