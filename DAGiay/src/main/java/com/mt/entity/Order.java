package com.mt.entity;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Sử dụng tự động tạo ID
	private Integer id;
	@Temporal(TemporalType.DATE)
	@Column(name = "Created_at")
	private Date createdAt = new Date();
	private String hoten;
	private String sdt;
	private String email;
	private String address;
	private Boolean status;

	@ManyToOne
	@JoinColumn(name = "accountid")
	private Account account;


	@JsonIgnore
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<Payment> payments;
}
