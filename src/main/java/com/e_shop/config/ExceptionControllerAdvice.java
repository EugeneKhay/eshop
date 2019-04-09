package com.e_shop.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import java.lang.ClassCastException;
import java.util.stream.Stream;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleMyException2(IllegalArgumentException mex) {
        ModelAndView model = new ModelAndView();
        model.addObject("errMsg", "Sorry, not enough amount of product");
        model.setViewName("error");
        return model;
    }

    @ExceptionHandler(ClassCastException.class)
    public ModelAndView handleMyException(ClassCastException mex) {
        ModelAndView model = new ModelAndView();
        model.addObject("errMsg", "Please, sign in first!");
        model.setViewName("error");
        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        Stream.of(ex.getStackTrace()).forEach(System.out::println);
        System.out.println(ex.getMessage());
        ModelAndView model = new ModelAndView();
        model.addObject("errMsg", "Sorry, something went wrong :(");
        model.setViewName("error");
        return model;
    }
}
