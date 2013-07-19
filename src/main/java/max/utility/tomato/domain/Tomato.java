package max.utility.tomato.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity
public class Tomato {

	@Column(nullable = true, length = 80)
	@Size(min = 0, max = 80)
	private String title;

	@Column(nullable = false, length = 500)
	@Size(min = 0, max = 500)
	private String focusOn;

	@Id
	@Column(name = "ID", unique = true, nullable = false, scale = 0)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	// @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime startTime;

	@Column
	private Integer duration;

	public Tomato() {
		super();
	}

	public Tomato(String focusOn) {
		this.focusOn = focusOn;
		startTime = new LocalDateTime();
	}

	public String getFocusOn() {
		return focusOn;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setFocusOn(String focusOn) {
		this.focusOn = focusOn;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tomato [title=");
		builder.append(title);
		builder.append(", id=");
		builder.append(id);
		builder.append(", focusOn=");
		builder.append(focusOn);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append("]");
		return builder.toString();
	}

}
