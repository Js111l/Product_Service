package com.ecom.productservice.model.enums;

import java.util.List;

public enum ReturnStatus implements BaseEnum{
    REQUEST_PENDING,
    REQUEST_APPROVED,
    REQUEST_REJECTED, //np. minal termin zwrotu, produkt zwrocony jest uszkodzony przez klienta itp.
    RETURN_RECEIVED,//zwrot przyszedl
    RETURN_IN_PROGRESS, // zwrot przyszedl i juz sprawdzaja.
    RETURN_COMPLETED,
    ; //przyjeto, sprawdzono i zwrocono srodki, caly proces zakonczony

    @Override
    public String getLabel() {
        return "";
    }
    @Override
    public List<String> getValues() {
        return this.getValues();
    }
}
