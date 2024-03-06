package com.ar.edu.um.santiago.library.service.mapper;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ar.edu.um.santiago.library.domain.Book;
import com.ar.edu.um.santiago.library.service.dto.BookDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookMapperTest {

    private BookMapper bookMapper;

    @BeforeEach
    public void setUp() {
        bookMapper = new BookMapperImpl();
    }

    @Test
    public void testDtoToEntityMapping() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setIsbn("1234");
        bookDTO.setName("The Importance of Being Earnest");
        bookDTO.setPublishYear("1895");
        bookDTO.setCopies(15);

        Book book = bookMapper.toEntity(bookDTO);

        assertEquals(book.getId(), bookDTO.getId());
        assertEquals(book.getIsbn(), bookDTO.getIsbn());
        assertEquals(book.getName(), bookDTO.getName());
        assertEquals(book.getPublishYear(), bookDTO.getPublishYear());
        assertEquals(book.getCopies(), bookDTO.getCopies());
    }

    @Test
    public void testEntityToDtoMapping() {
        Book book = new Book();
        book.setId(2L);
        book.setIsbn("5678");
        book.setName("The Shining");
        book.setPublishYear("1977");
        book.setCopies(25);

        BookDTO bookDTO = bookMapper.toDto(book);

        assertEquals(book.getId(), bookDTO.getId());
        assertEquals(book.getIsbn(), bookDTO.getIsbn());
        assertEquals(book.getName(), bookDTO.getName());
        assertEquals(book.getPublishYear(), bookDTO.getPublishYear());
        assertEquals(book.getCopies(), bookDTO.getCopies());
    }

    @Test
    public void testNullDtoToEntityMapping() {
        BookDTO bookDTO = null;
        Book book = bookMapper.toEntity(bookDTO);
        assertNull(book);
    }
}
