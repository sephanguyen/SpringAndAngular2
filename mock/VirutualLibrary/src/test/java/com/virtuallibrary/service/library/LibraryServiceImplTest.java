package com.virtuallibrary.service.library;

import com.virtuallibrary.model.library.Library;
import com.virtuallibrary.repository.library.interf.LibraryRepository;
import com.virtuallibrary.service.library.interf.LibraryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryServiceImplTest {
    @MockBean
    LibraryRepository mockLibraryRepository;

    @Autowired
    LibraryService libraryService;

    // Library findOne(Long id)

    @Test
    public void testFindOne_UTCID01() {
        Date date = new Date();

        Library libraryIn = new Library(1L, "Library admin", date, true);

        given(this.mockLibraryRepository.findOne(1L)).willReturn(new Library(1L, "Library admin", date, true));

        Library library = this.libraryService.findOne(1L);

        assertThat(library).isEqualToComparingFieldByField(libraryIn);
    }

    @Test
    public void testFindOne_UTCID02() {
        given(this.mockLibraryRepository.findOne(1L)).willReturn(new Library());

        Library library = this.libraryService.findOne(1L);

        assertThat(library).isNotNull();

        assertThat(library.getId()).isNull();
        assertThat(library.getLibAdmin()).isNull();
        assertThat(library.getCreateDate()).isNull();
        assertThat(library.getActive()).isNull();
    }

    @Test
    public void testFindOne_UTCID03() {
        given(this.mockLibraryRepository.findOne(1L)).willReturn(null);

        Library library = this.libraryService.findOne(1L);

        assertThat(library).isNull();
    }

    @Test(expected = TransactionSystemException.class)
    public void testFindOne_UTCID04() {
        given(this.mockLibraryRepository.findOne(1L)).willThrow(
                new TransactionSystemException("Transaction Error"));

        Library library = this.libraryService.findOne(1L);

        assertThat(library).isNull();
    }

    // List<Library> findAll()

    @Test
    public void testFindAll_UTCID01() {
        List<Library> listIn = Arrays.asList(
                new Library(1L, "Library admin 1", new Date(), true),
                new Library(2L, "Library admin 2", new Date(), false));

        given(this.mockLibraryRepository.findAll()).willReturn(listIn);

        List<Library> list = this.libraryService.findAll();

        assertThat(list).isEqualTo(listIn);
    }

    @Test
    public void testFindAll_UTCID03() {
        given(this.mockLibraryRepository.findAll()).willReturn(null);

        List<Library> library = this.libraryService.findAll();

        assertThat(library).isNull();
    }

    @Test(expected = TransactionSystemException.class)
    public void testFindAll_UTCID04() {
        given(this.mockLibraryRepository.findAll()).willThrow(
                new TransactionSystemException("Transaction Error"));

        List<Library> library = this.libraryService.findAll();

        assertThat(library).isNull();
    }

    // List<Library> findAll(List<Long> listId)

    @Test
    public void testFindAll_list_UTCID01() {
        List<Library> listIn = Arrays.asList(
                new Library(1L, "Library admin 1", new Date(), true),
                new Library(2L, "Library admin 2", new Date(), false));

        List<Long> listID = Arrays.asList(1L, 2L);

        given(this.mockLibraryRepository.findAll(listID)).willReturn(listIn);

        List<Library> list = this.libraryService.findAll(listID);

        assertThat(list).isEqualTo(listIn);
    }

    @Test
    public void testFindAll_list_UTCID03() {
        List<Long> listID = Arrays.asList(1L, 2L);

        given(this.mockLibraryRepository.findAll(listID)).willReturn(null);

        List<Library> library = this.libraryService.findAll(listID);

        assertThat(library).isNull();
    }

    @Test(expected = TransactionSystemException.class)
    public void testFindAll_list_UTCID04() {
        List<Long> listID = Arrays.asList(1L, 2L);

        given(this.mockLibraryRepository.findAll(listID)).willThrow(
                new TransactionSystemException("Transaction Error"));

        List<Library> library = this.libraryService.findAll(listID);

        assertThat(library).isNull();
    }

    // long count()

    @Test
    public void testCount_UTCID01() {
        given(this.mockLibraryRepository.count()).willReturn(10L);

        Long count = this.libraryService.count();

        assertThat(count).isEqualTo(10L);
    }

    @Test(expected = TransactionSystemException.class)
    public void testCount_UTCID04() {
        given(this.mockLibraryRepository.count()).willThrow(
                new TransactionSystemException("Transaction Error"));

        Long count = this.libraryService.count();

        assertThat(count).isNull();
    }

    // boolean exists(Long id)

    @Test
    public void testExists_UTCID01() {
        given(this.mockLibraryRepository.exists(1L)).willReturn(true);

        Boolean exists = this.libraryService.exists(1L);

        assertThat(exists).isEqualTo(true);
    }

    @Test
    public void testExists_UTCID02() {
        given(this.mockLibraryRepository.exists(1L)).willReturn(false);

        Boolean exists = this.libraryService.exists(1L);

        assertThat(exists).isEqualTo(false);
    }

    @Test(expected = TransactionSystemException.class)
    public void testExists_UTCID04() {
        given(this.mockLibraryRepository.exists(1L)).willThrow(
                new TransactionSystemException("Transaction Error"));

        Boolean exists = this.libraryService.exists(1L);

        assertThat(exists).isNull();
    }

    // Library save(Library library)

    @Test
    public void testSave_UTCID01() {
        Date date = new Date();
        Library libraryIn = new Library(1L, "Library admin", date, true);

        given(this.mockLibraryRepository.save(libraryIn)).willReturn(new Library(1L, "Library admin", date, true));

        Library library = this.libraryService.save(libraryIn);

        assertThat(library).isEqualToComparingFieldByField(libraryIn);
    }

    @Test
    public void testSave_UTCID02() {
        Date date = new Date();
        Library libraryIn = new Library(1L, "Library admin", date, true);

        given(this.mockLibraryRepository.save(libraryIn)).willReturn(new Library());

        Library library = this.libraryService.save(libraryIn);

        assertThat(library).isNotNull();

        assertThat(library.getId()).isNull();
        assertThat(library.getLibAdmin()).isNull();
        assertThat(library.getCreateDate()).isNull();
        assertThat(library.getActive()).isNull();
    }

    @Test
    public void testSave_UTCID03() {
        Date date = new Date();
        Library libraryIn = new Library(1L, "Library admin", date, true);

        given(this.mockLibraryRepository.save(libraryIn)).willReturn(null);

        Library library = this.libraryService.save(libraryIn);

        assertThat(library).isNull();
    }

    @Test(expected = TransactionSystemException.class)
    public void testSave_UTCID04() {
        Date date = new Date();
        Library libraryIn = new Library(1L, "Library admin", date, true);

        given(this.mockLibraryRepository.save(libraryIn)).willThrow(
                new TransactionSystemException("Transaction Error"));

        Library library = this.libraryService.save(libraryIn);

        assertThat(library).isNull();
    }

    // List<Library> save(List<Library> listLibrary)

    @Test
    public void testSave_list_UTCID01() {
        List<Library> listIn = Arrays.asList(
                new Library(1L, "Library admin 1", new Date(), true),
                new Library(2L, "Library admin 2", new Date(), false));

        given(this.mockLibraryRepository.save(listIn)).willReturn(listIn);

        List<Library> list = this.libraryService.save(listIn);

        assertThat(list).isEqualTo(listIn);
    }

    @Test
    public void testSave_list_UTCID03() {
        List<Library> listIn = Arrays.asList(
                new Library(1L, "Library admin 1", new Date(), true),
                new Library(2L, "Library admin 2", new Date(), false));

        given(this.mockLibraryRepository.save(listIn)).willReturn(null);

        List<Library> library = this.libraryService.save(listIn);

        assertThat(library).isNull();
    }

    @Test(expected = TransactionSystemException.class)
    public void testSave_list_UTCID04() {
        List<Library> listIn = Arrays.asList(
                new Library(1L, "Library admin 1", new Date(), true),
                new Library(2L, "Library admin 2", new Date(), false));

        given(this.mockLibraryRepository.save(listIn)).willThrow(
                new TransactionSystemException("Transaction Error"));

        List<Library> library = this.libraryService.save(listIn);

        assertThat(library).isNull();
    }

    // List<Library> findByLibAdmin(String libAdmin)

    @Test
    public void testFindByLibAdmin_UTCID01() {
        List<Library> listIn = Arrays.asList(
                new Library(1L, "Library admin 1", new Date(), true),
                new Library(2L, "Library admin 2", new Date(), false));

        given(this.mockLibraryRepository.findByLibAdmin("Library admin")).willReturn(listIn);

        List<Library> list = this.libraryService.findByLibAdmin("Library admin");

        assertThat(list).isEqualTo(listIn);
    }

    @Test
    public void testFindByLibAdmin_UTCID03() {

        given(this.mockLibraryRepository.findByLibAdmin("Library admin")).willReturn(null);

        List<Library> library = this.libraryService.findByLibAdmin("Library admin");

        assertThat(library).isNull();
    }

    @Test(expected = TransactionSystemException.class)
    public void testFindByLibAdmin_UTCID04() {

        given(this.mockLibraryRepository.findByLibAdmin("Library admin")).willThrow(
                new TransactionSystemException("Transaction Error"));

        List<Library> library = this.libraryService.findByLibAdmin("Library admin");

        assertThat(library).isNull();
    }

    // List<Library> findByLibAdminContaining(String libAdmin)

    @Test
    public void testFindByLibAdminContaining_UTCID01() {
        List<Library> listIn = Arrays.asList(
                new Library(1L, "Library admin 1", new Date(), true),
                new Library(2L, "Library admin 2", new Date(), false));

        given(this.mockLibraryRepository.findByLibAdminContaining("Library admin")).willReturn(listIn);

        List<Library> list = this.libraryService.findByLibAdminContaining("Library admin");

        assertThat(list).isEqualTo(listIn);
    }

    @Test
    public void testFindByLibAdminContaining_UTCID03() {

        given(this.mockLibraryRepository.findByLibAdminContaining("Library admin")).willReturn(null);

        List<Library> library = this.libraryService.findByLibAdminContaining("Library admin");

        assertThat(library).isNull();
    }

    @Test(expected = TransactionSystemException.class)
    public void testFindByLibAdminContaining_UTCID04() {

        given(this.mockLibraryRepository.findByLibAdminContaining("Library admin")).willThrow(
                new TransactionSystemException("Transaction Error"));

        List<Library> library = this.libraryService.findByLibAdminContaining("Library admin");

        assertThat(library).isNull();
    }

    // void delete(Long id)

    @Test
    public void testDelete_UTCID01() {
        this.libraryService.delete(1L);
    }

    @Test(expected = TransactionSystemException.class)
    public void testDelete_UTCID04() {
        try{
            this.libraryService.delete(1L);
        } finally {
            throw new TransactionSystemException("Transaction Error");
        }
    }

    // void delete(Library entity)

    @Test
    public void testDelete_lib_UTCID01() {
        Library library = new Library(1L, "Library admin", new Date(), true);
        this.libraryService.delete(library);
    }

    @Test(expected = TransactionSystemException.class)
    public void testDelete_lib_UTCID04() {
        try{
            Library library = new Library(1L, "Library admin", new Date(), true);
            this.libraryService.delete(library);
        } finally {
            throw new TransactionSystemException("Transaction Error");
        }
    }

    // void delete(List<Library> listEntity)

    @Test
    public void testDelete_list_UTCID01() {
        List<Library> list = Arrays.asList(
                new Library(1L, "Library admin 1", new Date(), true),
                new Library(2L, "Library admin 2", new Date(), false));

        this.libraryService.delete(list);
    }

    @Test(expected = TransactionSystemException.class)
    public void testDelete_list_UTCID04() {
        try{
            List<Library> list = Arrays.asList(
                    new Library(1L, "Library admin 1", new Date(), true),
                    new Library(2L, "Library admin 2", new Date(), false));

            this.libraryService.delete(list);
        } finally {
            throw new TransactionSystemException("Transaction Error");
        }
    }

    // void deleteAll()

    @Test
    public void testDeleteAll_UTCID01() {
        this.libraryService.deleteAll();
    }

    @Test(expected = TransactionSystemException.class)
    public void testDeleteAll_UTCID04() {
        try{
            this.libraryService.deleteAll();
        } finally {
            throw new TransactionSystemException("Transaction Error");
        }
    }


}

