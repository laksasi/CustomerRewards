package Controller;

import com.rewards.controller.CustomerRewardsController;
import com.rewards.entity.CustomerEntity;
import com.rewards.model.Customer;
import com.rewards.service.RewardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CustomerRewardsControllerTest {

    @InjectMocks
    private CustomerRewardsController customerRewardsController;

    @Mock
    private RewardService rewardService;
    @Mock
    private ModelMapper modelMapper;


    @Test()
    public void CreateCustomerNullInput() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customerRewardsController.createCustomerOrder(null);
        });
        assertEquals("customer object can not be null", thrown.getMessage());
    }

    @Test()
    public void CreateCustomerSuccessOutput() {
        CustomerEntity customerEntity = populateCustomerEntity();
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.eq(CustomerEntity.class))).thenReturn(customerEntity);
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.eq(Customer.class))).
                thenReturn(new Customer("John",
                        BigDecimal.valueOf(12.00)));
        Mockito.doReturn(customerEntity).when(rewardService).save(Mockito.any());
        Customer result =
                customerRewardsController.createCustomerOrder(new Customer("John",
                        BigDecimal.valueOf(12.00)));
        assertEquals("John", result.getFirstName());
    }

    @Test()
    public void getRewardsWithNullCustomerId() {
        EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            customerRewardsController.getRewardPointsByCustomerId(null);
        });
        assertEquals(null, thrown.getMessage());
    }

    @Test()
    public void getRewardsForHundredDollarsPurchaseAmount() {
        Mockito.doReturn(Optional.of(populateCustomerEntity())).when(rewardService).getCustomerEntityById(Mockito.any());
        Mockito.when(rewardService.calculateRewards(Mockito.any())).thenReturn(BigDecimal.valueOf(52.00));
        BigDecimal result =
                customerRewardsController.getRewardPointsByCustomerId(1);
        assertEquals(52.0, result.doubleValue());
    }

    private CustomerEntity populateCustomerEntity() {

        return new CustomerEntity("John", BigDecimal.valueOf(100.02));
    }

}
