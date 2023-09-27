package sunat.gob.pe.lavanderia.controller.enums;

public enum EstadoEnum {

  INACTIVO(0),
  ACTIVO(1),
  BLOQUEADO(2),
  BAJA(3),
  ELIMINADO(4);

  private int valor;

  EstadoEnum(int valor) {
    this.valor = valor;
  }

  public int getValor() {
    return valor;
  }

  public static String getStringValueFromInt(int i) {
    for (EstadoEnum estado : EstadoEnum.values()) {
      if (estado.getValor() == i) {
        return estado.toString();
      }
    }

    throw new IllegalArgumentException("el numero dado no se encontro.");
  }

}
