package max.utility.tomato.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity
public class Tomato {

    @Id
    @Column(name = "ID", unique = true, nullable = false, scale = 0)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String focusOn;
	 
	@Column(nullable=false)
//	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime startTime;
	
	
	
	public Tomato() {
		super();
	}

	public Tomato(String focusOn) {
		this.focusOn = focusOn;
		startTime = new LocalDateTime();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tomato [id=");
		builder.append(id);
		builder.append(", focusOn=");
		builder.append(focusOn);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append("]");
		return builder.toString();
	}

	
	
	
	
	

}
