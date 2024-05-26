package com.acciojob.Security.Integration;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {

    @GetMapping("helloAll")
    public String sayHello(){
        return "Hello everyone , Welcome to Security Integretion";
    }


    @GetMapping("helloAdmin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String hiAdmin(){
        return "Welcome Admin, You have been Authenticated";
    }



    @GetMapping("helloUser")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String hiUser(){
        return "Welcome User, You have been Authenticated";
    }

}

