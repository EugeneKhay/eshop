package com.eshop.service.impl;

import com.eshop.domain.Product;
import com.eshop.service.CatalogService;
import com.eshop.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class CatalogServiceTest {

    ProductService mockProductService = mock(ProductServiceImpl.class);
    private CatalogService service = new CatalogServiceImpl(mockProductService);
    private List<Product> productListPrice = new ArrayList<>();
    private List<Product> productListBrand = new ArrayList<>();
    private List<Product> productListColour = new ArrayList<>();
    private List<Product> commonList = new ArrayList<>();

    @Before
    public void prepareForTest() {
        Product mockProductPrice = mock(Product.class);
        Product mockProductBrand = mock(Product.class);
        Product mockProductColour = mock(Product.class);
        productListBrand.add(mockProductBrand);
        productListColour.add(mockProductColour);
        productListPrice.add(mockProductPrice);
        commonList.add(mockProductPrice);
        commonList.add(mockProductBrand);
        commonList.add(mockProductColour);
        when(mockProductService.getAllProductsByPrice(0.0, 50000.0, "Smartphones")).thenReturn(productListPrice);
        when(mockProductService.getAllProductsByBrand("Apple", "Smartphones")).thenReturn(productListBrand);
        when(mockProductService.getAllProductsByColour("black", "Smartphones")).thenReturn(productListColour);
        when(mockProductService.getAllProductsByCategory("Smartphones")).thenReturn(commonList);
    }

    @Test
    public void getDataForPostCatalogTestPrice() {
        assertEquals(service.getDataForPostCatalog(0.0, 50000.0, null, null, "Smartphones"), productListPrice);
    }

    @Test
    public void getDataForPostCatalogTestColour() {
        assertEquals(service.getDataForPostCatalog(null, null, null, "black", "Smartphones"), productListColour);
    }

    @Test
    public void getDataForPostCatalogTestBrand() {
        assertEquals(service.getDataForPostCatalog(null, null, "Apple", null, "Smartphones"), productListBrand);
    }
}