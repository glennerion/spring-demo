package com.example.demo.controllers;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the MathController.
 */
public class MathControllerTest {

    @Test
    public void testAddDouble() throws Exception {
        MathController controller = new MathController();
        assertEquals(7, controller.addDouble(5, 2), 0);
    }

    @Test
    public void testAddPost() throws Exception {
        MathController controller = new MathController();
        assertEquals(17, controller.addPostedValues("0x01", "16"), 0);
        assertEquals(17, controller.addPostedValues("010", "9"), 0);
        assertEquals(17, controller.addPostedValues("0b010", "15"), 0);
    }

    @Test
    public void testAddPostThrowsExceptionIfStringEmpty() {
        MathController controller = new MathController();
        try {
            controller.addPostedValues("1234", null);
            assertTrue(false);
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            controller.addPostedValues("1234", "");
            assertTrue(false);
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            controller.addPostedValues(null, "1234");
            assertTrue(false);
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            controller.addPostedValues("", "1234");
            assertTrue(false);
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    @Test
    public void testAddPostThrowsExceptionIfStringNotNumber() {
        MathController controller = new MathController();
        try {
            controller.addPostedValues("1234", "test");
            assertTrue(false);
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

}