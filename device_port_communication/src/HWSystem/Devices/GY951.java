package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;
import src.HWSystem.Protocols.SPI;
import src.HWSystem.Protocols.UART;

public class GY951 extends IMUSensor{
    public GY951(String protocol){
        super(selectProtocol(protocol));
    }
    private static Protocol selectProtocol(String protocol){
        switch(protocol){
            case "SPI":
                return new SPI();
            case "UART":
                return new UART();
            default:
                throw new IllegalArgumentException("Invalid protocol for GY-951.Try SPI or UART");
        }
    }
    @Override
    public String getName(){
        return "GY-951";
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
