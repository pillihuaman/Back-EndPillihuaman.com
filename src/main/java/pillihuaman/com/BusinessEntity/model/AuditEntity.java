package pillihuaman.com.BusinessEntity.model;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;

import lombok.Getter;
import lombok.Setter;
import pillihuaman.com.Help.Constants;
import pillihuaman.com.Help.RegisterStatus;


@MappedSuperclass
@Getter
@Setter
public abstract class AuditEntity {

	@Column(nullable = false, name = "status")
	protected String status;
	
	@Column(nullable = false, updatable = false, name = "user_create")
	protected String userCreate;

	@Column(nullable = false, updatable = false, name = "create_date")
	@JsonSerialize(using = InstantSerializer.class)
	@JsonFormat(pattern = Constants.FORMAT_DATE_AUDIT, timezone = Constants.FORMATO_TIMEZONE)
	protected Instant createDate;

	@Column(insertable = false, name = "user_modify")
	protected String userModify;
	
	@Column(insertable = false, name = "update_date")
	protected Instant updateDate;
	
	public void setSegIns(String userCreate, Instant createDate){
		this.status = RegisterStatus.ACTIVE.getCode();
		this.userCreate = userCreate;
		this.createDate = createDate;
	}
	public void setSegUpd(String statusRegister, String userModify, Instant updateDate){
		this.status = statusRegister;
		this.userModify = userModify;
		this.updateDate = updateDate;
	}
	
}
