package silly.myself.servlet

import silly.myself.base.BaseServlet
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import silly.myself.common.config
import silly.myself.common.whenAc
import silly.myself.utils.decryptData
import silly.myself.utils.failData

@WebServlet(name = "AppServlet", urlPatterns = ["/app"])
class AppServlet : BaseServlet() {
    override fun doWork() {
        val sign = request.getParameter("signs") ?: ""// 获取加密数据
        val timestamp = request.getParameter("timestamps") ?: ""// 时间戳
        if (sign.isEmpty() || timestamp.isEmpty()) {
            sendData(failData("数据或时间为空"))
            return
        }
        // 解密，打印提交的数据
        System.out.println("----------Start----------")
        System.out.println("URL:${request.requestURL}")
        val map = decryptData(sign, timestamp)// 解密
        System.out.println("-----------End-----------")
        // 接口判断
        val ac = map["ac"] ?: ""
        if (ac.isEmpty()) {
            failData("接口为空")
            return
        }
        // 接口选择
        whenAc(map, response)
    }
}
