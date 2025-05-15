package src.HWSystem.Protocols;

public class SPI implements Protocol{
    public SPI(){
        
    }
    @Override
    public String getProtocolName(){
        return "SPI";
    }
    @Override
    public String read(){
        return "<"+getProtocolName()+">"+":Reading.";
    }
    @Override
    public void write(String data){
        System.out.printf("<%s>: Writing \"%s\".%n", getProtocolName(), data);
    }
}