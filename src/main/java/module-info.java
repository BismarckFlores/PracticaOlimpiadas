module ni.edu.uam.practicaolimpiadas {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.practicaolimpiadas to javafx.fxml;
    exports ni.edu.uam.practicaolimpiadas;
}