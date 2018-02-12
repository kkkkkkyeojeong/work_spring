package model;

public class Job {
	
	private String jobId;
	private String jobtitle;
	private Integer minSalary;
	private Integer maxSalary;
	
	public Job() {}

	public Job(String jobId, String jobtitle, Integer minSalary, Integer maxSalary) {
		super();
		this.jobId = jobId;
		this.jobtitle = jobtitle;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobtitle;
	}

	public void setJobTitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public Integer getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Integer minSalary) {
		this.minSalary = minSalary;
	}

	public Integer getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Integer maxSalary) {
		this.maxSalary = maxSalary;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Job [jobId=");
		builder.append(jobId);
		builder.append(", jobtitle=");
		builder.append(jobtitle);
		builder.append(", minSalary=");
		builder.append(minSalary);
		builder.append(", maxSalary=");
		builder.append(maxSalary);
		builder.append("]");
		return builder.toString();
	}
	
	

}
