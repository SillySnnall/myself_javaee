package silly.myself.common

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Servlet相应配置
 */
fun config(request: HttpServletRequest, response: HttpServletResponse) {
    request.characterEncoding = "utf-8"
    response.contentType = "text/json;charset=utf-8"
    response.characterEncoding = "utf-8"
    response.addHeader("Access-Control-Allow-Origin", "*")
}