
public class Record {
	
	String fname;
	String lname;
	String email;
	String gender;
	String image;
	String creditCard;
	String charge;
	String bool1;
	String bool2;
	String city;

	/* No args constructor that does nothing */
	public Record() {
		
	}
	
	/* Constructor with arguments */
	public Record(String fname, String lname, String email, String gender, String image, String creditCard, String charge, String bool1, String bool2, String city) {
		
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.gender = gender;
		this.image = image;
		this.creditCard = creditCard;
		this.charge = charge;
		this.bool1 = bool1;
		this.bool2 = bool2;
	}
	
	public String getFname() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getCreditCard() {
		return creditCard;
	}
	
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	
	public String getCharge() {
		return charge;
	}
	
	public void setCharge(String charge) {
		this.charge = charge;
	}
	
	public String getBool1() {
		return bool1;
	}
	
	public void setBool1(String bool1) {
		this.bool1 = bool1;
	}
	
	public String getBool2() {
		return bool2;
	}
	
	public void setBool2(String bool2) {
		this.bool2 = bool2;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
    public String toString() {
        return "Record{" + "fname=" + fname + ", lname='" + lname + ", email=" + email + ", gender=" + gender + '}';
    }
}
