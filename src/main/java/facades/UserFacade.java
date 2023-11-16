package facades;

import dtos.UserDTO;
import entities.Role;
import entities.User;
import security.errorhandling.AuthenticationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


public class UserFacade implements IUserFacade{

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    protected UserFacade() {
    }


    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public User findUserByName(String username) {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
        } finally {
            em.close();
        }
        return user;
    }


    @Override
    public List<UserDTO> seeAllUsers() {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
            List<User> users = query.getResultList();

            List<UserDTO> udtos = UserDTO.getDtos(users);
            return udtos;
        }finally{
            em.close();
        }
    }


    @Override
    public UserDTO changeUserRole(String userName, String role) {
        System.out.println("role");
        System.out.println(role);
        System.out.println(role.equals("stopAdmin"));
        EntityManager em = emf.createEntityManager();
        try {
            User u = em.find(User.class, userName);
            if(role.equals("stopAdmin")){
                Role r = new Role("user");
                List<Role> roleList = new ArrayList<>();
                roleList.add(r);
                u.setRoleList(roleList);
            }else{
                Role r = new Role(role);
                u.addRole(r);
            }
            em.getTransaction().begin();
            User u2 = em.merge(u);
            em.getTransaction().commit();
            System.out.println(u);
            return new UserDTO(u2);
        }finally{
            em.close();
        }
    }


    @Override
    public User signUp(String userName, String password) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            User user = new User(userName, password);
            Role role = new Role("user");
            user.addRole(role);
            em.persist(user);
            em.getTransaction().commit();
            return user;
        }finally {
            em.close();
        }
    }
}