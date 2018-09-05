
package com.github.thiagonego.alfred.testes.object;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import junit.framework.Assert;

import org.junit.Test;

import com.github.thiagonego.alfred.object.Objeto;

public class ObjetoTest{

     @Test
     public void testaNulos() { 

          List<String> list = new ArrayList<String>();
          list.add("thiago");

          HashMap<String, String> mapa = new HashMap<String, String>();
          mapa.put("1", "1");

          List<Object> vals = new ArrayList<Object>();
          vals.add("");
          vals.add(2);
          vals.add(null);

          Assert.assertTrue(Objeto.isBlank(new Vector<String>()));
          Assert.assertTrue(Objeto.isBlank(null));
          Assert.assertTrue(Objeto.isBlank(""));
          Assert.assertTrue(Objeto.isBlank(new ArrayList<String>()));
          Assert.assertTrue(Objeto.isBlank(new HashMap<String, String>()));
          
          Assert.assertTrue(Objeto.isBlank(new PessoaTest()));
          Assert.assertTrue(Objeto.isBlank(new PessoaTest("", null)));
          Assert.assertTrue(Objeto.isBlank(new PessoaTest(null, null)));
          Assert.assertTrue(Objeto.isBlank(new PessoaTest(null, ""), new ArrayList<String>()));
          Assert.assertTrue(Objeto.isBlank(new PessoaTest(null, null, new PessoaTest("nome", "email"))));
          Assert.assertTrue(Objeto.notBlank(new PessoaTest("nome", null, new PessoaTest("nome", "email"))));
          Assert.assertTrue(Objeto.isBlank(null, "", new PessoaTest("", ""), null));
          Assert.assertTrue(Objeto.isBlank("", null, new ArrayList<String>(), new PessoaTest()));
          
          Assert.assertTrue(Objeto.isBlank(null, new String[] { "", null }));
          Assert.assertTrue(Objeto.isBlank(null, new Integer[] { null, null }));
          Assert.assertTrue(Objeto.notBlank(1));
          Assert.assertTrue(Objeto.notBlank("teste"));
          Assert.assertTrue(Objeto.notBlank("list"));
          Assert.assertTrue(Objeto.notBlank(new PessoaTest("1", null)));
          Assert.assertTrue(Objeto.notBlank(new PessoaTest(null, "1")));
          Assert.assertTrue(Objeto.notBlank(mapa));
          Assert.assertTrue(Objeto.notBlank(list));
          Assert.assertTrue(Objeto.notBlank("1", 2, list, new PessoaTest("asdasd", "asdadsa")));
          Assert.assertTrue(Objeto.notBlank(new java.util.Date()));
          Assert.assertTrue(Objeto.notBlank(new java.sql.Date(new Date().getTime())));
          Assert.assertTrue(Objeto.notBlank(Calendar.getInstance()));
          
          Assert.assertFalse(Objeto.notBlank(null, "", new PessoaTest("", ""), true));
          Assert.assertFalse(Objeto.notBlank(null, new Boolean("")));
          Assert.assertFalse(Objeto.notBlank(null, new String[] { "2", null }));
          Assert.assertFalse(Objeto.notBlank(null, new String[] { null, "1" }));
          Assert.assertFalse(Objeto.notBlank(2, new String[] { null, null }));
          Assert.assertFalse(Objeto.notBlank(null, new Integer[] { 1, null }));
          Assert.assertFalse(Objeto.notBlank(null, new Integer[] { 1, 2 }));
          Assert.assertFalse(Objeto.notBlank(null, new Integer[] { null, 2 }));
          Assert.assertFalse(Objeto.notBlank(2, new Integer[] { null, null }));
          

     }
     
     @Test
     public void primitiveArrays() {
          Assert.assertFalse(Objeto.isBlank(new boolean[]{ true, false}));
     }

     @Test
     public void testaOr() {

          Assert.assertEquals(1, Objeto.or("", 1));
          Assert.assertEquals(1, Objeto.or(1, ""));
          Assert.assertEquals(1, Objeto.or(null, 1));
          Assert.assertEquals(1, Objeto.or(1, null));
          Assert.assertEquals(1, Objeto.or(1, 2));
     }
     
     @Test
     public void testeHeranca(){
          
          PessoaFisicaTest pessoa = new PessoaFisicaTest();
          pessoa.setEmail("thiagonego@gmail.com");
          
          Assert.assertTrue(Objeto.notBlank(pessoa));
          Assert.assertTrue(Objeto.isBlank(new PessoaFisicaTest()));
          Assert.assertTrue(Objeto.notBlank(new PessoaFisicaTest("thiago", null)));
          Assert.assertTrue(Objeto.notBlank(new PessoaFisicaTest("thiago", "fisica")));
          Assert.assertTrue(Objeto.isBlank(new PessoaFisicaTest(null, null)));
          
          pessoa = new PessoaFisicaTest();
          pessoa.setEmail(null);
          pessoa.setNome("");
          Assert.assertTrue(Objeto.isBlank(pessoa));
          
     }
     
     @Test
     public void testeCoalesce() {

          Assert.assertEquals(1, Objeto.coalesce("", 1));
          Assert.assertEquals(1, Objeto.coalesce(1, ""));
          Assert.assertEquals(Integer.valueOf(1), Objeto.coalesce(null, 1));
          Assert.assertEquals(Integer.valueOf(1), Objeto.coalesce(1, null));
          Assert.assertEquals(Integer.valueOf(1), Objeto.coalesce(1, 2));
          
          Assert.assertEquals(BigDecimal.valueOf(1), Objeto.coalesce(null, BigDecimal.ONE));
          Assert.assertEquals(BigDecimal.valueOf(1), Objeto.coalesce(BigDecimal.ONE, null));
          Assert.assertEquals(BigDecimal.valueOf(1), Objeto.coalesce(BigDecimal.ONE, 2));
          
          
          Assert.assertEquals(1, Objeto.coalesce("", 1, null, null, 2));
          Assert.assertEquals(1, Objeto.coalesce(1, "", "", null));
          Assert.assertEquals(Integer.valueOf(1), Objeto.coalesce(null, 1, null, 2, 5));
          Assert.assertEquals(Integer.valueOf(1), Objeto.coalesce(1, null, 5, null, 8));
          Assert.assertEquals(Integer.valueOf(1), Objeto.coalesce(null, null, null, 1));
          Assert.assertNull(Objeto.coalesce(null, null, null));
          
     }

}
