package src.HWSystem.Devices;

import src.HWSystem.Protocols.Protocol;

public abstract class Display extends Device{
    public Display(Protocol protocol){
        super(protocol);
    }
    @Override
    public String getDevType(){
        return "Display";
    }
    public abstract void printData(String data);
}
