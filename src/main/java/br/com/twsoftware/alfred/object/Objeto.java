
package br.com.twsoftware.alfred.object;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import br.com.twsoftware.alfred.reflexao.Reflexao;
import br.com.twsoftware.alfred.texto.Texto;

public class Objeto{

     /**
      * M�todo para fazer a verifica��o se um objeto � nullo ou branco. A id�ia � que seja gen�rico para todo tipo de objeto.
      * 
      * @param obj
      * @return
      */
     @SuppressWarnings({ "unused", "rawtypes" })
     public static Boolean isBlank(Object obj) {

          try {

               if (obj == null) {

                    return true;

               } else if (obj instanceof Collection) {

                    for (Object o : (Collection) obj) {
                         if (notBlank(o)) {
                              return false;
                         }
                    }

                    return true;

               } else if ((obj instanceof String)) {

                    if (Texto.isBlankOrNull((String) obj)) {

                         return true;
                    }

               } else if ((obj instanceof Number)) {

                    if (obj == null) {

                         return true;
                    }

               } else if (obj instanceof Boolean) {

                    return false;

               } else if (obj.getClass().isArray()) {

                    int length = Array.getLength(obj);
                    for (int i = 0; i < length; i++) {
                         Object arrayElement = Array.get(obj, i);
                         if (notBlank(arrayElement)) {
                              return false;
                         }
                    }
                    return true;

               } else if ((obj instanceof AbstractMap)) {

                    if (((AbstractMap) obj).isEmpty()) {
                         return true;
                    }

               }

               boolean existeGetters = false;
               boolean nulo = true;

               List<Field> fields = new ArrayList<Field>();
               getAllFields(fields, obj.getClass());

               for (Field field : fields) {
                    if (Reflexao.existeGet(obj, field.getName())) {
                         existeGetters = true;
                         if (notBlank(Reflexao.getValorDoAtributo(obj, field.getName()))) {
                              nulo = false;
                              break;
                         }
                    }
               }

               if (existeGetters && nulo) {
                    return true;
               }

          } catch (Exception e) {
               e.printStackTrace();
          }

          return false;

     }

     public static List<Field> getAllFields(List<Field> fields, Class<?> type) {

          for (Field field : Arrays.asList(type.getDeclaredFields())) {

               if (field.getType().getName().equalsIgnoreCase(type.getName())) {

                    if (!field.getType().getName().equalsIgnoreCase(type.getName())) {
                         fields.add(field);
                    }

               } else {
                    fields.add(field);
               }

          }

          if (type.getSuperclass() != null) {
               fields = getAllFields(fields, type.getSuperclass());
          }

          return fields;
     }

     /**
      * M�todo que faz a nega��o do m�todo isBlank. A id�ia � que seja gen�rico para todo tipo de objeto.
      * 
      * @param obj
      * @return
      */

     public static Boolean notBlank(Object obj) {

          return !isBlank(obj);
     }

     public static Boolean notBlank(Object... objs) {

          if (objs == null) {
               return false;
          }

          for (Object o : objs) {
               if (isBlank(o)) {
                    return false;
               }
          }
          return true;

     }

     public static Boolean isBlank(Object... objs) {

          if (objs == null) {
               return true;
          }

          for (Object o : objs) {
               if (!isBlank(o)) {
                    return false;
               }
          }

          return true;
     }

     public static <E> Object or(E o1, E o2) {

          if (Objeto.notBlank(o1)) {
               return o1;
          }
          return o2;
     }

     public static <E> E coalesce(E o1, E o2) {

          if (Objeto.notBlank(o1)) {
               return o1;
          }
          return o2;
     }

     @SafeVarargs
     public static <E> E coalesce(E... itens) {

          for (E i : itens)
               if (Objeto.notBlank(i))
                    return i;
          return null;
     }

}
