package se233.chapter1.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import se233.chapter1.Launcher;

public class AllCustomHandler {
    public static class GenCharacterHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Launcher.setMainCharacter(GenCharacter.setUpCharacter());
            Launcher.refreshPane();
        }
    }
}
