package user.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import user.dao.UserDao;
import user.domain.Level;
import user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-applicationContext.xml")
public class UserServiceTest {
	@Autowired
	@Qualifier
	ApplicationContext context;

	UserService userService;
	List<User> users;
	private UserDao userDao; 
	@Before
	public void setUp() {
		
		this.userDao = this.context.getBean("userDao", UserDao.class);
		this.userService = this.context.getBean("userService", UserService.class);
		
		users = Arrays.asList(
					new User("bumjin", "�ڹ���", "p1", Level.BASIC, 49, 0),
					new User("joytouch", "����", "p2", Level.BASIC, 50, 0),
					new User("erwins", "�Ž�ȯ", "p3", Level.SILVER, 60, 29),
					new User("madnite1", "�̻�ȣ", "p4", Level.SILVER, 60, 30),
					new User("green", "���α�", "p5", Level.GOLD, 100, 100)
				);
	}
	
	@Test
	public void bean() {
		assertThat(this.userService, is(notNullValue()));
	}
	
	@Test
	public void upgradeLevels() {
		userDao.deleteAll();
		
		for(User user : users)
			userDao.add(user);
		
		userService.upgradeLevels();
		checkLevel(users.get(0), Level.BASIC);
		checkLevel(users.get(1), Level.SILVER);
		checkLevel(users.get(2), Level.SILVER);
		checkLevel(users.get(3), Level.GOLD);
		checkLevel(users.get(4), Level.GOLD);
		
	}
	
	@Test
	public void add() {
		userDao.deleteAll();
		
		User userWithLevel = users.get(4);     //GOLD����
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevel(null);
		
		userService.add(userWithLevel);
		userService.add(userWithoutLevel);
		
		User userWithLevelRead = userDao.get(userWithLevel.getId());
		User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());
		
		assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
		assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));
		
	}
	private void checkLevel(User user, Level expectedLevel) {
		User userUpdate = userDao.get(user.getId());
		assertThat(userUpdate.getLevel(), is(expectedLevel));
	}
	
	
}
