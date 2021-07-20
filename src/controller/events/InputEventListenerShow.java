package controller.events;

import geschaeftslogik.automat.Automat;
import geschaeftslogik.automat.Mode;

import java.util.Arrays;

public class InputEventListenerShow implements InputEventListener{
    Automat automat;

    public InputEventListenerShow(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        String[] str = event.getText().split(" ");

        if(automat.getMode()== Mode.Show) {
            if (event.getText().equalsIgnoreCase("hersteller")) {
                System.out.println(Arrays.toString(automat.showHerstellerList()));
            } else if (str[0].equalsIgnoreCase("kuchen")) {
                if (str.length == 1) {
                    System.out.println(automat.showKuchenList());
                } else {
                    System.out.println(automat.showKuchenList(str[1]));
                }
            } else if (str[0].equalsIgnoreCase("allergene")){
                //will be handled by InputEventListenerShowAllergene
            } else {
                System.out.println("Invalid command");
            }
        }
    }
}
