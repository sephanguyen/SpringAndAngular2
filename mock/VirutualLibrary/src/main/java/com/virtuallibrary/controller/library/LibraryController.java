package com.virtuallibrary.controller.library;

import com.virtuallibrary.controller.BaseController;
import com.virtuallibrary.model.library.Library;
import com.virtuallibrary.service.library.interf.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController extends BaseController {

    @Autowired
    private LibraryService libraryService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Library> getAllLibraryes() {
        return libraryService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Library getLibraryById(@PathVariable Long id) {
        return libraryService.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Library updateLibrary(@RequestBody Library library) {
        return libraryService.save(library);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Library createLibrary(@RequestBody Library library) {
        return libraryService.save(library);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteLibraryById(@PathVariable Long id) {
        libraryService.delete(id);
    }

}
