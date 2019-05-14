package com.eshop.config;

import com.eshop.exception.LoginException;
import com.eshop.exception.NoAddressException;
import com.eshop.exception.NoProductInBasketException;
import org.postgresql.util.PSQLException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import java.lang.ClassCastException;
import java.sql.SQLException;
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

    @ExceptionHandler(LoginException.class)
    public ModelAndView handleMyException3(LoginException mex) {
        ModelAndView model = new ModelAndView();
        model.addObject("errMsg", "Sorry, user with such email already exists." +
                "Please, enter another login.");
        model.setViewName("error");
        System.out.println(mex.getCause());
        return model;
    }

    @ExceptionHandler(NoAddressException.class)
    public ModelAndView handleMyException4(NoAddressException mex) {
        ModelAndView model = new ModelAndView();
        model.addObject("errMsg", mex.getMessage());
        model.setViewName("error");
        System.out.println(mex.getCause());
        return model;
    }

    @ExceptionHandler(NoProductInBasketException.class)
    public ModelAndView handleMyException4(NoProductInBasketException mex) {
        ModelAndView model = new ModelAndView();
        model.addObject("errMsg", "You should choose any product first");
        model.setViewName("error");
        System.out.println(mex.getCause());
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

    @ExceptionHandler(SQLException.class)
    public ModelAndView handleExceptionFromDB(SQLException ex) {
        Stream.of(ex.getStackTrace()).forEach(System.out::println);
        System.out.println(ex.getMessage());
        ModelAndView model = new ModelAndView();
        model.addObject("errMsg", "Can't delete category with existing products");
        model.setViewName("error");
        return model;
    }
}
