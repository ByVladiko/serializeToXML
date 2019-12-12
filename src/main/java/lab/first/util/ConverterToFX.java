package lab.first.util;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public final class ConverterToFX {
    private static ConverterToFX instance;

    private ConverterToFX() {
    }

    public static ConverterToFX getInstance(String value) {
        if (instance == null) {
            instance = new ConverterToFX();
        }
        return instance;
    }

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
