package com.myjava.quanlycuahang.GUI;

public class MyComboboxItem {
    private String name;
    private String value;

    public MyComboboxItem(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}