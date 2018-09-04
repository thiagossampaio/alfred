package com.github.thiagonego.alfred.texto;

import org.junit.Test;

import com.github.thiagonego.alfred.texto.Texto;

import junit.framework.Assert;

public class TextoTest{
     
     @Test
     public void testeReverse() {
          
          Assert.assertEquals("987654321", Texto.reverse("123456789"));
          Assert.assertEquals("111111", Texto.reverse("111111"));
          Assert.assertEquals("888888111111", Texto.reverse("111111888888"));
          Assert.assertEquals("665511", Texto.reverse("115566"));
          Assert.assertEquals("abcde", Texto.reverse("edcba"));
          Assert.assertEquals("(*abcde", Texto.reverse("edcba*("));
          Assert.assertNull(Texto.reverse(""));
          Assert.assertNull(Texto.reverse(null));
     }
     
     @Test
     public void testeIsBlankOrNullOrZero() {
          
          Assert.assertTrue(Texto.isBlankOrNullOrZero(""));
          Assert.assertTrue(Texto.isBlankOrNullOrZero("0000000.0000000"));
          Assert.assertTrue(Texto.isBlankOrNullOrZero("000,000"));
          Assert.assertTrue(Texto.isBlankOrNullOrZero("0,0"));
          Assert.assertTrue(Texto.isBlankOrNullOrZero("000.000.000,000"));
          Assert.assertTrue(Texto.isBlankOrNullOrZero("..,0"));
          Assert.assertFalse(Texto.isBlankOrNullOrZero("..,"));
          Assert.assertFalse(Texto.isBlankOrNullOrZero("000.000.000,001"));
          Assert.assertFalse(Texto.isBlankOrNullOrZero("5"));
          Assert.assertFalse(Texto.isBlankOrNullOrZero("asd"));
          Assert.assertFalse(Texto.isBlankOrNullOrZero("5,0"));
          Assert.assertFalse(Texto.isBlankOrNullOrZero("05,0"));
          Assert.assertFalse(Texto.isBlankOrNullOrZero("0,05"));
          Assert.assertFalse(Texto.isBlankOrNullOrZero("055.05"));
          Assert.assertFalse(Texto.isBlankOrNullOrZero("0.05"));
          
     }

}
