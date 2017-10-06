package com.mycompany.model;

import java.util.List;

import com.mycompany.model.leafletjs.MapBoxCircleLayer;
import com.mycompany.model.leafletjs.MapBoxPolygonLayer;

/**
 * @author mike
 *
 */
public class SelectedCircleRequest implements SelectedShapeRequest<MapBoxCircleLayer> {

	MapBoxCircleLayer selectedShape;
	MapBoxPolygonLayer selectedShapeScenarioIntersection;
	String scenarioId;
	List<Integer> portfolioIds;
	Boolean groupByState;

	@Override
	public String getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(String scenarioId) {
		this.scenarioId = scenarioId;
	}
	@Override
	public List<Integer> getPortfolioIds() {
		return portfolioIds;
	}

	public void setPortfolioIds(List<Integer> portfolioIds) {
		this.portfolioIds = portfolioIds;
	}
	@Override
	public MapBoxCircleLayer getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(MapBoxCircleLayer selectedShape) {
		this.selectedShape = selectedShape;
	}
	@Override
	public MapBoxPolygonLayer getSelectedShapeScenarioIntersection() {
		return selectedShapeScenarioIntersection;
	}

	public void setSelectedShapeScenarioIntersection(MapBoxPolygonLayer selectedShapeScenarioIntersection) {
		this.selectedShapeScenarioIntersection = selectedShapeScenarioIntersection;
	}

	public Boolean getGroupByState() {
		return groupByState;
	}

	public void setGroupByState(Boolean groupByState) {
		this.groupByState = groupByState;
	}

}
