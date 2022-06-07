package nohi.cloud.aptwo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NOHI
 * 2022-06-05 22:30
 **/
@RestController
@Slf4j
public class HelloController {


    @GetMapping(value = "/")
    public String index() {
        log.info("HelloController.index...");
        return "Hello";
    }

    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {
        log.info("ApServerOne.HelloController.echo...{}", string);
        return "Hello Nacos Discovery, ApServerOne " + string;
    }
}
