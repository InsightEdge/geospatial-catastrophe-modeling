package com.mycompany.model.leafletjs;

import java.util.Map;

public class MapBoxShapeLayer {
	private String type;
	private Map<String, String> properties;
	
	
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
