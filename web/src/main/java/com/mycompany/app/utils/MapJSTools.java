package com.mycompany.app.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openspaces.spatial.ShapeFactory;
import org.openspaces.spatial.shapes.Circle;
import org.openspaces.spatial.shapes.Point;
import org.openspaces.spatial.shapes.Polygon;

import com.mycompany.model.Asset;
import com.mycompany.model.leafletjs.FeatureCollection;
import com.mycompany.model.leafletjs.MapBoxCircleLayer;
import com.mycompany.model.leafletjs.MapBoxPoint;
import com.mycompany.model.leafletjs.MapBoxPolygonLayer;
import com.mycompany.model.leafletjs.FeatureCollection.Feature;
import com.mycompany.model.leafletjs.MapBoxCircleLayer.MapBoxCircle;
import com.mycompany.model.leafletjs.MapBoxPolygonLayer.MapBoxPolygon;

/***
 * 
		NYC
		Y or Longitude: 40.7142700	
		X or Latitude: -74.0059700 

 * @author mike
 *
 */
public class MapJSTools {

	private static final Double METERS_PER_DEGREE = 111111.0;
	
	
	private MapJSTools(){}
	
	public static MapBoxPolygonLayer convertGigaspacesPolygonToLeafletPolygonLayer(Polygon polygon){
		
		MapBoxPolygonLayer layer = new MapBoxPolygonLayer();
		layer.setType("Feature");
		
		MapBoxPolygon geo = new MapBoxPolygonLayer.MapBoxPolygon();
		geo.setType("Polygon");
		
		
		int numberOfPoints = (polygon != null)?polygon.getNumOfPoints():0;
		
		List<List<Double>> points = new ArrayList<List<Double>>();
		for(int i = 0; i < numberOfPoints; i++){
			List<Double> oneCoordinate = new ArrayList<Double>();
			oneCoordinate.add(polygon.getX(i));
			oneCoordinate.add(polygon.getY(i));
			points.add(oneCoordinate);
		}
		List<List<List<Double>>> shape = new ArrayList<List<List<Double>>>();
		shape.add(points);
		geo.setCoordinates(shape);
		
		layer.setGeometry(geo);
		
		return layer;
	}
	public static Polygon convertLeafletPolygonToGigaspacesPolygon(MapBoxPolygonLayer map){
		MapBoxPolygon geo = map.getGeometry();

		Collection<Point> points = new ArrayList<Point>();
		for (List<List<Double>> oneCoor : geo.getCoordinates()) {
			for (List<Double> onePoint : oneCoor) {
				points.add(ShapeFactory.point(onePoint.get(0), onePoint.get(1)));
			}
		}
		
		return ShapeFactory.polygon(points);
	}
	public static Circle convertLeafletCircleToGigaspacesCircle(MapBoxCircleLayer map){
		
		MapBoxCircle geo = map.getGeometry();
		List<Double> coordinates = geo.getCoordinates();
		Double latitude = coordinates.get(0);
		Double longitude = coordinates.get(1);
		
		Double radius = Double.parseDouble(map.getProperties().get("radius"))/METERS_PER_DEGREE;
		
		Point center = ShapeFactory.point(latitude, longitude);
		Circle circle = ShapeFactory.circle(center, radius);
		
		return circle;
	}
	public static FeatureCollection convertAssetsToMarkers(Asset[] assets) {
		
		FeatureCollection featureCollection = new FeatureCollection();
		featureCollection.setType("FeatureCollection");
		
		List<FeatureCollection.Feature> features = new ArrayList<FeatureCollection.Feature>();
		for(Asset oneAsset: assets){
			Map<String, Serializable> featureProperties = new HashMap<String, Serializable>();
			
			Feature feature = new Feature();
			
			feature.setType("Feature");
			featureProperties.put("assetId", oneAsset.getAssetId());
			featureProperties.put("portfolioId", oneAsset.getPortfolioId());
			featureProperties.put("exposure", oneAsset.getExposure());
			//featureProperties.put("premium", oneAsset.getPremium());
			feature.setProperties(featureProperties);
			
			MapBoxPoint onePoint = new MapBoxPoint();
			onePoint.setType("Point");
			List<Double> coordinates = new ArrayList<Double>();
			coordinates.add(oneAsset.getLocation().getX());
			coordinates.add(oneAsset.getLocation().getY());
			onePoint.setCoordinates(coordinates);
			
			feature.setGeometry(onePoint);
			
			features.add(feature);
			
		}
		featureCollection.setFeatures(features);
		
		return featureCollection;
	}
}
