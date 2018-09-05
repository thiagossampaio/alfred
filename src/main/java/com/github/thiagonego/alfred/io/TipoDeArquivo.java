package com.github.thiagonego.alfred.io;

public enum TipoDeArquivo {
  GIF("image/gif"), JPG("image/jpeg"), JPEG("image/pjpeg"), DOC(
      "application/msword"), ZIP("application/x-zip-compressed"), PDF(
      "application/pdf"), HTML("text/html"), XML("text/xml"), BMP("image/bmp"), TXT(
      "text/plain"), BIN("application/octet-stream"), RTF("application/msword"), XLS(
      "application/vnd.ms-excel"), P7S("application/pkcs7-signature"), P7SD(
      "application/pkcs7-data"), ODT("application/vnd.oasis.opendocument.text"),
      CER("application/x-x509-ca-cert"), P12("application/x-pkcs12");
  private String tipoDeArquivo;

  private TipoDeArquivo(String tipoDeArquivo) {
    this.tipoDeArquivo = tipoDeArquivo;
  }

  public String getTipoDeArquivo() {
    return tipoDeArquivo;
  }

  public void setTipoDeArquivo(String tipoDeArquivo) {
    this.tipoDeArquivo = tipoDeArquivo;
  }

  public static void main(String[] args) {
    mostra(TipoDeArquivo.DOC);
  }

  private static void mostra(TipoDeArquivo doc2) {
    System.out.println(doc2);
    System.out.println(doc2.getTipoDeArquivo());
  }
}