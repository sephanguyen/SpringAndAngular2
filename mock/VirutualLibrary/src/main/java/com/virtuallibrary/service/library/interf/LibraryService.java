package com.virtuallibrary.service.library.interf;

import com.virtuallibrary.model.library.Library;
import com.virtuallibrary.service.IBaseService;

import java.util.List;

public interface LibraryService extends IBaseService<Library, Long> {

    List<Library> findByLibAdmin(String libAdmin);

    List<Library> findByLibAdminContaining(String libAdmin);
}
