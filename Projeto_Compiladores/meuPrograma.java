import java.util.Scanner;
public class meuPrograma {
public static void main(String[] args) {
Scanner _scTrx = new Scanner(System.in);
int a;
int b;
String x;
String y;
a = _scTrx.nextInt();
b = _scTrx.nextInt();
System.out.println(a);
if (a>5) {
System.out.println("maior que 5");
}else {
System.out.println("menor que 5");
}
System.out.println("oi agora vamos testar outro");
if (b>=0) {
System.out.println("b positivo");
}else {
System.out.println("b negativo");
}
}
}
