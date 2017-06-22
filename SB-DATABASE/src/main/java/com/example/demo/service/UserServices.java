
package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;


public interface UserServices {
      public List<User> getAllUser();
       public User getUser(String id);
        public void addUser(User user);
       public void updateUser(String id, User user);
       public void deleteUser(String id);
   
}
