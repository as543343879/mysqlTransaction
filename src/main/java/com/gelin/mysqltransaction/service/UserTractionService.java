package com.gelin.mysqltransaction.service;

import com.gelin.mysqltransaction.dao.UserRepository;
import com.gelin.mysqltransaction.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

/**
 * UserTractionService class
 *
 * @author 格林
 * @date 2023-01-28
 */
@Service
public class UserTractionService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PlatformTransactionManager dataSourceTransactionManager;

    @Autowired
    TransactionDefinition transactionDefinition;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User addUser(String userName, boolean isError) {
        User user = new User("admin",userName);

        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try{
//            User save = userRepository.save(user);
            jdbcTemplate.execute("insert into user values ("+ System.currentTimeMillis() +",'admin','" + userName +  "')");
            if(isError) {
                return user;
            }
            dataSourceTransactionManager.commit(transactionStatus);
            return user;
        }catch (Exception e) {
            e.printStackTrace();
            dataSourceTransactionManager.rollback(transactionStatus);
            throw new RuntimeException(e);
        }
    }

}
