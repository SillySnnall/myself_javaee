package silly.myself.base

import com.google.gson.Gson
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException

import silly.myself.common.config

@WebServlet(name = "BaseServlet")
abstract class BaseServlet : HttpServlet() {
    protected lateinit var request: HttpServletRequest
    protected lateinit var response: HttpServletResponse

    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        doGet(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        this.request = request
        this.response = response
        config(request, response)
        doWork()
    }

    protected abstract fun doWork()

    /**
     * 发送数据给客户端
     */
    protected fun sendData(data: String) {
        response.writer.write(data)
        response.writer.flush()
        response.writer.close()
    }
}
