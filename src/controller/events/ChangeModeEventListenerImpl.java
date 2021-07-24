package controller.events;

import model.geschaeftslogik.automat.Automat;
import model.geschaeftslogik.automat.Mode;

public class ChangeModeEventListenerImpl implements ChangeModeEventListener{
    Automat automat;

    public ChangeModeEventListenerImpl(Automat automat){
        this.automat = automat;
    }
    @Override
    public void onChangeModeEvent(ChangeModeEvent event) {
        if(event.getText().equalsIgnoreCase(":c")){
            automat.setMode(Mode.Add);
            System.out.println("Changed to add mode");
        }
        else if(event.getText().equalsIgnoreCase(":d")){
            automat.setMode(Mode.Delete);
            System.out.println("Changed to delete mode");
        }
        else if(event.getText().equalsIgnoreCase(":r")){
            automat.setMode(Mode.Show);
            System.out.println("Changed to show mode");
        }
        else if(event.getText().equalsIgnoreCase(":u")){
            automat.setMode(Mode.Change);
            System.out.println("Changed to change mode");
        }
        else if(event.getText().equalsIgnoreCase(":p")){
            automat.setMode(Mode.Persistent);
            System.out.println("Changed to persistent mode");
        }
        else {
            System.out.println("Invalid input..");
        }
    }

    public void setAutomat(Automat automat) {
        this.automat = automat;
    }
}
