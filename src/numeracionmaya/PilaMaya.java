package numeracionmaya;
public class PilaMaya {

	int max = 9, top = 0;
	String[] vec = new String[max];
	static final char LINEA = '-';
	static final char PUNTO = '.';

	public void push(String r) {
		if (top < max && validar(r)) {
			vec[top] = r;
			top++;
		} else {
			System.out.println("No hay espacio");
		}
	}

	public String pop() {
		int ultimo = top - 1;
		if (ultimo >= 0) {
			top--;
			return vec[ultimo];
		} else {
			System.out.println("No hay datos.");
			return null;
		}
	}

	private boolean validar(String valor) {
		int lineas = 0, puntos = 0;
		for (int i = 0; i < valor.length(); i++) {
			char caracter = valor.charAt(i);
			if (LINEA == caracter) {
				lineas++;
				continue;
			}
			if (PUNTO == caracter) {
				puntos++;
				continue;
			} else {
				System.out.println("Numero Invalido");
				return false;
			}
		}
		return (lineas <= 3) && (puntos <= 4);
	}

	public String toDecimal(int nivel, String numeroMaya) {
		int cantidad = 0;
		for (int i = 0; i < numeroMaya.length(); i++) {
			char caracter = numeroMaya.charAt(i);
			if (LINEA == caracter) {
				cantidad += 5;
				continue;
			}
			if (PUNTO == caracter) {
				cantidad++;
				continue;
			}
		}
		Double exp = (double) nivel;
		Double resultado = (cantidad * (Math.pow(20, exp)));
		return cantidad + " * " + 20 + "^" + nivel + " = " + resultado;
	}

	public int getTop() {
		return top;
	}

	public String[] listar() {
		return vec;
	}
}