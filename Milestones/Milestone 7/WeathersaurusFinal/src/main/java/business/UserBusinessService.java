package business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import beans.User;
import data.UserDataInterface;

@Stateless
@Local(UserServiceInterface.class)
@LocalBean
public class UserBusinessService implements UserServiceInterface
{
	@EJB
	UserDataInterface<User> UserDAO;
	
	@Override
	public boolean register(User user) {		
		boolean result = UserDAO.create(user);
		return result;
	}

	@Override
	public boolean login(User user) 
	{
		return UserDAO.find(user);
	}

	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

		
}
