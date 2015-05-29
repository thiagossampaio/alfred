package br.com.twsoftware.alfred.encoder;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Encoder {
  public static String encode(Object objeto) {
    ByteArrayOutputStream arquivo = new ByteArrayOutputStream();
    BufferedOutputStream saida = new BufferedOutputStream(arquivo);
    XMLEncoder encoder = new XMLEncoder(saida);
    encoder.writeObject(objeto);
    encoder.close();
    return arquivo.toString();
  }

  public static Object decode(String xml) {
    ByteArrayInputStream arquivo = new ByteArrayInputStream(xml.getBytes());
    BufferedInputStream entrada = new BufferedInputStream(arquivo);
    XMLDecoder decoder = new XMLDecoder(entrada);
    Object o = decoder.readObject();
    return o;
  }
}
