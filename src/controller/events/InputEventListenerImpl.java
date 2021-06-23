package controller.events;

import geschaeftslogik.automat.Automat;
import geschaeftslogik.automat.Hersteller;
import geschaeftslogik.automat.HerstellerImpl;
import geschaeftslogik.automat.Mode;

import java.util.Arrays;

public class InputEventListenerImpl implements InputEventListener {
    Automat automat;
    public InputEventListenerImpl(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        String[] str = event.getText().split(" ");

        if(automat.getMode()== Mode.Add){
            if(str.length == 1) {
                if (automat.addHersteller(str[0])) { System.out.println("Successfully added manufacturer " + str[0] + " into machine");
                }else { System.out.println("error occured. Hersteller was not added into the machine"); }
            }
            else{
                if(str.length > 5){
                    
                }
            }
        }
    }
}
