package silly.myself.test

import com.github.qcloudsms.SmsSingleSender
import com.github.qcloudsms.httpclient.HTTPException
import com.google.gson.Gson
import org.json.JSONException
import org.junit.Test
import silly.myself.bean.User
import silly.myself.dao.UserDao
import silly.myself.utils.DesUtil

import java.io.IOException

class CommonTest {

    // 短信应用SDK AppID
    internal var appid = 1400132515 // 1400开头

    // 短信应用SDK AppKey
    internal var appkey = "c23d066637209277a771af4cc767ff23"

    // 需要发送短信的手机号码
    internal var phoneNumbers = arrayOf("15559733554", "12345678902", "12345678903")

    // 短信模板ID，需要在短信应用中申请
    internal var templateId = 180152 // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
    //templateId7839对应的内容是"您的验证码是: {1}"
    // 签名
    internal var smsSign = "myself" // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`


    @Test
    fun test() {

        try {
            val params = arrayOf("5678")//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            val ssender = SmsSingleSender(appid, appkey)
            val result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "")  // 签名参数未提供或者为空时，会使用默认签名发送短信
            println(result)
        } catch (e: HTTPException) {
            // HTTP响应码错误
            e.printStackTrace()
        } catch (e: JSONException) {
            // json解析错误
            e.printStackTrace()
        } catch (e: IOException) {
            // 网络IO错误
            e.printStackTrace()
        }

    }

    @Test
    fun xxxxxx() {
        val findByAccount = UserDao().findByAccount("admin")
      println(findByAccount)
    }

    @Test
    fun add(){
        UserDao().addUser(User(account = "admin",password = "admin"))
    }
}
