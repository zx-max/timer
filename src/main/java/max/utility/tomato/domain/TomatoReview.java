package max.utility.tomato.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TomatoReview {

	@Id
	@Column(name = "ID", unique = true, nullable = false, scale = 0)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String problemsRaised;

	@Column
	private String reallyDone;

	@OneToOne
	private Tomato tomato;

	public TomatoReview() {
		super();
	}

	public TomatoReview(Tomato tomato, String reallyDone, String problemsRaised) {
		this.problemsRaised = problemsRaised;
		this.reallyDone = reallyDone;
		this.tomato = tomato;
	}

	public Long getId() {
		return id;
	}

	public String getProblemsRaised() {
		return problemsRaised;
	}

	public String getReallyDone() {
		return reallyDone;
	}

	public Tomato getTomato() {
		return tomato;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProblemsRaised(String problemsRaised) {
		this.problemsRaised = problemsRaised;
	}

	public void setReallyDone(String reallyDone) {
		this.reallyDone = reallyDone;
	}

	public void setTomato(Tomato tomato) {
		this.tomato = tomato;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TomatoReview [id=");
		builder.append(id);
		builder.append(", problemsRaised=");
		builder.append(problemsRaised);
		builder.append(", reallyDone=");
		builder.append(reallyDone);
		builder.append("]");
		return builder.toString();
	}

}
