package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

/**
 * The abstract class Device represents a generic device in a system.
 * It provides a blueprint for devices with a specific protocol and state.
 * Subclasses must implement the abstract methods to define the behavior
 * of the device.
 */
public abstract class Device {

    /**
     * Enum representing the possible states of a device: ON or OFF.
     */
    public enum State {
        ON, OFF
    }

    /**
     * The communication protocol used by the device.
     */
    private Protocol protocol;

    /**
     * The current state of the device, defaulting to OFF.
     */
    protected State state = State.OFF;

    /**
     * Constructs a Device with the specified protocol.
     *
     * @param pro the protocol used by the device
     */
    public Device(Protocol pro) {
        this.protocol = pro;
    }

    /**
     * Gets the name of the device.
     *
     * @return the name of the device
     */
    public abstract String getName();

    /**
     * Gets the type of the device.
     *
     * @return the type of the device
     */
    public abstract String getDevType();

    /**
     * Turns the device ON.
     */
    public abstract void turnON();

    /**
     * Turns the device OFF.
     */
    public abstract void turnOFF();

    /**
     * Gets the current state of the device.
     *
     * @return the current state of the device
     */
    public abstract State getState();

    /**
     * Gets the protocol used by the device.
     *
     * @return the protocol of the device
     */
    public Protocol getProtocol() {
        return protocol;
    }
}
