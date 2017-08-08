package com.virtuallibrary.controller.library;

import com.virtuallibrary.model.library.Library;
import com.virtuallibrary.service.library.interf.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/libraries")
public class LibrarySearchController
{
    @Autowired
    private LibraryService libraryService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Library> findLibraryByLibAdmin(@RequestParam String libAdmin) {
        return libraryService.findByLibAdminContaining(libAdmin);
    }
}
