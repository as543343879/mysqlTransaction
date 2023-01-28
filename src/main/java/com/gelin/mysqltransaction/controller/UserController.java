package com.gelin.mysqltransaction.controller;

import java.util.ArrayList;
import java.util.List;

import com.gelin.mysqltransaction.dao.UserRepository;
import com.gelin.mysqltransaction.entity.User;
import com.gelin.mysqltransaction.service.UserTractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class UserController {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private UserTractionService userTractionService;

   @RequestMapping("/getAllUser")
   @ResponseBody
   public List<User> findAll() {
       List<User> list = new ArrayList<User>();
       list = userRepository.findAll();
       return list;
   }

   @RequestMapping("/getByUserName")
   @ResponseBody
   public User getByUserName(String userName) {
       User user = userRepository.findByUserName(userName);
       return user;
   }

    @PostMapping("/addUser")
    @ResponseBody
    public User addUser(String userName,boolean isError) {
        User user = userTractionService.addUser(userName,isError);
        return user;
    }

}

