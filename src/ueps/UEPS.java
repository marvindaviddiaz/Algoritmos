package ueps;
import java.util.Date;

public class UEPS {

	int max = 10, top = 0;
	Producto[] vec = new Producto[max];

	public void push(Producto r) {
		if (top < max) {
			vec[top] = r;
			top++;
		} else {
			System.out.println("No hay espacio");
		}
	}

	public Resultado pop(int cant) {
		Resultado r = new Resultado();
		int ultimo = top - 1;
		if (ultimo >= 0) {
			if (vec[ultimo].cantidad <= cant) {
				r.setCantidad(vec[ultimo].cantidad);
				r.setPrecio(vec[ultimo].precio);
				top--;
			} else {
				vec[ultimo].setCantidad(vec[ultimo].getCantidad() - cant);
				r.setCantidad(cant);
				r.setPrecio(vec[ultimo].precio);
			}
		} else {
			System.out.println("No hay datos.");
		}
		return r;
	}

	public Producto[] list() {
		return vec;
	}
}

class Producto {
	public Date fecha;
	public Integer cantidad;
	public Double precio;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}

class Resultado {
	public Integer cantidad = 0;
	public Double precio = 0.0;

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
}