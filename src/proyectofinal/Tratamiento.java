package proyectofinal;


public class Tratamiento {
    String descripcion;
    double costoBase;
    public Tratamiento(String descripcion_temp, double costoBase_temp){
        this.descripcion = descripcion_temp;
        this.costoBase = costoBase_temp;
    }
    public void ModificarTratamiento(String descripcion_temp, double costoBase_temp){
        this.descripcion = descripcion_temp;
        this.costoBase = costoBase_temp;
    }
    
}
