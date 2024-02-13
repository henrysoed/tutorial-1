package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(),savedProduct.getProductName());
        assertEquals(product.getProductQuantity(),savedProduct.getProductQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-468e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);
        Product product2 = new Product();
        product2.setProductId("a0f9de46-98b1-437d-a0bf-d0821dde9896");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testSuccessfulEdit(){
        // Make new product
        Product product = new Product();
        product.setProductId("abcde");
        product.setProductName("Pachil");
        product.setProductQuantity(10);
        productRepository.create(product);
        // Change the name & quantity of product
        product = productRepository.findById("abcde");
        product.setProductName("Pachil Baru");
        product.setProductQuantity(8);
        productRepository.save(product);
        // Verify
        product = productRepository.findById("abcde");
        assertEquals(product.getProductId(), "abcde");
        assertEquals(product.getProductName(), "Pachil Baru");
        assertEquals(product.getProductQuantity(), 8);
    }

    @Test
    void testFailedEdit(){

        // Make new product
        Product product = new Product();
        product.setProductId("abcde");
        product.setProductName("Pachil");
        product.setProductQuantity(10);
        productRepository.create(product);

        assertThrows(IllegalArgumentException.class, () -> productRepository.findById("random id"));

        assertDoesNotThrow(() -> productRepository.findById("abcde"));

        // Change the name & quantity of product
        product = productRepository.findById("abcde");
        product.setProductName("Pachil Baru");
        product.setProductQuantity(8);
        productRepository.save(product);

        // Make product that is not saved
        product = new Product();
        product.setProductId("x");
        product.setProductName("x");
        product.setProductQuantity(1);

        Product finalProduct = product;
        assertThrows(IllegalArgumentException.class, () -> productRepository.save(finalProduct));
    }

    @Test
    void testSuccessfulDelete(){
        // Make new product
        Product product = new Product();
        product.setProductId("abcde");
        product.setProductName("Pachil");
        product.setProductQuantity(10);
        productRepository.create(product);
        // Delete product
        productRepository.deleteProductById("abcde");
        // Verify
        Iterator<Product> productIterator = productRepository.findAll();
        while (productIterator.hasNext()){
            assertNotEquals(productIterator.next().getProductId(), "abcde");
        }
    }
    @Test
    void testFailedDelete(){
        // Make new product
        Product product = new Product();
        product.setProductId("abcde");
        product.setProductName("Pachil");
        product.setProductQuantity(10);
        productRepository.create(product);
        // Delete product with unknown id
        assertThrows(IllegalArgumentException.class, () -> productRepository.deleteProductById("randomId"));
        // Make sure the product still there
        String id = productRepository.findAll().next().getProductId();
        assertEquals(id, "abcde");
    }
}