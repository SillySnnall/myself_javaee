package silly.myself.servlet

import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "AppServlet",urlPatterns = ["/app"])
class AppServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        doGet(request,response)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        request.characterEncoding = "utf-8"
        response.contentType = "text/json;charset=utf-8"
        response.characterEncoding = "utf-8"
        response.addHeader("Access-Control-Allow-Origin", "*")


        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With")

        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS")

        response.setHeader("X-Powered-By","Jetty")


        val writer = response.writer
        writer.write("吹牛哥哥")
        writer.flush()
        writer.close()
    }
}
