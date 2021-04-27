package org.example.crm.controller;

import org.example.crm.base.BaseController;
import org.example.crm.service.CustomerContactService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("contact")
public class CustomerContactController extends BaseController {
    @Resource
    private CustomerContactService customerContactService;
}
