module Pizzaria {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;


    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires org.mariadb.jdbc;
    opens Pizzaria to org.hibernate.orm.core;

    opens Pizzaria.Frontend to javafx.fxml;
    exports Pizzaria.Frontend;
}