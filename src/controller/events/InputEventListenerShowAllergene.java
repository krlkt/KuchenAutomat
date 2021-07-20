package controller.events;

import geschaeftslogik.automat.Automat;
import geschaeftslogik.automat.Mode;

public class InputEventListenerShowAllergene implements InputEventListener{
    Automat automat;

    public InputEventListenerShowAllergene(Automat automat){
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        String[] str = event.getText().split(" ");

        if (automat.getMode() == Mode.Show) {
            if (str[0].equalsIgnoreCase("allergene")) {
                if (str.length > 1) {
                    if (str[1].equalsIgnoreCase("i")) {
                        System.out.println(automat.showAllergene());
                    } else if (str[1].equalsIgnoreCase("e")) {
                        System.out.println(automat.showNotIncludedAllergene());
                    } else {
                        System.out.println("Invalid command. After allergene type i for included allergies and e for not included");
                    }
                } else {
                    System.out.println("Invalid input length. After allergene type i for included allergies and e for not included");
                }
            }
        }
    }
}
