package pillihuaman.com.model.request;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.googlecode.jmapper.annotations.JGlobalMap;

import lombok.Getter;
import lombok.Setter;


@JGlobalMap
@Getter
@Setter
public class ReqProduct {
	
	private int idProduct;
	private Timestamp createDate;
	private String description;
	private Date expirationDate;
	private BigDecimal idImagen;
	private BigDecimal idPrice;
	private BigDecimal idSystem;
	private BigDecimal idType;
	private BigDecimal idUser;
	private String name;
	private String status;
	private Timestamp updateDate;
	private String userCreate;
	private String userModify;
	
}


