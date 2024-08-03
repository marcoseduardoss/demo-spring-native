package br.com.marcoseduardo.springnative.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private static final String PING_URL = "http://localhost:8080/ping";
    private static final String PING_MESSAGE = "...pong";
    private static final String LINK_TEXT = PING_URL;

    @GetMapping(path = "/")
    public String home() {
        return buildHtmlLink(PING_URL, LINK_TEXT);
    }

    @GetMapping(path = "/ping")
    public String ping() {
        return PING_MESSAGE;
    }

    private String buildHtmlLink(String url, String text) {
        return String.format("<html><body></br></br><a href='%s'>%s</a></body></html>", url, text);
    }
}
