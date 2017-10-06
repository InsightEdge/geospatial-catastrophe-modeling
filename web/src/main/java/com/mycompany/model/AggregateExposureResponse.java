package com.mycompany.model;

import java.util.List;

public class AggregateExposureResponse {

	Long responseTime;
	Long totalCount;
	Long totalExposure;
	List<AggregateExposureInfo> groupbyExposureInfoList;

	public Long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Long responseTime) {
		this.responseTime = responseTime;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getTotalExposure() {
		return totalExposure;
	}

	public void setTotalExposure(Long totalExposure) {
		this.totalExposure = totalExposure;
	}

	public List<AggregateExposureInfo> getGroupbyExposureInfoList() {
		return groupbyExposureInfoList;
	}

	public void setGroupbyExposureInfoList(List<AggregateExposureInfo> groupbyExposureInfoList) {
		this.groupbyExposureInfoList = groupbyExposureInfoList;
	}

}
