package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

public abstract class WirelessIO extends Device {
    public WirelessIO(Protocol protocol){
        super(protocol);
    }
    @Override
    public String getDevType(){
        return "WirelessIO";
    }
    public abstract void sendData(String data);
    public abstract String recvData();    
}
