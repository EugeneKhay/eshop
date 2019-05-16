package com.eshop.config;

import com.eshop.exception.LoginException;
import com.eshop.exception.NoAddressException;
import com.eshop.exception.NoProductInBasketException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import java.lang.ClassCastException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static String ATTR_NAME = "errMsg";
    private static String VIEW_NAME = "error";
    private Logger logger = Logger.getLogger("logger");

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleMyException2(IllegalArgumentException mex) {
        ModelAndView model = new ModelAndView();
        model.addObject(ATTR_NAME, "Sorry, not enough amount of product");
        logger.info("Attempt to login with exisring email");
        logger.log(Level.WARNING, "Not enough amount of product");
        model.setViewName(VIEW_NAME);
        return model;
    }

    @ExceptionHandler(LoginException.class)
    public ModelAndView handleMyException3(LoginException mex) {
        ModelAndView model = new ModelAndView();
        model.addObject(ATTR_NAME, "Sorry, user with such email already exists." +
                "Please, enter another login.");
        model.setViewName(VIEW_NAME);
        logger.info("Attempt to login with exisring email");
        return model;
    }

    @ExceptionHandler(NoAddressException.class)
    public ModelAndView handleMyException4(NoAddressException mex) {
        ModelAndView model = new ModelAndView();
        model.addObject(ATTR_NAME, mex.getMessage());
        model.setViewName(VIEW_NAME);
        logger.info("Attempt to register without address");
        return model;
    }

    @ExceptionHandler(NoProductInBasketException.class)
    public ModelAndView handleMyException4(NoProductInBasketException mex) {
        ModelAndView model = new ModelAndView();
        model.addObject(ATTR_NAME, "You should choose any product first");
        model.setViewName(VIEW_NAME);
        logger.info("Attempt to make with empty basket");
        return model;
    }

    @ExceptionHandler(ClassCastException.class)
    public ModelAndView handleMyException(ClassCastException mex) {
        ModelAndView model = new ModelAndView();
        model.addObject(ATTR_NAME, "Please, sign in first!");
        model.setViewName(VIEW_NAME);
        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        logger.log(Level.ALL, ex.getMessage());
        ModelAndView model = new ModelAndView();
        model.addObject(ATTR_NAME, "Sorry, something went wrong :(");
        model.setViewName(VIEW_NAME);
        return model;
    }

    @ExceptionHandler(SQLException.class)
    public ModelAndView handleExceptionFromDB(SQLException ex) {
        logger.log(Level.ALL, ex.getMessage());
        ModelAndView model = new ModelAndView();
        model.addObject(ATTR_NAME, "Can't delete category with existing products");
        model.setViewName(VIEW_NAME);
        return model;
    }
}
