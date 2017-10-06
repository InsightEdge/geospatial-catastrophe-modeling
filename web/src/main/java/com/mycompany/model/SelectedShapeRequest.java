package com.mycompany.model;

import java.util.List;

import com.mycompany.model.leafletjs.MapBoxPolygonLayer;

public interface SelectedShapeRequest<Shapetype> {

	public String getScenarioId();

	public List<Integer> getPortfolioIds();

	public Shapetype getSelectedShape();

	public MapBoxPolygonLayer getSelectedShapeScenarioIntersection();

}
