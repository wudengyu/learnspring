package holenote.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import holenote.business.entities.User;


public interface UserRepository extends PagingAndSortingRepository<User,Long>,JpaSpecificationExecutor<User>{

    User findByUsername(String username);
}
