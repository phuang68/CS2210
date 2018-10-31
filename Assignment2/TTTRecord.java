
public class TTTRecord {
	private String config;
	private int score;
	private int level;

	public TTTRecord(String config, int score, int level) {
		this.config = config;
		this.score = score;
		this.level = level;
	}

	public String getConfiguration() {
		return this.config;
	}

	public int getScore() {
		return this.score;
	}

	public int getLevel() {
		return this.level;
	}

}
