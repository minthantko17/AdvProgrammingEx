package se233.chapter1.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import se233.chapter1.Launcher;
import se233.chapter1.model.item.Armor;
import se233.chapter1.model.item.BasedEquipment;
import se233.chapter1.model.item.Weapon;

public class AllCustomHandler {
    public static class GenCharacterHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Launcher.setMainCharacter(GenCharacter.setUpCharacter());
            Launcher.refreshPane();
        }
    }

    //to detect when start drugging item--(not sure) story data type along with equipment to check with target later
    public static void onDragDetected(MouseEvent event, BasedEquipment equipment, ImageView imgView){
        Dragboard db=imgView.startDragAndDrop(TransferMode.ANY);
        db.setDragView(imgView.getImage());
        ClipboardContent content = new ClipboardContent();
        content.put(BasedEquipment.DATA_FORMAT,equipment);  //(key, value)
        db.setContent(content);
        event.consume();
        //e.g. this drag carry (format<basedequip, armor, weapon> , sword)
    }

    //while dragging over the target--check status and availability to drop
    public static void onDragOver(DragEvent event, String type){
        Dragboard dragboard=event.getDragboard();   //carried data
        BasedEquipment retrievedEquipment=(BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT); //carried dataFormat=BasedEqipment, Armor, Weapon
        //we get value from (key, value) -> e.g. sword

        //ensure dataType && check equipment type--------------//sword->Weapon->Weapon =? type
        if(dragboard.hasContent(BasedEquipment.DATA_FORMAT) && retrievedEquipment.getClass().getSimpleName().equals(type)){
            event.acceptTransferModes(TransferMode.MOVE);
        }
    }

    public static void onDragDropped(DragEvent event, Label label, StackPane imgGroup){
        boolean dragCompleted=false;
        Dragboard dragboard=event.getDragboard();       //(format, sword)
        if(dragboard.hasContent(BasedEquipment.DATA_FORMAT)){
            BasedEquipment retrievedEquipment=(BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);
            if(retrievedEquipment.getClass().getSimpleName().equals("Weapon")){
                Launcher.setEquippedWeapon((Weapon)retrievedEquipment);
            }else{
                Launcher.setEquippedArmor((Armor)retrievedEquipment);
            }

            ImageView imgView=new ImageView();
            if(imgGroup.getChildren().size()!=1){
                imgGroup.getChildren().remove(1);
                Launcher.refreshPane();
            }

            label.setText(retrievedEquipment.getClass().getSimpleName()+": \n"+retrievedEquipment.getName());
            imgView.setImage(new Image(Launcher.class.getResource(retrievedEquipment.getImagepath()).toString()));
            imgGroup.getChildren().add(imgView);
            dragCompleted=true;
        }
        event.setDropCompleted(dragCompleted);
    }
}
