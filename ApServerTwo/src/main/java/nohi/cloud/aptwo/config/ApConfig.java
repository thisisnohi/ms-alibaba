package nohi.cloud.aptwo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author NOHI
 * 2022-06-08 23:10
 **/
@Configuration
@ConfigurationProperties(prefix = "ap")
@Data
public class ApConfig {
    private Integer intVal;
    private String strVal;
    private Double doubleVal;
}
