package com.ar.edu.um.santiago.library.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PublisherMapperTest {

    private PublisherMapper publisherMapper;

    @BeforeEach
    public void setUp() {
        publisherMapper = new PublisherMapperImpl();
    }
}
