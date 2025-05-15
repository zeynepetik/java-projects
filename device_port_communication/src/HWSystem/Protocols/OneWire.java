package src.HWSystem.Protocols;

public class OneWire implements Protocol{
    public OneWire(){
        
    }
    @Override
    public String getProtocolName(){
        return "OneWire";
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
