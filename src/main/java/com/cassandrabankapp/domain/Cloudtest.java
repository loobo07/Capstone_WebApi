package com.cassandrabankapp.domain;

public class Cloudtest {
	
	private String instanceID;
	private String status;
	
	public Cloudtest(String instanceID, String status) {
		super();
		this.instanceID = instanceID;
		this.status = status;
	}

	public Cloudtest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instanceID == null) ? 0 : instanceID.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Cloudtest other = (Cloudtest) obj;
		if (instanceID == null) {
			if (other.instanceID != null)
				return false;
		} else if (!instanceID.equals(other.instanceID))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cloudtest [instanceID=" + instanceID + ", status=" + status + "]";
	}

}
