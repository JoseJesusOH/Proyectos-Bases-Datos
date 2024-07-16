package VO;

public class ProductoVO {

/*Todo los atributos*/
    int codigo;
    String nombre;
    double precio;
    String marca;

public ProductoVO(){}

/*Todo los codigos get*/
    public int getCodigo(){
        return codigo;
    }
    public String getNombre(){
        return nombre;
    }
    public double getPrecio(){
        return precio;
    }
    public String getMarca(){
        return marca;
    }


/*Todo los codigos set*/
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }

}
