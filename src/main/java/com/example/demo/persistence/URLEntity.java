package com.example.demo.persistence;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_URL")
public class URLEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "url", nullable = false)
	private String url;

	@Column(name = "hashcode", nullable = false)
	private String hashcode;

	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDateTime createDateTime;

	@Override
	public String toString() {
		StringBuffer st = new StringBuffer("URLEntity [");
		return st.append("id=").append(id).append("url =").append(url)
				.append(", hashcode=").append(hashcode).append("]")
				.append(",createDateTime=").append(createDateTime).toString();
	}
}
