package com.github.thiagonego.alfred.texto;

class NivelRomano {
  private String nivel_I;
  private String nivel_V;
  private String nivel_X;

  public String getNivel_I() {
    return nivel_I;
  }

  public void setNivel_I(String nivel_I) {
    this.nivel_I = nivel_I;
  }

  public String getNivel_V() {
    return nivel_V;
  }

  public void setNivel_V(String nivel_V) {
    this.nivel_V = nivel_V;
  }

  public String getNivel_X() {
    return nivel_X;
  }

  public void setNivel_X(String nivel_X) {
    this.nivel_X = nivel_X;
  }

  public NivelRomano(String nivel_I, String nivel_V, String nivel_X) {
    this.nivel_I = nivel_I;
    this.nivel_V = nivel_V;
    this.nivel_X = nivel_X;
  }
}
