package entidades;

import entidades.Cliente;
import entidades.RelacionProductoVenta;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-03-10T14:27:25")
@StaticMetamodel(Venta.class)
public class Venta_ { 

    public static volatile SingularAttribute<Venta, Calendar> fecha;
    public static volatile SingularAttribute<Venta, Cliente> cliente;
    public static volatile SingularAttribute<Venta, Float> descuento;
    public static volatile SingularAttribute<Venta, Long> id;
    public static volatile ListAttribute<Venta, RelacionProductoVenta> productos;
    public static volatile SingularAttribute<Venta, Float> montoTotal;

}