package com.cassandrabankapp.dto;

public class CloudtestForm {

	private String instanceID;
	
	
	public CloudtestForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CloudtestForm(String instanceID) {
		super();
		this.instanceID = instanceID;
	}

	public String getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instanceID == null) ? 0 : instanceID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CloudtestForm other = (CloudtestForm) obj;
		if (instanceID == null) {
			if (other.instanceID != null)
				return false;
		} else if (!instanceID.equals(other.instanceID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CloudtestForm [instanceID=" + instanceID + "]";
	}
	
}
