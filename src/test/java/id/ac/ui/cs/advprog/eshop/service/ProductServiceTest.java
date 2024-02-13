package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    ProductServiceImpl service;

    @Test
    void testCreateProduct() {
        for (int i = 0; i < 5;i++){
            Product product = new Product();
            product.setProductName(String.valueOf(i+10));
            product.setProductQuantity(i+20);
            service.create(product);
        }

        // Check the product
        List<Product> productList = service.findAll();
        for (int i = 0; i < 5;i++){
            Product product = productList.get(i);

            assertEquals(product.getProductId(), String.valueOf(i+1));
            assertEquals(product.getProductName(), String.valueOf(i+10));
            assertEquals(product.getProductQuantity(), i+20);
        }
    }
    @Test
    void testFindProduct(){
        Product product = new Product();
        product.setProductName("PachilLama");
        product.setProductQuantity(10);
        service.create(product);

        // Find the product
        Product foundProduct =  service.findById("1");
        assertEquals(foundProduct.getProductName(), "PachilLama");

    }

    @Test
    void testSaveProduct(){
        Product product = new Product();
        product.setProductName("PachilLama");
        product.setProductQuantity(10);
        service.create(product);

        // Update product
        product.setProductName("PachilBaru");
        product.setProductQuantity(15);
        service.save(product);

        // Check
        Product foundProduct =  service.findById("1");
        assertEquals(foundProduct.getProductName(), "PachilBaru");
        assertEquals(foundProduct.getProductQuantity(), 15);

    }

    @Test
    void testDeleteProduct(){
        Product product = new Product();
        product.setProductName("PachilLama");
        product.setProductQuantity(12);
        service.create(product);

        // Delete product
        service.deleteProductById(product.getProductId());

        // Check
        assertThrows(IllegalArgumentException.class, () -> service.findById("1"));

    }
}