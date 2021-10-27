package pillihuaman.com.BusinessEntity.model;
import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the product database table.
 * 
 */

@Getter
@Setter

@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product  extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_product")
	private int idProduct;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="expiration_date")
	private Date expirationDate;

	@Column(name="id_imagen")
	private BigDecimal idImagen;

	@Column(name="id_price")
	private BigDecimal idPrice;

	@Column(name="id_system")
	private BigDecimal idSystem;

	@Column(name="id_type")
	private BigDecimal idType;

	@Column(name="id_user")
	private BigDecimal idUser;

	private String name;

	public Product() {
		 super();
	}

}