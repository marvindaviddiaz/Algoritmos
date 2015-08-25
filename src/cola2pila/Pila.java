package cola2pila;
public class Pila {

	int max = 256, top = 0;
	char[] vec = new char[max];

	public void push(char r) {
		if (top < max) {
			vec[top] = r;
			top++;
		} else {
			System.out.println("No hay espacio");
		}
	}

	public char pop() {
		int ultimo = top - 1;
		if (ultimo >= 0) {
			top--;
			return vec[ultimo];
		} else {
			System.out.println("No hay datos.");
		}
		return '-';
	}

	public int getTop() {
		return top;
	}
	
	public char[] listar(){
		return vec;
	}
	
}