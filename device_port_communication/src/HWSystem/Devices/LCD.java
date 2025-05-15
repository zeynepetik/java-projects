package src.HWSystem.Devices;

import src.HWSystem.Protocols.I2C;

public class LCD extends Display{
    public LCD(){
        super(new I2C());
    }
    @Override
    public void printData(String data){
        getProtocol().write(data);
    }
    @Override
    public String getName(){
        return "LCD";
    }
    @Override
    public void turnON(){
        state=State.ON;
        System.out.println(getName()+" is Turning On");
        getProtocol().write("Turn ON");
    }
    @Override
    public void turnOFF(){
        state=State.OFF;
        System.out.println(getName()+" is Turning Off");
        getProtocol().write("Turn OFF");
    }
    @Override
    public State getState(){
        return state;
    }
}
