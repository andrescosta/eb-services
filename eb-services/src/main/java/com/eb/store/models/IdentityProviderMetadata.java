package com.eb.store.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdentityProviderMetadata {
	
	@Column(name="idp_type")
	private IdentityProviderType type;
	
	public String getSamlMetadataURI() {
		return samlMetadataURI;
	}

	public void setSamlMetadataURI(String samlMetadataURI) {
		this.samlMetadataURI = samlMetadataURI;
	}

	@Column(name="saml_metadata_uti")
	String samlMetadataURI;

	public IdentityProviderType getType() {
		return type;
	}

	public void setType(IdentityProviderType type) {
		this.type = type;
	}
	
}

