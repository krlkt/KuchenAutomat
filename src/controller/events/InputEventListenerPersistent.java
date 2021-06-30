package controller.events;

import geschaeftslogik.automat.Automat;
import geschaeftslogik.automat.Mode;
import geschaeftslogik.persistence.JBP;
import geschaeftslogik.persistence.JOS;

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
                Automat load = jos.load();
                automat.fillAutomat(load);
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
                jbp.load();
                System.out.println("successfully loaded machine");
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}
