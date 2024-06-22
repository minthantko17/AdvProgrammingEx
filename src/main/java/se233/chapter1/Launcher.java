package se233.chapter1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se233.chapter1.controller.GenCharacter;
import se233.chapter1.controller.GenItemList;
import se233.chapter1.model.character.BasedCharacter;
import se233.chapter1.model.item.Armor;
import se233.chapter1.model.item.BasedEquipment;
import se233.chapter1.model.item.Weapon;
import se233.chapter1.view.CharacterPane;
import se233.chapter1.view.EquipPane;
import se233.chapter1.view.InventoryPane;

public class Launcher extends Application {
    private static Scene mainScene;
    private static BasedCharacter mainCharacter=null;
    private static BasedEquipment[] allEquipments=null;
    private static Weapon equippedWeapon=null;
    private static Armor equippedArmor=null;
    private static CharacterPane characterPane=null;
    private static EquipPane equipPane=null;
    private static InventoryPane inventoryPane=null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chapter1");
        primaryStage.setResizable(false);
        primaryStage.show();

        mainCharacter= GenCharacter.setUpCharacter();   //build random character
        allEquipments= GenItemList.setUpItemList();     //add item lists

        Pane mainPane=getMainPane();
        mainScene=new Scene(mainPane);
        primaryStage.setScene(mainScene);
    }

    public Pane getMainPane(){              //to combine all subPanes into one
        BorderPane mainPane=new BorderPane();
        characterPane=new CharacterPane();
        equipPane=new EquipPane();
        inventoryPane=new InventoryPane();
        refreshPane();  //recalculate sub-components

        mainPane.setCenter(characterPane);
        mainPane.setLeft(equipPane);
        mainPane.setBottom(inventoryPane);
        return mainPane;
    }

    public static void refreshPane(){       //refresh by re-drawing Pane from respective View class
        characterPane.drawPane(mainCharacter);
        equipPane.drawPane(equippedWeapon, equippedArmor);
        inventoryPane.drawPane(allEquipments);
    }

    public static BasedCharacter getMainCharacter(){return mainCharacter;}
    public static void setMainCharacter(BasedCharacter mainCharacter){
        Launcher.mainCharacter=mainCharacter;}

    public static void main(String[] args) {launch(args);}
}