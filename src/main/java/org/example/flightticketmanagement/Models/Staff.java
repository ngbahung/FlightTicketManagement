package org.example.flightticketmanagement.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Staff {
    private final StringProperty tenNV;
    private final StringProperty hoNV;
    private final StringProperty diacChiNV;
    private final ObjectProperty<Account> checkingAccount;

    public Staff(String tenNV, String hoNV, String diacChNV, Account checkingAccount) {
        this.tenNV = new SimpleStringProperty(tenNV);
        this.hoNV = new SimpleStringProperty(hoNV);
        this.diacChiNV = new SimpleStringProperty(diacChNV);
        this.checkingAccount = new SimpleObjectProperty<>(checkingAccount);
    }

    public StringProperty getTenNV() {
        return tenNV;
    }

    public StringProperty getHoNV() {
        return hoNV;
    }

    public StringProperty getDiacChiNV() {
        return diacChiNV;
    }

    public ObjectProperty<Account> getCheckingAccount() {
        return checkingAccount;
    }
}
