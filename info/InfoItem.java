package info;
public class InfoItem {
    private String name;
    InfoCall infoCall;
    public InfoItem(String name, InfoCall infoCall) {
        this.name = name;
        this.infoCall = infoCall;
    }
    public String getName() {
        return this.name;
    }
    public void runMethod(){
        this.infoCall.execute();
    }
}