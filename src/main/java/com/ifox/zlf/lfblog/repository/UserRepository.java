package com.ifox.zlf.lfblog.repository;

import com.ifox.zlf.lfblog.entity.UserEO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEO,Integer> {

}
