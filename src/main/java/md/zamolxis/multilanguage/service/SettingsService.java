package md.zamolxis.multilanguage.service;

public class SettingsService {

	private long timestampMin;
	private long timestampMax;
	private long timestampDiff;
	private int latencyDays;

	public long getTimestampMin() {
		return timestampMin;
	}

	public void setTimestampMin(long timestampMin) {
		this.timestampMin = timestampMin;
	}

	public long getTimestampMax() {
		return timestampMax;
	}

	public void setTimestampMax(long timestampMax) {
		this.timestampMax = timestampMax;
	}

	public long getTimestampDiff() {
		return timestampDiff;
	}

	public void setTimestampDiff(long timestampDiff) {
		this.timestampDiff = timestampDiff;
	}

	public int getLatencyDays() {
		return latencyDays;
	}

	public void setLatencyDays(int latencyDays) {
		this.latencyDays = latencyDays;
	}

}
