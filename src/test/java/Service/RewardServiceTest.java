package Service;

import com.rewards.entity.CustomerEntity;
import com.rewards.service.RewardService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardServiceTest {

    @Test
    public void testRewardPointsInput1() {
        CustomerEntity customerEntity = new CustomerEntity("John", BigDecimal.valueOf(101.02));
        RewardService rewardService = new RewardService();
        BigDecimal result = rewardService.calculateRewards(customerEntity);
        assertEquals(52, result.intValue());
    }

    @Test
    public void testRewardPointsInput2() {
        CustomerEntity customerEntity = new CustomerEntity("John", BigDecimal.valueOf(120.01));
        RewardService rewardService = new RewardService();
        BigDecimal result = rewardService.calculateRewards(customerEntity);
        assertEquals(90, result.intValue());
    }

    @Test
    public void testRewardPointsInput3() {
        CustomerEntity customerEntity = new CustomerEntity("John", BigDecimal.valueOf(20.01));
        RewardService rewardService = new RewardService();
        BigDecimal result = rewardService.calculateRewards(customerEntity);
        assertEquals(0, result.intValue());
    }

    @Test
    public void testRewardPointsInput4() {
        CustomerEntity customerEntity = new CustomerEntity("John", BigDecimal.valueOf(50.01));
        RewardService rewardService = new RewardService();
        BigDecimal result = rewardService.calculateRewards(customerEntity);
        assertEquals(0, result.intValue());
    }

    @Test
    public void testRewardPointsInput5() {
        CustomerEntity customerEntity = new CustomerEntity("John", BigDecimal.valueOf(70.01));
        RewardService rewardService = new RewardService();
        BigDecimal result = rewardService.calculateRewards(customerEntity);
        assertEquals(20, result.intValue());
    }
}
