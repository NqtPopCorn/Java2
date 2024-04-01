package com.myjava.quanlysinhvien.MODEL;
import java.util.ArrayList;

public interface DataAccessObject<T> {
    public int insert(T obj);

    public int update(T obj);
    
    public int delete(String pk);

    public ArrayList<T> selectAll();

    public T selectById(String t);

    public int getAutoIncrement();
}
