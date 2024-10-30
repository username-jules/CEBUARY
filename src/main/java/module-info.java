module mainarea.structureplease {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    requires jdk.compiler;

    opens mainarea.cebuary to javafx.fxml;
    exports mainarea.cebuary;
    exports mainarea.cebuary.dictionaryscene;
    opens mainarea.cebuary.dictionaryscene to javafx.fxml;
}