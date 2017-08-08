package com.virtuallibrary.repository.library.interf;

import com.virtuallibrary.model.library.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Long>, LibraryRepositoryCustom {

    List<Library> findByLibAdmin(String libAdmin);

    List<Library> findByLibAdminContaining(String libAdmin);
}
