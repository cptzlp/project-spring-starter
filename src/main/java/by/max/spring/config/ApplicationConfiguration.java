package by.max.spring.config;


import by.max.spring.database.repository.UserRepository;
import by.max.spring.database.repository.pool.ConnectionPool;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;





@Configuration
public class ApplicationConfiguration {

    @Bean("connectionPool1")
    public ConnectionPool connectionPool(){
        return new ConnectionPool("mySql", "123", 1, "url");
    }


    /*@Bean
    @Profile("prod&web")
    public UserRepository userRepository(){
        return new UserRepository(connectionPool());
    }*/
}
