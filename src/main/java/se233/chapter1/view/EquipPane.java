package se233.chapter1.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import se233.chapter1.Launcher;
import se233.chapter1.model.item.Armor;
import se233.chapter1.model.item.Weapon;

public class EquipPane extends ScrollPane {
    private Weapon equippedWeapon;
    private Armor equippedArmor;

    public EquipPane(){}

    private Pane getDetailsPane(){
        Pane equipmentInfoPane=new VBox(10);    //creating Pane
        equipmentInfoPane.setBorder(null);
        ((VBox)equipmentInfoPane).setAlignment(Pos.CENTER); //casting Pane to VBox
        equipmentInfoPane.setPadding(new Insets(25, 25, 25, 25));

        Label weaponLbl, armorLbl;
        ImageView weaponImg=new ImageView();
        ImageView armorImg=new ImageView();

        if(equippedWeapon!=null){   //add equipped weapon and info to display(ImageView)
            weaponLbl=new Label("Weapon:\n"+equippedWeapon.getName());
            weaponImg.setImage(new Image(Launcher.class.getResource(equippedWeapon.getImagepath()).toString()));
        }else{
            weaponLbl=new Label("Weapon:\n");
            weaponImg.setImage(new Image(Launcher.class.getResource("assets/blank.png").toString()));
        }

        if(equippedArmor!=null){
            armorLbl=new Label("Armor:\n"+equippedArmor.getName());
            armorImg.setImage(new Image(Launcher.class.getResource(equippedArmor.getImagepath()).toString()));
        }else{
            armorLbl=new Label("Armor:\n");
            armorImg.setImage(new Image(Launcher.class.getResource("assets/blank.png").toString()));
        }

        //add weapon and armor display into Pane
        equipmentInfoPane.getChildren().addAll(weaponLbl, weaponImg, armorLbl, armorImg);

        return equipmentInfoPane;
    }

    public void drawPane(Weapon equippedWeapon, Armor equippedArmor){
        this.equippedWeapon=equippedWeapon;
        this.equippedArmor=equippedArmor;
        Pane equipmentInfo = getDetailsPane();
        this.setStyle("-fx-background-color: Red");
        this.setContent(equipmentInfo);
    }
}
