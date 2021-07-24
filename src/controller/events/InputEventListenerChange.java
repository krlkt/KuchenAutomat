package controller.events;

import model.geschaeftslogik.automat.Automat;
import model.geschaeftslogik.automat.Mode;

import java.util.Date;

public class InputEventListenerChange implements InputEventListener{
    Automat automat;

    public InputEventListenerChange(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if(automat.getMode()== Mode.Change){
            String[] str = event.getText().split(" ");

            if(str.length == 1){
                if(str[0].matches("[0-9]+")) {
                    int fachnummer = Integer.parseInt(str[0]);
                    if(automat.getFaecher(fachnummer)!=null) {
                        automat.getKuchen(fachnummer).setInspektionsdatum(new Date());
                        System.out.println("updated date of inspection");
                    }else{
                        System.out.println("fachnummer " + fachnummer + " is empty..");
                    }
                }else {
                    System.out.println("invalid input. type in locker number to update date of inspection");
                }
            }else{
                System.out.println("Invalid input");
            }
        }
    }
}
