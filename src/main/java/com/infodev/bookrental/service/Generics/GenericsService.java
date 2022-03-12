package com.infodev.bookrental.service.Generics;


import com.infodev.bookrental.dto.ResponseDto;

import java.io.IOException;
import java.util.List;

public interface GenericsService <T,ID>{
    public ResponseDto create(T t) throws IOException;
    public List<T> showAll();
    public ResponseDto findById(ID id);
    public ResponseDto deleteById(ID id);

}
