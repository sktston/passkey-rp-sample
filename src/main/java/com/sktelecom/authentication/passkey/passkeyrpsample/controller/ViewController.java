package com.sktelecom.authentication.passkey.passkeyrpsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/")
    public String index() {
        return "third_party/fido-interop-webapp/index";
    }
}