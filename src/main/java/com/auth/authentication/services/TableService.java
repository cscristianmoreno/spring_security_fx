package com.auth.authentication.services;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;

@Getter
public class TableService {
    
    public TableService(final TableView<Object> table) {
        for (int i = 0; i < table.getColumns().size(); i++) {
            String id = table.getColumns().get(i).getId();
            table.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(id));
        }

    }
}
