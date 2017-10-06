package com.mycompany.model.leafletjs;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FeatureCollection {
	private String type;
	
	private List<Feature> features;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(List<Feature> features) {
		this.features = features;
	}
	
	public static class Feature{
		private String type;
		
		private Map<String,Serializable> properties;
		
		private MapBoxPoint geometry;
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Map<String, Serializable> getProperties() {
			return properties;
		}
		public void setProperties(Map<String, Serializable> properties) {
			this.properties = properties;
		}
		public MapBoxPoint getGeometry() {
			return geometry;
		}
		public void setGeometry(MapBoxPoint geometry) {
			this.geometry = geometry;
		}
		
	}
}
