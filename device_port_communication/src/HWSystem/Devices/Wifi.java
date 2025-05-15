package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;
import src.HWSystem.Protocols.SPI;
import src.HWSystem.Protocols.UART;

public class Wifi extends WirelessIO {
    public Wifi(String protocol){
        super(selectProtocol(protocol));
    }
    private static Protocol selectProtocol(String protocol){
        switch (protocol) {
            case "SPI":
                return new SPI();
            case "UART":
                return new UART();
            default:
                throw new IllegalArgumentException("Invalid protocol for Wifi.Try UART or SPI");
        }
    }
    @Override
    public void sendData(String data){
        getProtocol().write(data);
    }
    @Override
    public String recvData(){
       return getProtocol().read();
    }
    @Override
    public String getName(){
        return "Wifi";
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
