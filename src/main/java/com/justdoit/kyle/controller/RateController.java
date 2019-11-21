package com.justdoit.kyle.controller;

import com.justdoit.kyle.service.RateService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-10-22 14:38:58
 * 用户学习进度控制
 */
@RestController
@RequestMapping("/rate")
public class RateController {

    @Resource
    private RateService rateService;



}
