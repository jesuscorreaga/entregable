import java.util.Scanner;

public class conversiones {
  static Scanner sc = new Scanner(System.in);
  static String continuar = "S";

  public static void main(String[] args) {
    while (continuar.equalsIgnoreCase("S")) {
      menu();
    }
    System.out.println("👋 Gracias por usar el conversor!");
  }

  public static void menu() {
    int opmenu;
    System.out.println("1. Convertir decimales a binarios.");
    System.out.println("2. Convertir binarios a decimales.");
    System.out.println("3. Convertir decimales a octales.");
    System.out.println("4. Convertir octales a decimales.");
    System.out.println("5. Convertir decimales a hexadecimales.");
    System.out.println("6. Convertir hexadecimales a decimales.");
    System.out.println("7. Convertir romanos a decimales.");
    System.out.println("8. Convertir decimales a romanos.");
    System.out.println("Escoja una opción del 1 al 8.");
    opmenu = sc.nextInt();
    sc.nextLine(); // Limpia el buffer
    switch (opmenu) {
      case 1:
        decimalesABinario();
        break;
      case 2:
        binariosADecimal();
        break;
      case 3:
        decimalAOctal();
        break;
      case 4:
        octalAdecimal();
        break;
      case 5:
        decimalAHexadecimal();
        break;
      case 6:
        hexaADecimal();
        break;
      case 7:
        romanosADecimal();
        break;
      case 8:
        decimalesARomanos();
        break;
      default:
        System.out.println("Ingrese una opción valida.");
    }
    preguntarContinuar();
  }

  public static void preguntarContinuar() {
    System.out.println("\n¿Desea realizar otra conversión? (S/N)");
    continuar = sc.nextLine().toUpperCase();
    if (!continuar.equals("S")) {
      continuar = "N";
    }
    System.out.println("--------------------------------");
  }

  public static void decimalesABinario() {
    System.out.println("Ingrese un número decimal: ");
    int num = sc.nextInt();
    sc.nextLine();

    if (num == 0) {
      System.out.println("El número binario es 0.");
      return;
    }

    String binario = "";
    while (num > 0) {
      int residuo = num % 2;
      binario = residuo + binario;
      num /= 2;
    }
    System.out.println("El número en binario es: " + binario);
  }

  public static void binariosADecimal() {

    System.out.print("Ingrese un número en binario: ");
    String binario = sc.nextLine();

    // Verificar que solo contenga 0 y 1
    if (!binario.matches("[01]+")) {
      System.out.println("Error: el número ingresado no es binario (solo puede contener 0 y 1).");
    } else {
      int decimal = 0;
      int potencia = 1; // representa 2^0, 2^1, 2^2...

      // Recorremos el número de derecha a izquierda
      for (int i = binario.length() - 1; i >= 0; i--) {
        char bit = binario.charAt(i);

        if (bit == '1') {
          decimal += potencia; // sumamos la potencia actual si hay un
        }

        potencia *= 2; // pasamos a la siguiente potencia de 2
      }

      System.out.println("✅ El número binario " + binario + " en decimal es: " + decimal);
    }

  }

  public static void decimalAOctal() {
    System.out.println("Ingrese un número para convertirlo de decimal a octal.");
    int num = sc.nextInt();
    sc.nextLine();

    if (num == 0) {
      System.out.println("El número es 0.");
      return;

    }

    String octal = "";
    while (num > 0) {
      int residuo = num % 8;
      octal = residuo + octal;
      num /= 8;
    }
    System.out.println("El número en octal es: " + octal);
  }

  public static void octalAdecimal() {
    System.out.println("Ingrese un número octal para convertirlo en decimal.");
    String octal = sc.nextLine();

    int decimal = 0;
    int potencia = 1; // Primero 8^0
    boolean valido = true;

    // Recorrer el número de derecha a izquierda
    for (int i = octal.length() - 1; i >= 0; i--) {
      char digito = octal.charAt(i);
      int valor = Character.getNumericValue(digito);

      // Validar que el digito sea valido(0-7)
      if (valor < 0 || valor > 7) {
        System.out.println("El número no es octal.");
        valido = false;
        break;

      }
      decimal += valor * potencia;
      potencia *= 8;
    }
    if (valido) {
      System.out.println("El número decimal es: " + decimal);
    }
  }

