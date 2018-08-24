package silly.myself.dao

import org.apache.commons.dbutils.handlers.BeanListHandler
import silly.myself.base.BaseDao
import silly.myself.bean.User
import silly.myself.utils.getCurrentDate
import java.sql.SQLException


class UserDao : BaseDao() {
    fun updatePwd(account: String, password: String): Boolean {
        try {
            val sql = "UPDATE user SET password='$password' WHERE account='$account';"
            getQueryRunner().update(sql)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            System.out.println(e)
        }
        return false
    }

    fun saveEmail(account: String, email: String): Boolean {
        try {
            val sql = "UPDATE user SET email='$email' WHERE account='$account';"
            getQueryRunner().update(sql)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            System.out.println(e)
        }
        return false
    }

    fun updateToken(account: String, token: String): Boolean {
        try {
            val sql = "UPDATE user SET token='$token' WHERE account='$account';"
            getQueryRunner().update(sql)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            System.out.println(e)
        }
        return false
    }

    /**
     * 通过Account查询用户数据
     */
    fun findByAccount(account: String): List<User> {
        try {
            val sql = "SELECT * FROM user WHERE account='$account'"
            return getQueryRunner().query(sql, BeanListHandler<User>(User::class.java))
        } catch (e: SQLException) {
            e.printStackTrace()
            System.out.println(e)
        }
        return arrayListOf()
    }

    /**
     * 添加用户
     */
    fun addUser(user: User): Boolean {
        try {
            val sql = "INSERT INTO user ( _id, account, password, create_time ) VALUES ( null, '${user.account}', '${user.password}', '${getCurrentDate()}' );"
            getQueryRunner().update(sql)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            System.out.println(e)
        }
        return false
    }
}