package org.learn.simpl.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Students")
public class Students {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;

	 //@NotBlank
	    //@Size(max = 100)
	    @Indexed(unique = true)
	private String firstName;

	private String lasttName;

	private String emaiId;

	private String mobileNo;

	public Students() {
	}

	public Students(int Id, String firstName, String lasttName, String emaiId, String mobileNo) {
		super();
		this.id = Id;
		this.firstName = firstName;
		this.lasttName = lasttName;
		this.emaiId = emaiId;
		this.mobileNo = mobileNo;
	}

	public long getId() {
		return id;
	}

	public void setId(long Id) {
		this.id = Id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLasttName() {
		return lasttName;
	}

	public void setLasttName(String lasttName) {
		this.lasttName = lasttName;
	}

	public String getEmaiId() {
		return emaiId;
	}

	public void setEmaiId(String emaiId) {
		this.emaiId = emaiId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "Students [id=" + id + ", firstName=" + firstName + ", lasttName=" + lasttName + ", emaiId=" + emaiId
				+ ", mobileNo=" + mobileNo + "]";
	}

}