  public static void decimalAHexadecimal() {
    System.out.println("Ingrese un número para convertirlo de decimal a hexadecimal:");
    int num = sc.nextInt();
    sc.nextLine();

    if (num == 0) {
      System.out.println("El número en hexadecimal es: 0");
      return;
    }

    String hexa = "";

    while (num > 0) {
      int residuo = num % 16;
      char digito;

      // Si el residuo es 10-15, convertirlo en A-F
      if (residuo < 10) {
        digito = (char) ('0' + residuo);
      } else {
        digito = (char) ('A' + (residuo - 10));
      }

      hexa = digito + hexa; // concatenar al inicio
      num /= 16;
    }

    System.out.println("El número en hexadecimal es: " + hexa);
  }

  public static void hexaADecimal() {
    System.out.print("Ingrese un número hexadecimal: ");
    String hexa = sc.nextLine().toUpperCase(); // Pasamos a mayúsculas por consistencia

    int decimal = 0;
    int potencia = 1; // 16^0 al inicio
    boolean valido = true;

    // Recorremos el número de derecha a izquierda
    for (int i = hexa.length() - 1; i >= 0; i--) {
      char digito = hexa.charAt(i);
      int valor;

      // Determinar el valor numérico del dígito
      if (digito >= '0' && digito <= '9') {
        valor = digito - '0'; // convierte '0'-'9' a 0-9
      } else if (digito >= 'A' && digito <= 'F') {
        valor = 10 + (digito - 'A'); // convierte 'A'-'F' a 10-15
      } else {
        System.out.println("Error: el número ingresado no es hexadecimal.");
        valido = false;
        break;
      }

      decimal += valor * potencia;
      potencia *= 16;
    }

    if (valido) {
      System.out.println("El número en decimal es: " + decimal);
    }
  }

  public static void romanosADecimal() {
    System.out.println("Ingrese un número romano:");
    String romano = sc.nextLine().toUpperCase();

    int decimal = 0;

    for (int i = 0; i < romano.length(); i++) {
      int valorActual = valorRomano(romano.charAt(i));
      if (valorActual == 0) {
        System.out.println("Número romano inválido.");
        return;
      }

      int valorSiguiente = 0;
      if (i + 1 < romano.length()) {
        valorSiguiente = valorRomano(romano.charAt(i + 1));
      }

      if (valorActual < valorSiguiente) {
        decimal -= valorActual;
      } else {
        decimal += valorActual;
      }
    }

    System.out.println("El número en decimal es: " + decimal);
  }

  public static int valorRomano(char c) {
    switch (c) {
      case 'I':
        return 1;
      case 'V':
        return 5;
      case 'X':
        return 10;
      case 'L':
        return 50;
      case 'C':
        return 100;
      case 'D':
        return 500;
      case 'M':
        return 1000;
      default:
        return 0;
    }
  }

  public static void decimalesARomanos() {

    // Pedimos al usuario que ingrese un número decimal
    System.out.println("Ingrese un número decimal (1 a 3999)");
    int num = sc.nextInt();
    sc.nextLine();

    // Validar el rango de los números romanos clásicos del 1 al 3999

    if (num < 1 || num > 3999) {
      System.out.println("Error no pertenece a un número romano");
      return;
    }

    int[] valores = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    String[] romanos = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

    // Variable para construir el resultado
    String resultado = "";

    // Recorrer los valores de mayor a menor

    for (int i = 0; i < valores.length; i++) {
      while (num >= valores[i]) {
        num -= valores[i];
        resultado += romanos[i];
      }
    }
    System.out.println("El número en romano es: " + resultado);
  }
}
