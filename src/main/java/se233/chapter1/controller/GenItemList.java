package se233.chapter1.controller;

import se233.chapter1.model.DamageType;
import se233.chapter1.model.item.Armor;
import se233.chapter1.model.item.BasedEquipment;
import se233.chapter1.model.item.Weapon;

public class GenItemList {
    public static BasedEquipment[] setUpItemList(){
        BasedEquipment[] itemLists=new BasedEquipment[5];
        itemLists[0]=new Weapon("Sword", 10, DamageType.PHYSICAL,"assets/sword.png");
        itemLists[1]=new Weapon("Gun", 20, DamageType.PHYSICAL, "assets/gun.png");
        itemLists[2]=new Weapon("Staff", 30, DamageType.MAGICAL, "assets/staff.png");
        itemLists[3]=new Armor("Shirt", 0, 50, "assets/shirt.png");
        itemLists[4]=new Armor("Armor", 50, 0, "assets/armor.png");

        return itemLists;
    }
}
