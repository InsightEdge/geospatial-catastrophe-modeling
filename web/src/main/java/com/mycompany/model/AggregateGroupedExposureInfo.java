package com.mycompany.model;


public class AggregateGroupedExposureInfo {
	
	String title;
	Long total;
	Long exposure;
	Long responseTime;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getExposure() {
		return exposure;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setExposure(Long exposure) {
		this.exposure = exposure;
	}

	public Long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Long responseTime) {
		this.responseTime = responseTime;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":");
		sb.append(total);
		sb.append("{\",title\":");
		sb.append(title);
		sb.append("{\",exposure\":");
		sb.append(exposure);
		sb.append("{\",responseTime\":");
		sb.append(responseTime);
		sb.append("}");
		return sb.toString();
	}
	
}
