package com.mycompany.model.leafletjs;

import java.util.List;

public class MapBoxPoint extends MapBoxShape{
	
   private List<Double> coordinates;

	public List<Double> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}
	
	   
}
