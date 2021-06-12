package ui.controller;

import geschaeftslogik.automat.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;

public class ViewModel {
    private Automat automat = new Automat(1000);

    ObservableList<String> hersteller = FXCollections.observableArrayList();

    @FXML ListView<String> herstellerListView = new ListView<>(hersteller);

    ObservableList<String> kuchen = FXCollections.observableArrayList();

    @FXML ListView<String> kuchenList = new ListView<>(kuchen);

    @FXML private TextField addHerstellerField;
    @FXML private TextField removeHerstellerField;
    @FXML private TextField creamField;
    @FXML private TextField fruitField;
    @FXML private TextField fachNummerField;

    @FXML private Label warningLabel;

    @FXML private RadioButton rad_obstkuchen;
    @FXML private RadioButton rad_kremkuchen;
    @FXML private RadioButton rad_obsttorte;

    @FXML private void initialize() {
        radioButtonSetup();
    }

    private void radioButtonSetup() {
        ToggleGroup kuchenRadio = new ToggleGroup();
        rad_obstkuchen.setToggleGroup(kuchenRadio);
        rad_kremkuchen.setToggleGroup(kuchenRadio);
        rad_obsttorte.setToggleGroup(kuchenRadio);
        kuchenRadio.selectToggle(rad_obstkuchen);
    }

    //Hersteller
    public void addHerstellerClicked(){
        String name = addHerstellerField.getText();
        if(automat.addHersteller(name)) {
            herstellerListView.getItems().add(name);
            System.out.println("added manufacturer to machine");
        }else{
            System.out.println("manufacturer already exist");
            warningLabel.setText("manufacturer already exist");
        }
    }

    public void removeSelectedHerstellerClicked(){
        int selectedID = herstellerListView.getSelectionModel().getSelectedIndex();
        String toRemove = herstellerListView.getSelectionModel().getSelectedItem();
        if(automat.removeHersteller(toRemove)) {
            System.out.println("selected hersteller removed");
            herstellerListView.getItems().remove(selectedID);
        }else {
            System.out.println("failed to remove manufacturer, cake from this manufacturer still exists");
            warningLabel.setText("failed to remove manufacturer, cake from this manufacturer still exists");
        }
    }

    public void removeHerstellerClicked() throws Exception {
        String name = removeHerstellerField.getText();
        int gesuchtIndex = getHerstellerIndex(name);
        if(automat.removeHersteller(name)){
            System.out.println("removed Hersteller from machine");
            herstellerListView.getItems().remove(gesuchtIndex);
        }else {
            System.out.println("failed to remove manufacturer, cake from this manufacturer still exists");
            warningLabel.setText("failed to remove manufacturer, cake from this manufacturer still exists");
        }
    }

    private int getHerstellerIndex(String name) throws Exception {
        for(int i = 0; i<herstellerListView.getItems().size(); i++){
            if(herstellerListView.getItems().get(i).equalsIgnoreCase(name)){ return i; }
        }
        throw new Exception("hersteller not found");
    }

    //Kuchen
    public void addKuchenClicked() throws Exception {

        String name = herstellerListView.getSelectionModel().getSelectedItem();
        Hersteller hersteller = new HerstellerImpl(name);
        if(rad_obsttorte.isSelected()){
            if(creamField.getText().length()==0 || fruitField.getText().length()==0){
                warningLabel.setText("type in both field first!");
            } else if(name==null){
                System.out.println("select hersteller name first!");
                warningLabel.setText("select hersteller name first!");
            }
            else {
                Obsttorte ot = new ObsttorteImpl(fruitField.getText(), creamField.getText(), hersteller);
                if(automat.addKuchen(ot, "Obsttorte")){
                    System.out.println("added fruit tart to machine");
                    kuchenList.getItems().add("Fach: "+ ot.getFachnummer() + " " + ot.getName());
                }
            }
        }
        else if(rad_kremkuchen.isSelected()){
            if(creamField.getText().length()==0){
                warningLabel.setText("type in Krem Sorte field first!");
            }else if(name==null){
                System.out.println("select hersteller name first!");
                warningLabel.setText("select hersteller name first!");
            }
            else {
                Kremkuchen kk = new KremkuchenImpl(creamField.getText(), hersteller);
                if (automat.addKuchen(kk, "Kremkuchen")){
                    System.out.println("added creamcake to machine");
                    kuchenList.getItems().add("Fach: "+ kk.getFachnummer() + " " + kk.getName());
                }
            }
        }
        else if(rad_obstkuchen.isSelected()){
            if(fruitField.getText().length()==0){
                warningLabel.setText("type in Obst Sorte field first!");
            }else if(name==null){
                System.out.println("select hersteller name first!");
                warningLabel.setText("select hersteller name first!");
            }
            else {
                Obstkuchen ok = new ObstkuchenImpl(fruitField.getText(), hersteller);
                if(automat.addKuchen(ok, "Obstkuchen")) {
                    System.out.println("added fruitcake to machine");
                    kuchenList.getItems().add("Fach: "+ ok.getFachnummer() + " " + ok.getName());
                }
            }
        }
    }



    public void removeKuchenClicked() throws Exception {
        int fachNummer = Integer.parseInt(fachNummerField.getText());
        if(automat.eraseKuchen(fachNummer) != null){
            System.out.println("removed cake");
            if(fachNummerField.getText().length()==1){
                for(int i=0; i<kuchenList.getItems().size(); i++){
                    if(Character.toString(kuchenList.getItems().get(i).charAt(6)).equalsIgnoreCase(fachNummerField.getText())){
                        kuchenList.getItems().remove(i);
                    }
                }
            }else if(fachNummerField.getText().length()==2){
                int fieldInput = Integer.parseInt(fachNummerField.getText());
                for(int i=0; i<kuchenList.getItems().size(); i++){
                    String char1 = String.valueOf(kuchenList.getItems().get(i).charAt(6));
                    if(Character.isDigit(kuchenList.getItems().get(i).charAt(7))){
                        String char2 = String.valueOf(kuchenList.getItems().get(i).charAt(7));
                        String erg = char1 + char2;
                        int fromList = Integer.parseInt(erg);
                        if(fieldInput == fromList) {
                            kuchenList.getItems().remove(i);
                        }
                    }
                }
            }else if(fachNummerField.getText().length()==3) {
                int fieldInput = Integer.parseInt(fachNummerField.getText());
                for (int i = 0; i < kuchenList.getItems().size(); i++) {
                    String char1 = String.valueOf(kuchenList.getItems().get(i).charAt(6));
                    if (Character.isDigit(kuchenList.getItems().get(i).charAt(7))) {
                        String char2 = String.valueOf(kuchenList.getItems().get(i).charAt(7));
                        if (Character.isDigit(kuchenList.getItems().get(i).charAt(8))) {
                            String char3 = String.valueOf(kuchenList.getItems().get(i).charAt(8));
                            String erg = char1 + char2 + char3;
                            int fromList = Integer.parseInt(erg);
                            if (fieldInput == fromList) {
                                kuchenList.getItems().remove(i);
                                return;
                            }
                        }
                    }
                }
            }
        }else{
            System.out.println("failed to remove cake");
            warningLabel.setText("failed to remove cake");
        }
    }

    public void clearKuchenClicked(){
        kuchenList.getItems().clear();
        Fach[] faecher = new Fach[automat.getFaecherAnzahl()];
        automat.setFaecher(faecher);
        System.out.println("list of cake cleared");
    }

    public void showButtonClicked(){
        System.out.println(Arrays.toString(automat.showHerstellerList()));
        System.out.println(automat.showKuchenList());
    }
}
