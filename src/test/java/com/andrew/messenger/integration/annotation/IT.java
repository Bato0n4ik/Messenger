package com.andrew.messenger.integration.annotation;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles("test")
@SpringBootTest
@Transactional
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = MessengerApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
public @interface IT {
}
