package entidades;

import entidades.Producto;
import entidades.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-03-10T14:27:25")
@StaticMetamodel(RelacionProductoVenta.class)
public class RelacionProductoVenta_ { 

    public static volatile SingularAttribute<RelacionProductoVenta, Float> precio;
    public static volatile SingularAttribute<RelacionProductoVenta, Venta> venta;
    public static volatile SingularAttribute<RelacionProductoVenta, Long> id;
    public static volatile SingularAttribute<RelacionProductoVenta, Integer> cantidad;
    public static volatile SingularAttribute<RelacionProductoVenta, Producto> producto;
    public static volatile SingularAttribute<RelacionProductoVenta, Float> importe;

}