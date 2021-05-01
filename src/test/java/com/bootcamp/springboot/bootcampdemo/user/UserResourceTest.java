package com.bootcamp.springboot.bootcampdemo.user;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.junit.jupiter.api.Test;


@RunWith(SpringRunner.class)
@WebMvcTest(value = UserResource.class)
public class UserResourceTest {

    @Mock
    private UserDaoService service;


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserResource userRepository;

//    static {
//        users.add(new User(1, "Diana", "diana22@yahoo.com", "092929211", "diana", "www.google.com",
//                new Address( "steert", "apt 55", "Amsterdam", "2998-3874"), new Company("Everis", "Everis", "about it" )));
//    }
//
//    User user = new User(1, "Diana", "diana22@yahoo.com", "092929211", "diana", "www.google.com",
//            new Address( "steert", "apt 55", "Amsterdam", "2998-3874"), new Company("Everis", "Everis", "about it" ));

//    String exampleUserJson = "{\"name\":\"diana\", \"email\":\"diana@yahoo.com\", \"phone\":\"90393901\", \"username\":\"diana\", " +
//            "\"address\":[\"Learn Maven\",\"IAmsterdam\",\"Apt 55\",\"82902-2928L\"], \"company\":[\"Everis\",\"IAmsterdam\",\"about it\"] }";
    String exampleUserJson = "{\n" +
        "\"id\": 3,\n" +
        "\"name\": \"Rachimo Supa\",\n" +
        "\"username\": \"supar\",\n" +
        "\"email\": \"rachimo@april.biz\",\n" +
        "\"address\": {\n" +
        "\"street\": \"Bussum Station\",\n" +
        "\"suite\": \"Apt. 33\",\n" +
        "\"city\": \"Bussum\",\n" +
        "\"zipcode\": \"92998-3874\"\n" +
        "},\n" +
        "\"phone\": \"1-770-736-8031 x56442\",\n" +
        "\"website\": \"rachimo.org\",\n" +
        "\"company\": {\n" +
        "\"name\": \"Public space\",\n" +
        "\"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
        "\"bs\": \"harness real-time e-markets\"\n" +
        "}\n" +
        "}";



    @Test
    public void createUser() throws Exception {

        when(userRepository.createUser(
                        Mockito.any(User.class))).thenReturn(null);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("http://localhost:8080/users")
                .accept(MediaType.APPLICATION_JSON).content(exampleUserJson)
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

        MockHttpServletResponse response = result.getResponse();

//        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

//         assertEquals("http://localhost:8080/users",
//                response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    public void get_allUsers() throws Exception {

        List<User> userList = new ArrayList<>();
        User user1 = new User(3, "Diana", "diana22@yahoo.com", "092929211", "diana", "www.google.com",
            new Address( "steert", "apt 55", "Amsterdam", "2998-3874"), new Company("Everis", "Everis", "about it" ));

        User user2 = new User(4, "Diana", "diana22@yahoo.com", "092929211", "diana", "www.google.com",
                new Address( "steert", "apt 55", "Amsterdam", "2998-3874"), new Company("Everis", "Everis", "about it" ));

        userList.add(user1);
        userList.add(user2);

        // Mocking out the vehicle service
        Mockito.when(userRepository.retrieveAllUsers()).thenReturn(userList);

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/users").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
