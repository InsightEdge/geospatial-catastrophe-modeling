package com.mycompany.model;

import org.openspaces.spatial.SpaceSpatialIndex;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import org.openspaces.spatial.shapes.Polygon;

@SpaceClass
public class GISScenario {

	private String scenarioId;

	private Polygon geometry;

	public GISScenario() {
	}

	public GISScenario(String scenarioId, Polygon geometry) {
		this.scenarioId = scenarioId;
		this.geometry = geometry;
	}

	@SpaceId(autoGenerate = false)
	public String getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(String scenarioId) {
		this.scenarioId = scenarioId;
	}

	@SpaceSpatialIndex
	public Polygon getGeometry() {
		return geometry;
	}

	public void setGeometry(Polygon geometry) {
		this.geometry = geometry;
	}

}
