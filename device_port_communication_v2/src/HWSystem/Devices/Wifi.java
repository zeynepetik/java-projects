package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

/**
 * Class representing a Wifi device for wireless communication.
 * Inherits from WirelessIO and implements methods for sending and receiving data.
 */
public class Wifi extends WirelessIO {

    /**
     * Constructor to initialize the Wifi device with a given protocol.
     *
     * @param protocol The protocol used for communication.
     */
    public Wifi(Protocol protocol){
        super(protocol);
    }

     /**
     * Sends data over the Wifi connection.
     *
     * @param data The data to be sent.
     */
    @Override
    public void sendData(String data){
        getProtocol().write(data);
    }

    /**
     * Receives data over the Wifi connection.
     *
     * @return The received data.
     */
    @Override
    public String recvData(){
       return getProtocol().read();
    }

    /**
     * Returns the name of the Wifi device.
     *
     * @return The name of the device ("Wifi").
     */
    @Override
    public String getName(){
        return "Wifi";
    }

    /**
     * Turns the Wifi device ON.
     */
    @Override
    public void turnON(){
        state=State.ON;
        System.out.println(getName()+": Turning ON.");
        getProtocol().write("turnON");
    }

    /**
     * Turns the Wifi device OFF.
     */
    @Override
    public void turnOFF(){
        state=State.OFF;
        System.out.println(getName()+": Turning OFF.");
        getProtocol().write("turnOFF");
    }

    /**
     * Returns the current state of the Wifi device.
     *
     * @return The current state of the device.
     */
    @Override
    public State getState(){
        return state;
    }  
}
