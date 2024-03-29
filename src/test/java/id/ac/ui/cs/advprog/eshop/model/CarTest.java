package id.ac.ui.cs.advprog.eshop.model;




import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;




import static org.junit.jupiter.api.Assertions.*;




class CarTest {

    Car car;




    @BeforeEach

    void setUp() {

        this.car = new Car();

        this.car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");

        this.car.setCarName("Porsche");

        this.car.setCarColor("Red");

        this.car.setCarQuantity(100);

    }




    @Test

    void testGetCarId() {

        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.car.getCarId());

        assertNotEquals("88fa7a93-1aeb-40fd-aafb-8823e9ab178c", this.car.getCarId());

    }




    @Test

    void testGetCarName() {

        assertEquals("Porsche", this.car.getCarName());

        assertNotEquals("McLaren", this.car.getCarName());

    }




    @Test

    void testGetCarColor() {

        assertEquals("Red", this.car.getCarColor());

        assertNotEquals("Black", this.car.getCarColor());

    }




    @Test

    void testGetCarQuantity() {

        assertEquals(100, this.car.getCarQuantity());

        assertNotEquals(50, this.car.getCarQuantity());

    }

}