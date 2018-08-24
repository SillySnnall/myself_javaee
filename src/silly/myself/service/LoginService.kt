package silly.myself.service

import silly.myself.base.BaseService
import silly.myself.bean.User
import silly.myself.dao.UserDao
import silly.myself.utils.failData
import silly.myself.utils.isEmptyParams
import silly.myself.utils.successData
import javax.servlet.http.HttpServletResponse

class LoginService(response: HttpServletResponse, map: Map<String, Any>) : BaseService<User>(response, map, User()) {
    override fun isEmpty(): Boolean {
        if (isEmptyParams(arrayListOf(model.account, model.password))) {
            sendData(failData("参数为空"))
            return false
        }
        return true
    }

    override fun mainService() {
        val userDao = UserDao()
        // 判断用户是否存在
        val userList = userDao.findByAccount(model.account)
        if (userList.isEmpty()) {
            sendData(failData("用户不存在"))
            return
        }
        val user = userList[0]
        // 判断密码是否正确
        if (model.password != user.password) {
            sendData(failData("密码不正确"))
            return
        }
        // 发送成功数据
        sendData(successData(user))
    }
}