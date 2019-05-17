package com.eshop.dao;

import com.eshop.domain.ShopAddress;

import java.util.Set;

public interface ShopAddressDAO {

    void saveShop(ShopAddress address);

    Set<ShopAddress> getAllShops();

    ShopAddress getShopById(Integer id);
}
