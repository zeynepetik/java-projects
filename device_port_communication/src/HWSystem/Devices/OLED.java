package src.HWSystem.Devices;

import src.HWSystem.Protocols.SPI;

public class OLED extends Display{
    public OLED(){
        super(new SPI());
    }
    @Override
    public void printData(String data){
        getProtocol().write(data);
    }
    @Override
    public String getName(){
        return "OLED";
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
