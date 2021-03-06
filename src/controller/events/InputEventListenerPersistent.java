package controller.events;

import model.geschaeftslogik.automat.Automat;
import model.geschaeftslogik.automat.Mode;
import model.geschaeftslogik.persistence.JBP;
import model.geschaeftslogik.persistence.JOS;

public class InputEventListenerPersistent implements InputEventListener{
    Automat automat;

    public InputEventListenerPersistent(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        String[] str = event.getText().split(" ");

        if(automat.getMode()== Mode.Persistent) {
            if (event.getText().equalsIgnoreCase("savejos")) {
                JOS jos = new JOS();
                jos.save(automat);
                System.out.println("successfully saved to items.ser");
            } else if (event.getText().equalsIgnoreCase("loadjos")) {
                JOS jos = new JOS();
                Automat loadedAutomat = jos.load();
                automat.fillAutomat(loadedAutomat);
                System.out.println("successfully loaded machine");
            } else if (event.getText().equalsIgnoreCase("savejbp")) {
                JBP jbp = new JBP();
                try {
                    jbp.save(automat);
                    System.out.println("successfully saved to JBP.xml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (event.getText().equalsIgnoreCase("loadjbp")) {
                JBP jbp = new JBP();
                Automat loadedAutomat = jbp.load();
                automat.fillAutomat(loadedAutomat);
                System.out.println("successfully loaded machine");
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}
