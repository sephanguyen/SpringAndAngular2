package com.virtuallibrary.repository.library;

import com.virtuallibrary.model.library.Library;
import com.virtuallibrary.repository.BaseRepository;
import com.virtuallibrary.repository.library.interf.LibraryRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public class LibraryRepositoryImpl extends BaseRepository<Library, Long> implements LibraryRepositoryCustom {
}
