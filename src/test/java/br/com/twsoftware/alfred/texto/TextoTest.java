package br.com.twsoftware.alfred.texto;

import org.junit.Test;

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

}
