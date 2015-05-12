/**
 * Timer Review  -  a personal time management tool
 *
 *
 * Copyright (C)  2012 - 2014 Parentini Massimiliano
 * Project home page: http://www.timer-review.net
 *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */
package zxmax.tools.timerreview.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
public class TomatoReview {

	@Id
	@Column(name = "ID", unique = true, nullable = false, scale = 0)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = true, length = 500)
	@Size(min = 0, max = 500)
	private String problemsRaised;

	@Column(nullable = true, length = 500)
	@Size(min = 0, max = 500)
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
