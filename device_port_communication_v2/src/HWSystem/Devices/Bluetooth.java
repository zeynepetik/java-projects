package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;
/**
 * The {@code Bluetooth} class represents a Bluetooth device that extends the
 * {@code WirelessIO} class. It provides functionality to send and receive data
 * using a specified protocol, as well as to manage the device's state (ON/OFF).
 * 
 * <p>This class is part of the {@code src.HWSystem.Devices} package and relies
 * on the {@code Protocol} interface for communication.
 * 
 * @see src.HWSystem.Protocols.Protocol
 */
public class Bluetooth extends WirelessIO {

    /**
     * Constructs a {@code Bluetooth} device with the specified protocol.
     * 
     * @param protocol the protocol to be used for communication
     */
    public Bluetooth(Protocol protocol) {
        super(protocol);
    }

    /**
     * Sends data using the associated protocol.
     * 
     * @param data the data to be sent
     */
    @Override
    public void sendData(String data) {
        getProtocol().write(data);
    }

    /**
     * Receives data using the associated protocol.
     * 
     * @return the data received
     */
    @Override
    public String recvData() {
        return getProtocol().read();
    }

    /**
     * Returns the name of the device.
     * 
     * @return the name of the device, which is "Bluetooth"
     */
    @Override
    public String getName() {
        return "Bluetooth";
    }

    /**
     * Turns the Bluetooth device ON and sends a "turnON" signal using the protocol.
     */
    @Override
    public void turnON() {
        state = State.ON;
        System.out.println(getName() + ": Turning ON.");
        getProtocol().write("turnON");
    }

    /**
     * Turns the Bluetooth device OFF and sends a "turnOFF" signal using the protocol.
     */
    @Override
    public void turnOFF() {
        state = State.OFF;
        System.out.println(getName() + ": Turning OFF.");
        getProtocol().write("turnOFF");
    }

    /**
     * Returns the current state of the Bluetooth device.
     * 
     * @return the current state of the device, either {@code State.ON} or {@code State.OFF}
     */
    @Override
    public State getState() {
        return state;
    }
}



