package com.github.paulocarpinetti.rest_udemy_2025.unittests.mapper;

import com.github.paulocarpinetti.rest_udemy_2025.data.dto.PersonDTO;
import com.github.paulocarpinetti.rest_udemy_2025.mapper.DozerMapper;
import com.github.paulocarpinetti.rest_udemy_2025.model.Person;
import com.github.paulocarpinetti.rest_udemy_2025.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DozerConverterTest {

    MockPerson inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockPerson();
    }

    @Test
    public void parseEntityToVOTest() {
        PersonDTO output = DozerMapper.parseObject(inputObject.mockEntity(), PersonDTO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Address Test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parseEntityListToDTOListTest() {
        List<PersonDTO> outputList = DozerMapper.parseListObjects(inputObject.mockEntityList(), PersonDTO.class);
        PersonDTO outputZero = outputList.getFirst();

        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("First Name Test0", outputZero.getFirstName());
        assertEquals("Last Name Test0", outputZero.getLastName());
        assertEquals("Address Test0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());

        PersonDTO outputSeven = outputList.getFirst();

        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("First Name Test0", outputSeven.getFirstName());
        assertEquals("Last Name Test0", outputSeven.getLastName());
        assertEquals("Address Test0", outputSeven.getAddress());
        assertEquals("Male", outputSeven.getGender());

        PersonDTO outputEleven = outputList.get(1);

        assertEquals(Long.valueOf(1L), outputEleven.getKey());
        assertEquals("First Name Test11", outputEleven.getFirstName());
        assertEquals("Last Name Test11", outputEleven.getLastName());
        assertEquals("Address Test11", outputEleven.getAddress());
        assertEquals("Female", outputEleven.getGender());
    }

    @Test
    public void parseDTOToEntityTest() {
        Person output = DozerMapper.parseObject(inputObject.mockDTO(), Person.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Address Test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parserDTOListToEntityListTest() {
        List<Person> outputList = DozerMapper.parseListObjects(inputObject.mockDTOList(), Person.class);
        Person outputZero = outputList.getFirst();

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("First Name Test0", outputZero.getFirstName());
        assertEquals("Last Name Test0", outputZero.getLastName());
        assertEquals("Address Test0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());

        Person outputSeven = outputList.getFirst();

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("First Name Test0", outputSeven.getFirstName());
        assertEquals("Last Name Test0", outputSeven.getLastName());
        assertEquals("Address Test0", outputSeven.getAddress());
        assertEquals("Male", outputSeven.getGender());

        Person outputTwelve = outputList.get(6);

        assertEquals(Long.valueOf(6L), outputTwelve.getId());
        assertEquals("First Name Test12", outputTwelve.getFirstName());
        assertEquals("Last Name Test12", outputTwelve.getLastName());
        assertEquals("Address Test12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
    }
}
