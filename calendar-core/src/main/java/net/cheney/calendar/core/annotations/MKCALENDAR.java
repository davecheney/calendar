package net.cheney.calendar.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.cheney.motown.api.Method;
import net.cheney.motown.dispatcher.dynamic.HttpMethod;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@HttpMethod(Method.DELETE)
public @interface MKCALENDAR {

}
