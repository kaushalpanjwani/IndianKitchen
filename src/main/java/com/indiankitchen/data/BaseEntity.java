package com.indiankitchen.data;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

public class BaseEntity {
	
	@CreatedDate
	private Date createDate;
	@LastModifiedDate
	private Date updateDate;
	@CreatedBy
	private String createdById;
	@LastModifiedBy
	private String updatedById;
	
	public Date getCreateDate() {
		return createDate == null ? new Date() : createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate == null ? new Date() : updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreatedById() {
		return createdById;
	}
	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}
	public String getUpdatedById() {
		return updatedById == null ? getCreatedById() : updatedById;
	}
	public void setUpdatedById(String updatedById) {
		this.updatedById = updatedById;
	}

}
