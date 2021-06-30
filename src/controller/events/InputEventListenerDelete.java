package controller.events;

import geschaeftslogik.automat.Automat;
import geschaeftslogik.automat.Mode;

public class InputEventListenerDelete implements InputEventListener{
    Automat automat;

    public InputEventListenerDelete(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        String[] str = event.getText().split(" ");

        if(automat.getMode()== Mode.Delete){
            if(str.length == 1){
                if(str[0].matches("[0-9]+")){
                    try {
                        automat.eraseKuchen(Integer.parseInt(str[0]));
                        System.out.println("successfully erased cake on locker " + str[0]);
                    } catch (Exception e) {
                        System.out.println("failed to erase cake from locker "+ str[0]);
                    }
                }else {
                    if(automat.removeHersteller(str[0])){
                        System.out.println("successfully removed hersteller " + str[0] + " from machine");
                    }else {
                        System.out.println("failed to remove hersteller "+ str[0] + " from machine");
                    }
                }
            }else {
                System.out.println("Invalid input");
            }
        }
    }
}
