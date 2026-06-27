package com.example;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class HomeControllerTest {

    @Autowired
    private HomeController homeController;

    @Test
    void controllerShouldBeCreated() {
        assertNotNull(homeController);
    }

    @Test
    void cartPageShouldReturnCartView() {
        Model model = new ExtendedModelMap();
        HttpSession session = new MockHttpSession();

        String viewName = homeController.cart(model, session);

        assertEquals("cart", viewName);
        assertNotNull(model.getAttribute("cartItems"));
    }

    @Test
    void productDetailShouldReturnProductPageForKnownSlug() {
        Model model = new ExtendedModelMap();

        String viewName = homeController.productDetail("sofa-wabi-2-cho", model);

        assertEquals("product-detail", viewName);
        assertNotNull(model.getAttribute("product"));
        assertNotNull(model.getAttribute("backUrl"));
    }

    @Test
    void cartShouldAcceptQuantityWhenAddingProduct() {
        MockHttpSession session = new MockHttpSession();

        String viewName = homeController.addToCart("sofa-wabi-2-cho", 3, session);

        assertEquals("redirect:/product/sofa-wabi-2-cho", viewName);
        assertEquals(3, ((java.util.List<?>) session.getAttribute("cart")).size());
    }

    @Test
    void categoryPageShouldExposeProductCountForSelectedCategory() {
        Model model = new ExtendedModelMap();

        String viewName = homeController.products("phong-khach", null, null, null, null, null, model);

        assertEquals("products", viewName);
        assertEquals(11, model.getAttribute("productCount"));
    }

    @Test
    void categorySearchShouldFilterProductsWithinCategory() {
        Model model = new ExtendedModelMap();

        String viewName = homeController.products("phong-khach", "sofa", null, null, null, null, model);

        assertEquals("products", viewName);
        assertEquals(2, model.getAttribute("productCount"));
        assertEquals("sofa", model.getAttribute("searchQuery"));
    }

    @Test
    void categorySearchShouldFilterByKindAndPriceRange() {
        Model model = new ExtendedModelMap();

        String viewName = homeController.products("phong-khach", "", "bàn", "", "12000000", "20000000", model);

        assertEquals("products", viewName);
        assertEquals(2, model.getAttribute("productCount"));
        assertEquals("bàn", model.getAttribute("selectedKind"));
    }

    @Test
    void servicePageShouldRenderInfoPage() {
        Model model = new ExtendedModelMap();

        String viewName = homeController.servicePage("tu-van-thiet-ke-mien-phi", model);

        assertEquals("info-page", viewName);
        assertEquals("Tư vấn thiết kế miễn phí", model.getAttribute("pageTitle"));
        assertNotNull(model.getAttribute("pageBlocks"));
    }

    @Test
    void brandPageShouldRenderInfoPage() {
        Model model = new ExtendedModelMap();

        String viewName = homeController.brandPage("lien-he", model);

        assertEquals("info-page", viewName);
        assertEquals("Liên hệ", model.getAttribute("pageTitle"));
        assertNotNull(model.getAttribute("pageBlocks"));
    }
}
