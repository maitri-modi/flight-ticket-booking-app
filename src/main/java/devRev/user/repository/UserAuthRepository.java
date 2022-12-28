package devRev.user.repository;

import devRev.user.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuthEntity, Integer> {
}
