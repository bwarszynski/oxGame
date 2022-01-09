module lab.oxgame.oxgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.zaxxer.hikari;
    requires org.slf4j;


    opens lab.oxgame.oxgame to javafx.fxml;
    exports lab.oxgame.oxgame;
    exports lab.oxgame.model;
    opens lab.oxgame.model to javafx.fxml;
}