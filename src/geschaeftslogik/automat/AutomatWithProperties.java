package geschaeftslogik.automat;

import javafx.beans.property.*;

public final class AutomatWithProperties {
    private Automat automat = new Automat(1000);
    private ObjectProperty herstellerProperty=new SimpleObjectProperty();
    private ObjectProperty kuchenProperty=new SimpleObjectProperty();

    private AutomatWithProperties(){
    }

    private static final AutomatWithProperties instance = new AutomatWithProperties();
    public static AutomatWithProperties getInstance() { return instance; }
}
