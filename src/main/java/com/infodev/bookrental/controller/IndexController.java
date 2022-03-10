package com.infodev.bookrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author rawalokes
 * Date:3/8/22
 * Time:10:53 AM
 */
@Controller("/")
public class IndexController {
    @GetMapping
    public String indexPage(){
        return "index";
    }
}
