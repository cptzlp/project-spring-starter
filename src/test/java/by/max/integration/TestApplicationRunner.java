package by.max.integration;


import by.max.spring.database.repository.pool.ConnectionPool;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

@TestConfiguration
public class TestApplicationRunner {

    @MockitoSpyBean(name="connectionPool1")
    private ConnectionPool pool1;
}
