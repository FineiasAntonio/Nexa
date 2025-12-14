package com.nexasolutions.nexa.utils;

import jakarta.persistence.GeneratedValue;
import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.security.cert.PolicyNode;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@IdGeneratorType(UuidV7Generator.class)
public @interface UUIDV7 {


}
