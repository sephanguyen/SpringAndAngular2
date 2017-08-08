package com.virtuallibrary.service.library;

import com.virtuallibrary.model.library.Library;
import com.virtuallibrary.repository.library.interf.LibraryRepository;
import com.virtuallibrary.service.BaseService;
import com.virtuallibrary.service.library.interf.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl extends BaseService<Library, Long> implements LibraryService {

    @Autowired
    LibraryRepository libraryRepository;

    @Override
    public Library findOne(Long id) {
        return libraryRepository.findOne(id);
    }

    @Override
    public List<Library> findAll() {
        return (List<Library>) libraryRepository.findAll();
    }

    @Override
    public List<Library> findAll(List<Long> listId) {
        return (List<Library>) libraryRepository.findAll(listId);
    }

    @Override
    public long count() {
        return libraryRepository.count();
    }

    @Override
    public boolean exists(Long id) {
        return libraryRepository.exists(id);
    }

    @Override
    public Library save(Library library) {
        return libraryRepository.save(library);
    }

    @Override
    public List<Library> save(List<Library> listLibrary) {
        return (List<Library>) libraryRepository.save(listLibrary);
    }

    @Override
    public void delete(Long id) {
        this.libraryRepository.delete(id);
    }

    @Override
    public void delete(Library entity) {
        this.libraryRepository.delete(entity);
    }

    @Override
    public void delete(List<Library> listEntity) {
        libraryRepository.delete(listEntity);
    }

    @Override
    public void deleteAll() {
        libraryRepository.deleteAll();
    }

    @Override
    public List<Library> findByLibAdmin(String libAdmin) {
        return libraryRepository.findByLibAdmin(libAdmin);
    }

    @Override
    public List<Library> findByLibAdminContaining(String libAdmin) {
        return libraryRepository.findByLibAdminContaining(libAdmin);
    }
}
