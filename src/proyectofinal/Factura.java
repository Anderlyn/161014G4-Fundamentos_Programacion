package proyectofinal;


public class Factura {
    String fecha;
    Cliente cliente;
    Tratamiento[] tratamientos;
    Double total;
    
    public Factura(String fecha_temp, Cliente cliente_temp, Tratamiento[] tratamientos_temp){
       this.fecha = fecha_temp;
       this.cliente = cliente_temp;
       this.tratamientos = tratamientos_temp;
       this.total = 0.0;
    }
    
    public double calcularPrecio(){
        this.total = 0.0;
        for(int i=0; i < this.tratamientos.length; i++){
            if(tratamientos[i]!=null){
                this.total += this.tratamientos[i].costoBase;
            }
        }
        return this.total;
    }
}
