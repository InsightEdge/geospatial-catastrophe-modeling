package com.mycompany.model.leafletjs;

import java.util.List;

public class MapBoxCircleLayer  extends MapBoxShapeLayer{
	private MapBoxCircle geometry;
	
	public MapBoxCircle getGeometry() {
		return geometry;
	}
	public void setGeometry(MapBoxCircle geometry) {
		this.geometry = geometry;
	}
	public class MapBoxCircle extends MapBoxShape{
		
		   private List<Double> coordinates;

			public List<Double> getCoordinates() {
				return coordinates;
			}
			public void setCoordinates(List<Double> coordinates) {
				this.coordinates = coordinates;
			}
		}
}
