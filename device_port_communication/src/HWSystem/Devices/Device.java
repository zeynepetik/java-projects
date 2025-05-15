package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

public abstract class Device {
    public enum State{ON, OFF};
    private Protocol protocol;
    protected State state=State.OFF;

    public Device(Protocol pro){
        this.protocol=pro;
    }
    public abstract String getName();
    public abstract String getDevType();
    public abstract void turnON();
    public abstract void turnOFF();
    public abstract State getState();
    public Protocol getProtocol(){
        return protocol;
    }
}
