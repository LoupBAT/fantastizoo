module fr.g4zoo.fantastizoo {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.g4zoo.fantastizoo to javafx.fxml;
    exports fr.g4zoo.fantastizoo;
}