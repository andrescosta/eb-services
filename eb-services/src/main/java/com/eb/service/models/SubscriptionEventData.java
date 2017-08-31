package com.eb.service.models;

import java.util.Collection;

import org.springframework.hateoas.Link;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionEventData {

	public SubscriptionEventData() {
		super();
	}

	private String type;

	@Override
	public String toString() {
		return "SubscriptionEventData [type=" + type + ", marketplace=" + marketplace + ", creator=" + creator
				+ ", flag=" + flag + ", returnUrl=" + returnUrl + ", links=" + links + ", payload=" + payload + "]";
	}

	private MarketPlace marketplace;
	private User creator;
	private String flag;
	private String returnUrl;
	private String applicationUuid;
	private Collection<Link> links;
	public String getApplicationUuid() {
		return applicationUuid;
	}

	public void setApplicationUuid(String applicationUuid) {
		this.applicationUuid = applicationUuid;
	}

	private Payload payload;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MarketPlace getMarketplace() {
		return marketplace;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public Collection<Link> getLinks() {
		return links;
	}

	public void setLinks(Collection<Link> links) {
		this.links = links;
	}

	public String getFlag() {
		return flag;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String isFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public void setMarketplace(MarketPlace marketPlace) {
		this.marketplace = marketPlace;
	}

}