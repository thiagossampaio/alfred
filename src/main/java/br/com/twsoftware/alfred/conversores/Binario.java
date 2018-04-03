/*
 *  This file is part of Alfred Library.
 *
 *  Alfred Library is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Alfred Library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Alfred Library.  If not, see <http://www.gnu.org/licenses/>.
 */

package br.com.twsoftware.alfred.conversores;

import java.util.Base64;

/**
 * Conversor de tipos bin�rios.
 * 
 * @author Marlon Silva Carvalho
 * @since 11/06/2009
 */
final public class Binario{

     /**
      * Converter um array de bytes em uma representa��o em Base 64.
      * 
      * @param bytes
      * Bytes.
      * @return String contendo a representa��o em Base 64.
      */
     public static String codificarBase64(byte[] bytes) {
          
          return Base64.getEncoder().encodeToString(bytes);
          
     }

     /**
      * Decodificar uma String em Base64 para bin�rio.
      * 
      * @param base
      * Base64.
      * @return Bin�rio.
      */
     public static byte[] decodificarBase64(String base) {

          return Base64.getDecoder().decode(base);
     }

}