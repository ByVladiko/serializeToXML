package lab.first.controllers;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class ConverterToFX {

    public StringProperty toStringProperty(String str) {
        return new SimpleStringProperty(str);
    }

    public IntegerProperty toIntegerProperty(int num) {
        return new SimpleIntegerProperty(num);
    }

    public ObservableList toObservableList (List list) {
        return FXCollections.observableArrayList(list);
    }
}
