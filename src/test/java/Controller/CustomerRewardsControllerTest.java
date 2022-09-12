package Controller;

import com.rewards.controller.CustomerRewardsController;
import com.rewards.entity.CustomerEntity;
import com.rewards.repository.CustomerRepository;
import com.rewards.service.RewardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CustomerRewardsControllerTest {

    @InjectMocks
    CustomerRewardsController customerRewardsController;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    RewardService rewardService;

    @Test()
    public void CreateCustomerNullInput() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customerRewardsController.createCustomerOrder(null);
        });
        assertEquals("customer object can not be null", thrown.getMessage());
    }

    @Test()
    public void CreateCustomerSuccessOutput() {
        Mockito.doReturn(populateCustomerEntity()).when(customerRepository).save(Mockito.any());
        CustomerEntity result =
                customerRewardsController.createCustomerOrder(populateCustomerEntity());
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
        Mockito.doReturn(Optional.of(populateCustomerEntity())).when(customerRepository).findById(Mockito.any());
        Mockito.when(rewardService.calculateRewards(Mockito.any())).thenReturn(BigDecimal.valueOf(52.00));
        BigDecimal result =
                customerRewardsController.getRewardPointsByCustomerId(1);
        assertEquals(52.0, result.doubleValue());
    }

    private CustomerEntity populateCustomerEntity() {
        return new CustomerEntity("John", BigDecimal.valueOf(100.02));
    }

}
