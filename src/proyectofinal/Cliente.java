package proyectofinal;


public class Cliente {
    public String nombre;
    public String cedula;
    
    public Cliente(String nombre_temp, String cedula_temp){
        this.nombre = nombre_temp;
        this.cedula = cedula_temp;
    }
    
    public void ModificarCliente(String nombre_temp, String cedula_temp){
        this.nombre = nombre_temp;
        this.cedula = cedula_temp;
    }
}
