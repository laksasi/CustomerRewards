package Main;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewards.entity.CustomerEntity;
import com.rewards.main.RewardsApplication;
import com.rewards.model.Customer;
import com.rewards.repository.CustomerRepository;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RewardsApplication.class)
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RewardsApplicationIntegrationTest {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    @Order(1)
    public void createCustomer() throws Exception {
        String uri = "/customer";
        Customer customer = new Customer("John", BigDecimal.valueOf(120.00));

        String inputJson = mapToJson(customer);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        Customer result = mapFromJson(mvcResult.getResponse().getContentAsString(), Customer.class);
        assertEquals(1, result.getId().intValue());
    }

    @Test
    @Order(2)
    public void getRewardPoints() throws Exception {
        String uri = "/customer/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        assertEquals("90.00", mvcResult.getResponse().getContentAsString());
    }

    @Test
    @Ignore
    public void testFindById() {
        CustomerEntity customerEntity = new CustomerEntity("John", BigDecimal.valueOf(120.00));
        customerRepository.save(customerEntity);
        CustomerEntity result = customerRepository.findById(customerEntity.getId()).get();
        assertEquals(customerEntity.getFirstName(), result.getFirstName());
    }

    @Test
    @Ignore
    public void testSave() {
        CustomerEntity customerEntity = new CustomerEntity("John", BigDecimal.valueOf(120.00));
        CustomerEntity result = customerRepository.save(customerEntity);
        assertEquals(customerEntity.getFirstName(), result.getFirstName());
    }
}
