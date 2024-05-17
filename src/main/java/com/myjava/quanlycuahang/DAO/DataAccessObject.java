package com.myjava.quanlycuahang.DAO;
import java.util.ArrayList;

public interface DataAccessObject<T> {
    public int insert(T obj) throws Exception;

    public int update(T obj) throws Exception;
    
    public int delete(String pk) throws Exception;

    public ArrayList<T> selectAll() throws Exception;

    public T selectById(Object pk) throws Exception;

}
