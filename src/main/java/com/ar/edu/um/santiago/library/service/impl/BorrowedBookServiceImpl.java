package com.ar.edu.um.santiago.library.service.impl;

import com.ar.edu.um.santiago.library.domain.BorrowedBook;
import com.ar.edu.um.santiago.library.repository.BorrowedBookRepository;
import com.ar.edu.um.santiago.library.service.BorrowedBookService;
import com.ar.edu.um.santiago.library.service.dto.BorrowedBookDTO;
import com.ar.edu.um.santiago.library.service.mapper.BorrowedBookMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BorrowedBook}.
 */
@Service
@Transactional
public class BorrowedBookServiceImpl implements BorrowedBookService {

    private final Logger log = LoggerFactory.getLogger(BorrowedBookServiceImpl.class);

    private final BorrowedBookRepository borrowedBookRepository;

    private final BorrowedBookMapper borrowedBookMapper;

    public BorrowedBookServiceImpl(BorrowedBookRepository borrowedBookRepository, BorrowedBookMapper borrowedBookMapper) {
        this.borrowedBookRepository = borrowedBookRepository;
        this.borrowedBookMapper = borrowedBookMapper;
    }

    @Override
    public BorrowedBookDTO save(BorrowedBookDTO borrowedBookDTO) {
        log.debug("Request to save BorrowedBook : {}", borrowedBookDTO);
        BorrowedBook borrowedBook = borrowedBookMapper.toEntity(borrowedBookDTO);
        borrowedBook = borrowedBookRepository.save(borrowedBook);
        return borrowedBookMapper.toDto(borrowedBook);
    }

    @Override
    public BorrowedBookDTO update(BorrowedBookDTO borrowedBookDTO) {
        log.debug("Request to update BorrowedBook : {}", borrowedBookDTO);
        BorrowedBook borrowedBook = borrowedBookMapper.toEntity(borrowedBookDTO);
        borrowedBook = borrowedBookRepository.save(borrowedBook);
        return borrowedBookMapper.toDto(borrowedBook);
    }

    @Override
    public Optional<BorrowedBookDTO> partialUpdate(BorrowedBookDTO borrowedBookDTO) {
        log.debug("Request to partially update BorrowedBook : {}", borrowedBookDTO);

        return borrowedBookRepository
            .findById(borrowedBookDTO.getId())
            .map(existingBorrowedBook -> {
                borrowedBookMapper.partialUpdate(existingBorrowedBook, borrowedBookDTO);

                return existingBorrowedBook;
            })
            .map(borrowedBookRepository::save)
            .map(borrowedBookMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BorrowedBookDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BorrowedBooks");
        return borrowedBookRepository.findAll(pageable).map(borrowedBookMapper::toDto);
    }

    public Page<BorrowedBookDTO> findAllWithEagerRelationships(Pageable pageable) {
        return borrowedBookRepository.findAllWithEagerRelationships(pageable).map(borrowedBookMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BorrowedBookDTO> findOne(Long id) {
        log.debug("Request to get BorrowedBook : {}", id);
        return borrowedBookRepository.findOneWithEagerRelationships(id).map(borrowedBookMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BorrowedBook : {}", id);
        borrowedBookRepository.deleteById(id);
    }
}
