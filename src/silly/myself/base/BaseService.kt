package silly.myself.base

import org.apache.commons.beanutils.BeanUtils
import javax.servlet.http.HttpServletResponse

abstract class BaseService<T>(private val response: HttpServletResponse, private var map: Map<String, Any>, var model: T) {

    init {
        init()
    }

    private fun init() {
        BeanUtils.populate(model, map)
        if (isEmpty()) mainService()
    }

    /**
     * 判空
     */
    abstract fun isEmpty(): Boolean

    /**
     * 业务处理
     */
    abstract fun mainService()


    /**
     * 发送数据给客户端
     */
    protected fun sendData(data: String) {
        response.writer.write(data)
        response.writer.flush()
        response.writer.close()
    }
}
