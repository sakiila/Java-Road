package io.kimmking.rpcfx.demo.consumer;

import io.kimmking.rpcfx.client.Rpcfx;

import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpcfxClientApplication {

    public static void main(String[] args) {
        UserService userService = Rpcfx.create(UserService.class, "http://localhost:12345/");
        User user = userService.findById(1);
        System.out.println("find user id=1 from server: " + user.getName());
    }

}
