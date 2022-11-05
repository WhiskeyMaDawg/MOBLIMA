
public class Customer implements CustomerAge {
	private int age;
	private Age ageEnum;
	private int mobileNumber;
	private String emailAddress;
	private int[] movieHistory; //Change when movie class created
	
	
	
	public Customer(int age, Age ageEnum, int mobileNumber, String emailAddress) {
		this.ageEnum = ageEnum;
		this.age = age;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
	}
	
	public Age getAgeEnum() {
		return ageEnum;
	}
	public void setAgeEnum(int age) {
		if(age < 18) {
			ageEnum = Age.CHILD;
		}
		else if(age>=18 & age <65) {
			ageEnum = Age.ADULT;
		}
		else {
			ageEnum = Age.SENIOR;
		}
	}
	public void addReview() {
		
	}
	public void buyTicket() {
		
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int[] getMovieHistory() {
		return movieHistory;
	}

	public void setMovieHistory(int[] movieHistory) {
		this.movieHistory = movieHistory;
	}

	public void setAgeEnum(Age ageEnum) {
		this.ageEnum = ageEnum;
	}


	
	
	
}
