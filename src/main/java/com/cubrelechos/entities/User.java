package com.cubrelechos.entities;


import javax.persistence.*;


@Entity
@Table(name = "user")
public class User {
	
	private static final long serialVersionUID = 1L;

	@Id 
	@Column (name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name ;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="product")
	private String  product;
	
	@Column(name="price")
	private int price;
	
	@Column(name="share")
	private int share;
	
	@Column(name="fertilizer")
	private int fertilizer;
	
	@Column(name="fertilizer_worth")
	private int fertilizer_worth;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String name, String phone, String product, int price, int share, int fertilizer,
			int fertilizer_worth) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.product = product;
		this.price = price;
		this.share = share;
		this.fertilizer = fertilizer;
		this.fertilizer_worth = fertilizer_worth;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

	public int getFertilizer() {
		return fertilizer;
	}

	public void setFertilizer(int fertilizer) {
		this.fertilizer = fertilizer;
	}

	public int getFertilizer_worth() {
		return fertilizer_worth;
	}

	public void setFertilizer_worth(int fertilizer_worth) {
		this.fertilizer_worth = fertilizer_worth;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phone=" + phone + ", product=" + product + ", price=" + price
				+ ", share=" + share + ", fertilizer=" + fertilizer + ", fertilizer_worth=" + fertilizer_worth + "]";
	}


	
	
	
	

}
