package learnspring.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import learnspring.business.BusinessConfig;
import learnspring.business.entities.User;
import learnspring.business.repository.UserRepository;
@Ignore
public class UserDaoTest {
    private static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    private GenericApplicationContext ctx;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(BusinessConfig.class);
        userRepository = ctx.getBean(UserRepository.class);
    }

    @Test
    public void testSpecificaiton() {
        User user=userRepository.findByUsername("admin");
        assertNotNull(user);
        logger.info(user.getUsername());
    }

    @Test
    public void testUpdate(){
        User user=userRepository.findById(Long.valueOf(2)).get();
        user.setEnable(!user.isEnabled());
        userRepository.save(user);
    }



}
