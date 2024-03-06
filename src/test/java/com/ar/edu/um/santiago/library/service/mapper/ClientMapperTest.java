package com.ar.edu.um.santiago.library.service.mapper;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ar.edu.um.santiago.library.domain.Client;
import com.ar.edu.um.santiago.library.service.dto.ClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientMapperTest {

    private ClientMapper clientMapper;

    @BeforeEach
    public void setUp() {
        clientMapper = new ClientMapperImpl();
    }

    @Test
    public void testDtoToEntityMapping() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(2L);
        clientDTO.setFirstName("Stephen");
        clientDTO.setLastName("King");
        clientDTO.setEmail("thestephenking@gmail.com");
        clientDTO.setAddress("Portland, Maine");
        clientDTO.setPhone("1144444444");

        Client client = clientMapper.toEntity(clientDTO);

        assertEquals(clientDTO.getId(), client.getId());
        assertEquals(clientDTO.getFirstName(), client.getFirstName());
        assertEquals(clientDTO.getLastName(), client.getLastName());
        assertEquals(clientDTO.getEmail(), client.getEmail());
        assertEquals(clientDTO.getAddress(), client.getAddress());
        assertEquals(clientDTO.getPhone(), client.getPhone());
    }

    @Test
    public void testEntityToDtoMapping() {
        Client client = new Client();
        client.setId(1L);
        client.setFirstName("Oscar");
        client.setLastName("Wilde");
        client.setEmail("theoscarwilde@gmail.com");
        client.setAddress("Dublin, Ireland");
        client.setPhone("1155555555");

        ClientDTO clientDTO = clientMapper.toDto(client);

        assertEquals(client.getId(), clientDTO.getId());
        assertEquals(client.getFirstName(), clientDTO.getFirstName());
        assertEquals(client.getLastName(), clientDTO.getLastName());
        assertEquals(client.getEmail(), clientDTO.getEmail());
        assertEquals(client.getAddress(), clientDTO.getAddress());
        assertEquals(client.getPhone(), clientDTO.getPhone());
    }

    @Test
    public void testNullDtoToEntityMapping() {
        ClientDTO clientDTO = null;
        Client client = clientMapper.toEntity(clientDTO);
        assertNull(client);
    }

    @Test
    public void testNullEntityToDtoMapping() {
        Client client = null;
        ClientDTO clientDTO = clientMapper.toDto(client);
        assertNull(clientDTO);
    }

    @Test
    public void testEmptyDtoToEntityMapping() {
        ClientDTO clientDTO = new ClientDTO();
        Client client = clientMapper.toEntity(clientDTO);
        assertNull(client.getId());
        assertNull(client.getFirstName());
        assertNull(client.getLastName());
        assertNull(client.getEmail());
        assertNull(client.getAddress());
        assertNull(client.getPhone());
    }

    @Test
    public void testEmptyEntityToDtoMapping() {
        Client client = new Client();
        ClientDTO clientDTO = clientMapper.toDto(client);
        assertNull(clientDTO.getId());
        assertNull(clientDTO.getFirstName());
        assertNull(clientDTO.getLastName());
        assertNull(clientDTO.getEmail());
        assertNull(clientDTO.getAddress());
        assertNull(clientDTO.getPhone());
    }
}
