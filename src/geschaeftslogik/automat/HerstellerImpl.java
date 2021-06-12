package geschaeftslogik.automat;

public class HerstellerImpl implements Hersteller{
    String name;

    //Constructor
    public HerstellerImpl(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
