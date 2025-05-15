package src.HWSystem.Devices;

import src.HWSystem.Protocols.UART;

public class Bluetooth extends WirelessIO{
    public Bluetooth(){
        super(new UART());
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
        return "Bluetooth";
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
