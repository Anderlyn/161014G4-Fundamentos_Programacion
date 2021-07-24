package proyectofinal;


public class Caja {
    Factura[][] caja;
    public Caja(){
        this.caja = new Factura[3][3];
    }
    public void cerrar_caja1(){
        for(int i = 0; i < this.caja[0].length; i++){
            this.caja[0][i] = null;
        }
    }
    public void cerrar_caja2(){
        for(int i = 0; i < this.caja[1].length; i++){
            this.caja[1][i] = null;
        }
    }
    public void cerrar_caja3(){
        for(int i = 0; i < this.caja[2].length; i++){
            this.caja[2][i] = null;          
        }
    }
}
