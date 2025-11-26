package ru.is_lab1.mapper;

import ru.is_lab1.dto.request.ImportRequest;
import ru.is_lab1.entity.Import;

public class ImportResultMapper {
    public Import toEntity(ImportRequest request){
        if (request == null)
            return null;
        Import newImport = new Import();
        newImport.setCount(request.getCount());
        newImport.setIsSuccess(request.getIsSuccess());
        return newImport;
    }
}
