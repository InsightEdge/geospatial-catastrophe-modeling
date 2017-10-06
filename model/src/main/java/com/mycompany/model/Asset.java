package com.mycompany.model;

import org.openspaces.spatial.SpaceSpatialIndex;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.metadata.index.SpaceIndexType;
import org.openspaces.spatial.shapes.Point;

@SpaceClass
public class Asset {
	private Integer assetId;
	private Integer portfolioId;
	private Point location;
	private String state;
	private Long exposure;
	private Long premium;

	public Asset() {
	}

	public Asset(Integer assetId, Integer portfolioId, String state, Point location, Long exposure, Long premium) {
		this.assetId = assetId;
		this.portfolioId = portfolioId;
		this.state = state;
		this.location = location;
		this.exposure = exposure;
		this.premium = premium;
	}

	@SpaceId(autoGenerate = false)
	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

	@SpaceSpatialIndex
	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	@SpaceIndex(type = SpaceIndexType.EXTENDED)
	public Long getExposure() {
		return exposure;
	}

	public void setExposure(Long appraisal) {
		this.exposure = appraisal;
	}

	@SpaceIndex(type = SpaceIndexType.EXTENDED)
	public Long getPremium() {
		return premium;
	}

	public void setPremium(Long premium) {
		this.premium = premium;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id:");
		sb.append(assetId);
		sb.append(" portfolioId:");
		sb.append(portfolioId);
		sb.append(" state:");
		sb.append(state);
		sb.append(" exposure:");
		sb.append(exposure);
		sb.append(" premium");
		sb.append(premium);
		return sb.toString();
	}

	@SpaceIndex(type = SpaceIndexType.BASIC)
	public Integer getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
