package org.example.gmup.adapter.inbound;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // اضافه کردن مسیر صفحه لاگین و ثبت‌نام
    @GetMapping("/auth")
    public String authPage() {
        return "auth";
    }
}