package org.app.util;

public enum StatusEnum {
    Created("1"),
    Assigned("2"),
    InProcess("3"),
    Cancelled("4"),
    Finished("5");

    private final String clave;
    StatusEnum(String clave) { this.clave = clave; }
    public String getClave() { return clave; }
}
