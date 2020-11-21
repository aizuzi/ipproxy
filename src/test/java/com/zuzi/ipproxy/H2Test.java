package com.zuzi.ipproxy;


import com.zuzi.ipproxy.dao.UserDao;
import com.zuzi.ipproxy.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class H2Test {
    @Autowired
    private UserDao H2Service;
    @Test
    public void testGetUserByName(){
//        User user=H2Service.findByName("zx");
//        System.out.println("查询到用户名为："+user.getName()+"的电话为："+user.getPhone());
    }
    @Test
    public void testGetUserAll(){
        Iterable<User>userIterable=H2Service.findAll();
        for(User user:userIterable){
            System.out.println("查询到用户名为："+user.getName()+"的电话为："+user.getPhone());
        }
    }

}