package nohi.cloud.apone.schedual;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author NOHI
 * 2022-06-20 17:45
 **/
@Component
@Slf4j
public class JobService {
    @Scheduled(cron = "0/10 * * * * ?")
    @SentinelResource("JobService.startHello")
    public void startHello(){
        log.info("JobService.startHello.");
    }
}
