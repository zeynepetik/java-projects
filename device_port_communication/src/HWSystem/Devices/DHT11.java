package src.HWSystem.Devices;

import src.HWSystem.Protocols.OneWire;

public class DHT11 extends TempSensor{
    public DHT11(){
        super(new OneWire());
    }
    @Override
    public String getName(){
        return "DHT11";
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
