package com.thread.demo.entity;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "base_count")
@Data
public class BaseCount {

	/**
	 * ID
	 */
	@Id
	@Column(name = "id",unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 数量
	 */
	@Column(name = "count", nullable = false)
	private Integer count;
}
