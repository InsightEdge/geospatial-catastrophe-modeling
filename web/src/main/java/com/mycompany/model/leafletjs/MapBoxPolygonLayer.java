package com.mycompany.model.leafletjs;

import java.util.List;

public class MapBoxPolygonLayer  extends MapBoxShapeLayer{
	private MapBoxPolygon geometry;
	
	public MapBoxPolygon getGeometry() {
		return geometry;
	}
	public void setGeometry(MapBoxPolygon geometry) {
		this.geometry = geometry;
	}
	public static class MapBoxPolygon extends MapBoxShape {
		
	    private List<List<List<Double>>> coordinates ;

		public List<List<List<Double>>> getCoordinates() {
			return coordinates;
		}
		public void setCoordinates(List<List<List<Double>>> coordinates) {
			this.coordinates = coordinates;
		}
		
		   
	}
}
