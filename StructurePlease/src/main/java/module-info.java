module mainarea.structureplease {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jdk.compiler;

    opens mainarea.structureplease to javafx.fxml;
    exports mainarea.structureplease;
    exports mainarea.structureplease.dictionaryscene;
    opens mainarea.structureplease.dictionaryscene to javafx.fxml;
}