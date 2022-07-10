package beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserResponseDataModel extends UserResponseModel
{

	private User user;
	
	public UserResponseDataModel() 
	{
		super(0, "");
		this.setUser(null);
	}

	public UserResponseDataModel(int status, String message, User user)
	{
		super(status, message);
		this.setUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
