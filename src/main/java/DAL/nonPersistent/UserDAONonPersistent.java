package DAL.nonPersistent;

import DAL.interfaces.DALException;
import DAL.interfaces.IUserDAO;
import DAL.interfaces.JunkFormatException;
import DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: Andreas, Christoffer
 * Created: 08-06-2020
 * This class is responsible for:
 *  Storing information about users in a non persistent manner
 *  Assuring wrong or illegal information is not stored
 */
public class UserDAONonPersistent implements IUserDAO {
    private List<UserDTO> users;

    public UserDAONonPersistent() {
        users = new ArrayList<>();
    }

    public UserDTO getUser(int userId) throws DALException {
        for (UserDTO user : users)
        {
            if (user.getID() == userId)
            {
                return user;
            }
        }
        throw new DALException("There is no user where ID=" + userId);
    }

    public List<UserDTO> getUserList() throws DALException
    {
        return users;
    }

    public void createUser(UserDTO newUser) throws DALException, JunkFormatException
    {
        if (newUser.getID() < 11 || newUser.getID() > 99)
        {
            throw new DALException("BrugerID skal være mellem 11 og 99 (inklusivt)");
        }
        for (UserDTO user : users)
        {
            if (user.getID() == newUser.getID())
            {
                throw new DALException("Der findes allerede en bruger med ID " + user.getID());
            }
        }
        users.add(newUser);
    }

    public void updateUser(UserDTO newUser) throws DALException, JunkFormatException
    {
        for (UserDTO user : users)
        {
            if (user.getID() == newUser.getID())
            {
                users.remove(user);
                users.add(newUser);
                return;
            }
        }
        throw new DALException("Brugeren du prøvede at opdatere fandtes ikke");
    }


    @Override
    public void setIsActive(int userId, boolean isActive) throws DALException {
        UserDTO user = getUser(userId);
        if (user.getIsActive() == isActive){
            throw new DALException("The user activity is already "+isActive);
        }
        UserDTO newUser = new UserDTO(user.getID(), user.getUsername(), user.getIni(), user.getCPR(), user.getPassword(), user.getRole(), isActive);
        try {
            updateUser(newUser);
        } catch (JunkFormatException e) {
            throw new AssertionError("Changing isActive should not result in junkformat");
        }
    }

    public void setInactiveUser(int userId) throws DALException
    {
        for (UserDTO user : users)
        {
            if (user.getID() == userId)
            {
                users.remove(user);
                return;
            }
        }
        throw new DALException("Der fandtes ingen bruger med ID " + userId);
    }
}
