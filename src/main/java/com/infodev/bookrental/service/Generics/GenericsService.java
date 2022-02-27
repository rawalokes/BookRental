package com.infodev.bookrental.service.Generics;


import java.util.List;

public interface GenericsService <T,ID>{
    public T create(T t);
    public List<T> showAll();
    public T findById(ID id);
    public void deleteById(ID id);

}
