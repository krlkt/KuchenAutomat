package model.geschaeftslogik.automat;

import java.io.Serializable;

public class HerstellerImpl implements Hersteller, Serializable {
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
