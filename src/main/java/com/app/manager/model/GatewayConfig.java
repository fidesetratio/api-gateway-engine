package com.app.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gateway_config")
public class GatewayConfig {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name="configId")
	private Long configId;

	public Long getConfigId() {
		return configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}
	
}
