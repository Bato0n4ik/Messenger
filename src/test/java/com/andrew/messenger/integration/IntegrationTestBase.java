package com.andrew.messenger.integration;

import com.andrew.messenger.integration.annotation.IT;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@IT
@Sql({"classpath:sql/insert.sql"})
public abstract class IntegrationTestBase {

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));

    @BeforeAll
    static void runContainer(){
        container.start();
    }



    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry. add("spring.datasource.url", container::getJdbcUrl);
    }
}
