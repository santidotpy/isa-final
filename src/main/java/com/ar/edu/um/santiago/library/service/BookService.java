package com.ar.edu.um.santiago.library.service;

import com.ar.edu.um.santiago.library.service.dto.BookDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.ar.edu.um.santiago.library.domain.Book}.
 */
public interface BookService {
    /**
     * Save a book.
     *
     * @param bookDTO the entity to save.
     * @return the persisted entity.
     */
    BookDTO save(BookDTO bookDTO);

    /**
     * Updates a book.
     *
     * @param bookDTO the entity to update.
     * @return the persisted entity.
     */
    BookDTO update(BookDTO bookDTO);

    /**
     * Partially updates a book.
     *
     * @param bookDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BookDTO> partialUpdate(BookDTO bookDTO);

    /**
     * Get all the books.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BookDTO> findAll(Pageable pageable);

    /**
     * Get all the books with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BookDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" book.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BookDTO> findOne(Long id);

    /**
     * Delete the "id" book.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
