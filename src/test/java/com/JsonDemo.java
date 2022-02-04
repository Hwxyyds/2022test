package com;

import com.alibaba.fastjson.JSON;


public class JsonDemo {
    public static void main(String[] args) {
        //JAVA对象转化为Json数据
        User user = new User();
        user.setAge(11);
        user.setUsername("admin");
        String jsonString = JSON.toJSONString(user);
        System.out.println(jsonString);//{"age":11,"username":"admin"}

        //Json数据转化为Java对象
        User u = JSON.parseObject("{\"age\":11,\"username\":\"admin\"}", User.class);
        System.out.println(u);
    }

}
