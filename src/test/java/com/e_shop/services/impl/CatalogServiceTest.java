//package com.e_shop.services.impl;
//
//import com.e_shop.domain.Product;
//import com.e_shop.enums.ProductCategory;
//import com.e_shop.services.ProductService;
//import org.junit.Before;
//import org.junit.Test;
//import static org.mockito.Mockito.*;
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.Assert.*;
//
//public class CatalogServiceTest {
//
//    ProductService mockProductService = mock(ProductServiceImpl.class);
//    private CatalogService service = new CatalogService(mockProductService);
//    private List<Product> productListPrice = new ArrayList<>();
//    private List<Product> productListBrand = new ArrayList<>();
//    private List<Product> productListColour = new ArrayList<>();
//    private List<Product> commonList = new ArrayList<>();
//
//    @Before
//    public void prepareForTest() {
//        Product mockProductPrice = mock(Product.class);
//        Product mockProductBrand = mock(Product.class);
//        Product mockProductColour = mock(Product.class);
//        productListBrand.add(mockProductBrand);
//        productListColour.add(mockProductColour);
//        productListPrice.add(mockProductPrice);
//        commonList.add(mockProductPrice);
//        commonList.add(mockProductBrand);
//        commonList.add(mockProductColour);
//        when(mockProductService.getAllProductsByPrice(0.0, 50000.0, "PHONE")).thenReturn(productListPrice);
//        when(mockProductService.getAllProductsByBrand("Apple", "PHONE")).thenReturn(productListBrand);
//        when(mockProductService.getAllProductsByColour("black", "PHONE")).thenReturn(productListColour);
//        when(mockProductService.getAllProductsByCategory(ProductCategory.PHONE)).thenReturn(commonList);
//    }
//
//    @Test
//    public void getDataForPostCatalogTestPrice() {
//        assertEquals(service.getDataForPostCatalog(0.0, 50000.0, null, null, "/Phone"), productListPrice);
//    }
//
//    @Test
//    public void getDataForPostCatalogTestColour() {
//        assertEquals(service.getDataForPostCatalog(null, null, null, "black", "/Phone"), productListColour);
//    }
//
//    @Test
//    public void getDataForPostCatalogTestBrand() {
//        assertEquals(service.getDataForPostCatalog(null, null, "Apple", null, "/Phone"), productListBrand);
//    }
//}