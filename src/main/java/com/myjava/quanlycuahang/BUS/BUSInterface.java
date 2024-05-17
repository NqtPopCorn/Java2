package com.myjava.quanlycuahang.BUS;

import java.util.ArrayList;

public interface BUSInterface<T> {
    public ArrayList<T> getList();

    public int them(T obj) throws Exception;

    public int sua(T obj) throws Exception;

    public int xoa(Object id) throws Exception;

    public ArrayList<T> loc(String fieldName, String value);

    public T tim(String fieldName, String value);
}
