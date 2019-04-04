package com.e_shop.config;

import com.e_shop.domain.Basket;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {

        Basket basket = (Basket) request.getSession().getAttribute("shop_basket");
        basket.getProductsInBasket().clear();
        request.getSession().setAttribute("shop_basket", basket);
        request.getSession().setAttribute("totalPrice", 0);
        super.onLogoutSuccess(request, response, authentication);
    }
}
