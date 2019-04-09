package com.study.demo.controller;

import com.study.demo.base.Result;
import com.study.demo.base.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author valiantzh
 * @version 1.0
 */
@RestController
public class IndexController {
    @GetMapping("/helloworld")
    public Result helloWorld(){
        return ResultGenerator.genSuccessResult("hello world");
    }
}
