package com.mycompany.model;

import java.util.List;

import com.mycompany.model.leafletjs.MapBoxPolygonLayer;


public class SelectedPolygonRequest implements SelectedShapeRequest<MapBoxPolygonLayer>{

	String scenarioId;
	List<Integer> portfolioIds;
	
	MapBoxPolygonLayer selectedShape;
	MapBoxPolygonLayer selectedShapeScenarioIntersection;
	
	Boolean groupByState;
	
	@Override
	public MapBoxPolygonLayer getSelectedShape() {
		return selectedShape;
	}
	public void setSelectedShape(MapBoxPolygonLayer selectedShape) {
		this.selectedShape = selectedShape;
	}
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
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("scenarioId:");
		sb.append(scenarioId);
		sb.append("groupByState:");
		sb.append(groupByState);
		return sb.toString();
	}
	
	
}
