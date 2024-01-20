package com.digitalride.digitalride.shared.annotation;

import com.digitalride.digitalride.shared.annotation.testcontainers.PostgresContainer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

@IntegrationTest
@Testcontainers
@Transactional
@ContextConfiguration(initializers = {PostgresContainer.Initializer.class})
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseTest {

}
