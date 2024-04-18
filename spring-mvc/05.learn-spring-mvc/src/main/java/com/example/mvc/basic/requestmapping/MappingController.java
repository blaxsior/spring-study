package com.example.mvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

//@Slf4j
@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping({"/hello-basic", "/hello-world"})
    public String helloBasic() {
        log.info("hellobasic");
        return "hello";
    }

    @RequestMapping(value = "/hello-basic2", method = RequestMethod.POST)
    public String helloBasic2() {
        log.info("hellobasic2");
        return "hello";
    }

    @PostMapping("/hello-basic3")
    public String helloBasic3() {
        log.info("hellobasic3");
        return "hello";
    }

    @GetMapping("/path-variable/{id}")
    public String getPathVariable(@PathVariable("id") String id) {
        log.info("path variable = {}", id);
        return id;
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String getMappingParams(@RequestParam("mode") String mode) {
        log.info("mode = debug ? {}", mode.equals("debug"));
        return "ok";
    }

    @GetMapping(value = "/mapping-header", headers = "hello=world")
    public String mappingHeader(@RequestParam("mode") String mode) {
        log.info("mode = debug ? {}", mode.equals("debug"));
        return "ok";
    }

    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    @PostMapping(value = "/mapping-produce", produces = "text/plain")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
