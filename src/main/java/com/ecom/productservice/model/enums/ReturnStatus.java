package com.ecom.productservice.model.enums;

public enum ReturnStatus {
    REQUEST_PENDING,
    REQUEST_APPROVED,
    REQUEST_REJECTED, //np. minal termin zwrotu, produkt zwrocony jest uszkodzony przez klienta itp.
    RETURN_RECEIVED,//zwrot przyszedl
    RETURN_IN_PROGRESS, // zwrot przyszedl i juz sprawdzaja.
    RETURN_COMPLETED, //przyjeto, sprawdzono i zwrocono srodki, caly proces zakonczony
}
