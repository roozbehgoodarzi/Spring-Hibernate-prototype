package com.ocs.spring.dao.hibernateImpl;

import com.ocs.entity.Customer;
import com.ocs.entity.Person;
import com.ocs.exception.DaoException;
import com.ocs.exception.DataAccessException;
import com.ocs.exception.ExceptionReason;
import com.ocs.spring.dao.GenericDaoHibernate;
import com.ocs.util.DbUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Goodarzi on 10/12/14.
 */
@Repository
public class CustomerDao extends GenericDaoHibernate<Customer> {

    @Resource
    private SessionFactory sessionFactory;

    public Customer getCustomerByNationalId(String nationalId) {
        String hql = "FROM Customer as C WHERE C.nationalId = :nationalId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("nationalId", nationalId);
        List<Customer> customers = query.list();
        try {
            if (!DbUtil.checkResultExist(customers))
                throw new DaoException("Calling class " + PersonDao.class + " has an error on getPersonByNationalID: " + ExceptionReason.NoResult, (short) 0);
            if (!DbUtil.checkUniqueResult(customers))
                throw new DaoException("Calling Class " + PersonDao.class + " has an error on getPersonByNationalID:" + ExceptionReason.NotUniqueResult, (short) 0);
        } catch (DaoException e) {
            throw new DataAccessException(e, (short) 0);
        }

        return customers.get(0);
    }

//    @Autowired
//    private SessionFactory sessionFactory;

//    /**
//     * Get Hibernate Session Factory
//     *
//     * @return SessionFactory - Hibernate Session Factory
//     */
//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    /**
//     * Set Hibernate Session Factory
//     *
//     * @param sessionFactory SessionFactory - Hibernate Session Factory
//     */
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    /**
//     * Add customer
//     *
//     * @param   customer   customer
//     */
//
//    public void addCustomer(Customer customer) {
//        getSessionFactory().getCurrentSession().save(customer);
//    }
//
//    /**
//     * Delete customer
//     *
//     * @param   customer  customer
//     */
//
//    public void deleteCustomer(Customer customer) {
//        getSessionFactory().getCurrentSession().delete(customer);
//    }
//
//    /**
//     * Update customer
//     *
//     * @param  customer customer
//     */
//
//    public void updateCustomer(Customer customer) {
//        getSessionFactory().getCurrentSession().update(customer);
//    }
//
//    /**
//     * Get customer
//     *
//     * @param  id int
//     * @return customer
//     */
//
//    public Customer getCustomerById(int id) {
//        List list = getSessionFactory().getCurrentSession()
//                .createQuery("from Customer  where id=?")
//                .setParameter(0, id).list();
//        return (Customer)list.get(0);
//    }
//
//    /**
//     * Get customer List
//     *
//     * @return List - customer list
//     */
//
//    public List<Customer> getCustomers() {
//        List list = getSessionFactory().getCurrentSession().createQuery("from  Customer").list();
//        return list;
//    }

}
