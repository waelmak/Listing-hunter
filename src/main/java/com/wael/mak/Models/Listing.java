package com.wael.mak.Models;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "listings")
public class Listing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Address is required")
	private String address;

	@Min(value = 1, message = "Minimum price must be more than 0 !")
	@Max(value = 999999, message = "Maximum price must be less than 999999 !")
	private int price;

	@PastOrPresent(message = "Listing Date mustn\''t be in the future!")
	@NotNull(message = "Listing Date is required")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate listingDate = LocalDate.now();

	@Column(updatable = false)
	private Date createdAt;

	private Date updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "listing", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Note> notes;

	public Listing() {

	}

	public Listing(Long id, @NotBlank(message = "Address is required") String address,
			@NotBlank(message = "Price is required") @NotBlank(message = "Price is required") @Min(value = 1, message = "Minimum price must be more than 0!") @Min(value = 999999, message = "Maximum price must be less than 999999!") int price,
			LocalDate listingDate, Date createdAt, Date updatedAt, User user) {
		super();
		this.id = id;
		this.address = address;
		this.price = price;
		this.listingDate = listingDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDate getListingDate() {
		return listingDate;
	}

	public void setListingDate(LocalDate listingDate) {
		this.listingDate = listingDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
