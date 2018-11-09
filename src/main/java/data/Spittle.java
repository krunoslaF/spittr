package data;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Spittle {

	// == fields ==
	private final Long id;
	private final String message;
	private final Date time;
	private Double latitude;
	private Double longitude;
	
	// == constructor ==
	public Spittle(String message, Date time, Double latitude, Double longitude) {
		this.id = null;
		this.message = message;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Spittle(String message, Date time) {
		this(message,time,null,null);
	}
	
	// == methods ==
	
}
