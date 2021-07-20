package com.example.taskmanagementapplication.annotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({PARAMETER, TYPE})
@Retention(RUNTIME)
@AuthenticationPrincipal(expression = "id")
public @interface UserId {

}
