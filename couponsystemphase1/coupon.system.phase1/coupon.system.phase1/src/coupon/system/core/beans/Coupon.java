package coupon.system.core.beans;

import java.time.LocalDate;
import java.util.Objects;

public class Coupon {

	private int couponID;
	private int companyID;
	private Category category;
	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private int amount;
	private double price;
	private double maxPrice;
	private String image;

	public Coupon() {
	}

	public Coupon(int couponID) {
		super();
		this.couponID = couponID;
	}

	public Coupon(int couponID, int companyID, Category category, String title, String description, LocalDate startDate,
			LocalDate endDate, int amount, double price, String image) {
		super();
		this.couponID = couponID;
		this.companyID = companyID;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	public int getCouponID() {
		return couponID;
	}

	public void setCouponID(int couponID) {
		this.couponID = couponID;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Coupon [CouponID= " + couponID + ", companyID= " + companyID + ", category= " + category + ", title= "
				+ title + ", description= " + description + ", startDate= " + startDate + ", endDate= " + endDate
				+ ", amount= " + amount + ", price= " + price + ", image= " + image + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, category, companyID, couponID, description, endDate, image, price, startDate,
				title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		return amount == other.amount && category == other.category && companyID == other.companyID
				&& couponID == other.couponID && Objects.equals(description, other.description)
				&& Objects.equals(endDate, other.endDate) && Objects.equals(image, other.image)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(startDate, other.startDate) && Objects.equals(title, other.title);
	}
	
	

}
